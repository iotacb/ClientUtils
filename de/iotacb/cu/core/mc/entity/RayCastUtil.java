package de.iotacb.cu.core.mc.entity;

import java.util.List;

import com.google.common.base.Predicates;

import net.minecraft.block.BlockAir;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class RayCastUtil {

	public static final RayCastUtil INSTANCE = new RayCastUtil();
	private static final Minecraft MC = Minecraft.getMinecraft();

	/**
	 * Casts a ray in the given direction with the given length If the ray
	 * intersects with a entity it will return it
	 * 
	 * @param range     The length of the ray
	 * @param rotations The direction of the ray
	 * @return
	 */
	public final Entity raycastEntity(double range, float[] rotations) {
		final Entity player = MC.getRenderViewEntity();

		if (player != null && MC.theWorld != null) {
			final Vec3 eyeHeight = player.getPositionEyes(MC.timer.renderPartialTicks);

			final Vec3 looks = RotationUtil.INSTANCE.getVectorForRotation(rotations[0], rotations[1]);
			final Vec3 vec = eyeHeight.addVector(looks.xCoord * range, looks.yCoord * range, looks.zCoord * range);
			final List<Entity> list = MC.theWorld.getEntitiesInAABBexcluding(player, player.getEntityBoundingBox().addCoord(looks.xCoord * range, looks.yCoord * range, looks.zCoord * range).expand(1, 1, 1), Predicates.and(EntitySelectors.NOT_SPECTATING, Entity::canBeCollidedWith));

			Entity raycastedEntity = null;

			for (final Entity entity : list) {
				if (!(entity instanceof EntityLivingBase))
					continue;

				final float borderSize = entity.getCollisionBorderSize();
				final AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox().expand(borderSize, borderSize, borderSize);
				final MovingObjectPosition movingobjectposition = axisalignedbb.calculateIntercept(eyeHeight, vec);

				if (axisalignedbb.isVecInside(eyeHeight)) {
					if (range >= 0.0D) {
						raycastedEntity = entity;
						range = 0.0D;
					}
				} else if (movingobjectposition != null) {
					final double distance = eyeHeight.distanceTo(movingobjectposition.hitVec);

					if (distance < range || range == 0.0D) {

						if (entity == player.ridingEntity) {
							if (range == 0.0D) {
								raycastedEntity = entity;
							}
						} else {
							raycastedEntity = entity;
							range = distance;
						}
					}
				}
			}
			return raycastedEntity;
		}
		return null;
	}

	/**
	 * Same as the other raycast but this method wont use given rotations
	 * @param range
	 * @param target
	 * @return
	 */
	public Entity raycast(final double range, final Entity target) {
		final Vec3 eyePosition = target.getPositionVector().add(new Vec3(0, target.getEyeHeight(), 0));
		final Vec3 eyeHeight = MC.thePlayer.getPositionVector().add(new Vec3(0, MC.thePlayer.getEyeHeight(), 0));
		Vec3 hitVec = null;
		final AxisAlignedBB axisAlignedBB = MC.thePlayer.getEntityBoundingBox().addCoord(eyePosition.xCoord - eyeHeight.xCoord, eyePosition.yCoord - eyeHeight.yCoord, eyePosition.zCoord - eyeHeight.zCoord).expand(1.0F, 1.0F, 1.0F);
		final List<Entity> entities = MC.theWorld.getEntitiesWithinAABBExcludingEntity(MC.thePlayer, axisAlignedBB);
		double rangeExtension = range + 0.5;
		
		Entity raycastedEntity = null;
		for (final Entity entity : entities) {

			if (entity.canBeCollidedWith()) {
				float collisionSize = entity.getCollisionBorderSize();
				final AxisAlignedBB expandedBox = entity.getEntityBoundingBox().expand(collisionSize, collisionSize, collisionSize);
				final MovingObjectPosition movingObjectPosition = expandedBox.calculateIntercept(eyeHeight, eyePosition);

				if (expandedBox.isVecInside(eyeHeight)) {
					if (0.0D < rangeExtension || rangeExtension == 0.0D) {
						raycastedEntity = entity;
						hitVec = movingObjectPosition == null ? eyeHeight : movingObjectPosition.hitVec;
						rangeExtension = 0.0D;
					}
				} else if (movingObjectPosition != null) {
					final double eyeDistance = eyeHeight.distanceTo(movingObjectPosition.hitVec);

					if (eyeDistance < rangeExtension || rangeExtension == 0.0D) {
						raycastedEntity = entity;
						hitVec = movingObjectPosition.hitVec;
						rangeExtension = eyeDistance;
					}
				}
			}
		}
		return raycastedEntity;
	}

	/**
	 * Casts a ray into the given direction returns if the ray hit the given block
	 * position
	 * 
	 * @param yaw
	 * @param pitch
	 * @param pos
	 * @return
	 */
	public final boolean rayTraceBlockSide(final BlockPos pos, final float[] rotations) {
		final Vec3 eyePosition = MC.thePlayer.getPositionEyes(1.0f);
		final Vec3 vector = RotationUtil.INSTANCE.getVectorForRotation(rotations[0], rotations[1]);
		final Vec3 lookVector = eyePosition.addVector(vector.xCoord * 5, vector.yCoord * 5, vector.zCoord * 5);

		final MovingObjectPosition result = MC.theWorld.rayTraceBlocks(eyePosition, lookVector, false);

		return result != null && result.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && pos.equals(result.getBlockPos());
	}
	
	/**
	 * Casts a ray into the current viewing direction returns the hit block
	 * @param range
	 * @return
	 */
	public final BlockPos raycastPosition(final double range) {
		final Entity renderViewEntity = MC.getRenderViewEntity();
		
		if (renderViewEntity != null && MC.theWorld != null) {
			final MovingObjectPosition movingObjectPosition = renderViewEntity.rayTrace(range, 1F);
			
			if (MC.theWorld.getBlockState(movingObjectPosition.getBlockPos()).getBlock() instanceof BlockAir)
				return null;
			
			return movingObjectPosition.getBlockPos();
		}
		return null;
	}

}
