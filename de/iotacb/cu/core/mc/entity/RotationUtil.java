package de.iotacb.cu.core.mc.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class RotationUtil {

	public static final RotationUtil INSTANCE = new RotationUtil();
	private static final Minecraft MC = Minecraft.getMinecraft();

	public float currentYaw, currentPitch; // You can use this for the smooth rotations

	/**
	 * Returns the current rotation
	 * @return
	 */
	public final float[] getCurrentRotations() {
		return new float[] { currentYaw, currentPitch };
	}
	
	/**
	 * Sets the current rotation
	 * @param yaw
	 * @param pitch
	 */
	public final void setCurrentRotations(final float yaw, final float pitch) {
		this.currentYaw = yaw;
		this.currentPitch = pitch;
	}

	/**
	 * Will return rotations to look at the given entity Index 0: Yaw Index 1: Pitch
	 * 
	 * @param entity
	 * @return
	 */
	public final float[] getRotations(final Entity entity) {
		final Vec3 playerPos = new Vec3(MC.thePlayer.posX, MC.thePlayer.posY + (entity instanceof EntityLivingBase ? MC.thePlayer.getEyeHeight() : 0), MC.thePlayer.posZ);
		final Vec3 entityPos = new Vec3(entity.posX, entity.posY, entity.posZ);

		final double diffX = entityPos.xCoord - playerPos.xCoord;
		final double diffY = (entity instanceof EntityLivingBase ? entityPos.yCoord + entity.getEyeHeight() - playerPos.yCoord : entityPos.yCoord - playerPos.yCoord);
		final double diffZ = entityPos.zCoord - playerPos.zCoord;

		final double dist = Math.sqrt(diffX * diffX + diffZ * diffZ);

		double yaw = Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0F;
		double pitch = -Math.toDegrees(Math.atan2(diffY, dist));

		yaw = MC.thePlayer.rotationYaw + MathHelper.wrapAngleTo180_double(yaw - MC.thePlayer.rotationYaw);
		pitch = MC.thePlayer.rotationPitch + MathHelper.wrapAngleTo180_double(pitch - MC.thePlayer.rotationPitch);

		return new float[] { (float) yaw, (float) pitch };
	}

	/**
	 * Will return rotations to look at the given position Index 0: Yaw Index 1:
	 * Pitch
	 * 
	 * @param pos
	 * @return
	 */
	public final float[] getRotations(final Vec3 pos) {
		final Vec3 playerPos = new Vec3(MC.thePlayer.posX, MC.thePlayer.posY, MC.thePlayer.posZ);

		final double diffX = pos.xCoord + 0.5 - playerPos.xCoord;
		final double diffY = pos.yCoord + 0.5 - (playerPos.yCoord + MC.thePlayer.getEyeHeight());
		final double diffZ = pos.zCoord + 0.5 - playerPos.zCoord;

		final double dist = MathHelper.sqrt_double(diffX * diffX + diffZ * diffZ);

		double yaw = Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0F;
		double pitch = -Math.toDegrees(Math.atan2(diffY, dist));

		yaw = MC.thePlayer.rotationYaw + MathHelper.wrapAngleTo180_double(yaw - MC.thePlayer.rotationYaw);
		pitch = MC.thePlayer.rotationPitch + MathHelper.wrapAngleTo180_double(pitch - MC.thePlayer.rotationPitch);
		return new float[] { (float) yaw, (float) pitch };
	}

	/**
	 * Returns a rotation based on the current rotation reaching the target rotation
	 * by using the given speed Index 0: Yaw Index 1: Pitch
	 * 
	 * @param currentRotations
	 * @param neededRotations
	 * @param rotationSpeed
	 * @return
	 */
	public final float[] smoothRotation(final float[] currentRotations, final float[] targetRotations, final float rotationSpeed) {
		final float yawDiff = getDifference(targetRotations[0], currentRotations[0]);
		final float pitchDiff = getDifference(targetRotations[1], currentRotations[1]);

		float rotationSpeedYaw = rotationSpeed;

		if (yawDiff > rotationSpeed) {
			rotationSpeedYaw = rotationSpeed;
		} else {
			rotationSpeedYaw = Math.max(yawDiff, -rotationSpeed);
		}

		float rotationSpeedPitch = rotationSpeed;

		if (pitchDiff > rotationSpeed) {
			rotationSpeedPitch = rotationSpeed;
		} else {
			rotationSpeedPitch = Math.max(pitchDiff, -rotationSpeed);
		}

		final float newYaw = currentRotations[0] + rotationSpeedYaw;
		final float newPitch = currentRotations[1] + rotationSpeedPitch;

		return new float[] { newYaw, newPitch };
	}

	/**
	 * Returns the difference between two angles Code taken from:
	 * https://rosettacode.org/wiki/Angle_difference_between_two_bearings#Java
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public final float getDifference(final float a, final float b) {
		float r = ((a - b) % 360.0F);
		if (r < -180.0) {
			r += 360.0;
		}
		if (r >= 180.0) {
			r -= 360.0;
		}
		return r;
	}

	/**
	 * Author: Mojang Returns a vector based on yaw and rotation
	 * 
	 * @param yaw
	 * @param pitch
	 * @return
	 */
	public final Vec3 getVectorForRotation(final float yaw, final float pitch) {
		final double f = Math.cos(Math.toRadians(-yaw) - Math.PI);
		final double f1 = Math.sin(Math.toRadians(-yaw) - Math.PI);
		final double f2 = -Math.cos(Math.toRadians(-pitch));
		final double f3 = Math.sin(Math.toRadians(-pitch));
		return new Vec3((f1 * f2), f3, (f * f2));
	}

}
