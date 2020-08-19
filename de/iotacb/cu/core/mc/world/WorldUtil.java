package de.iotacb.cu.core.mc.world;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;

public class WorldUtil {
	
	private static final Minecraft MC = Minecraft.getMinecraft();
	
	/**
	 * Returns an entity if it is found in the world using the given entity id
	 * @param entityId
	 * @return
	 */
	public static final Entity getEntityById(final int entityId) {
		return MC.theWorld.loadedEntityList.stream().filter(entity -> entity.getEntityId() == entityId).findFirst().orElse(null);
	}
	
	/**
	 * Returns an entity if it is found in the world using the given entity name
	 * @param entityName
	 * @return
	 */
	public static final Entity getEntityByName(final String entityName) {
		return MC.theWorld.loadedEntityList.stream().filter(entity -> entity.getName().equals(entityName)).findFirst().orElse(null);
	}
	
	/**
	 * Returns a list of entities which passed the filtering
	 * @param predicate
	 * @return
	 */
	public static final List<Entity> getFilteredEntityList(final Predicate<Entity> predicate) {
		return MC.theWorld.loadedEntityList.stream().filter(predicate).collect(Collectors.<Entity>toList());
	}
	
	/**
	 * Returns a list of tile entities which passed the filtering
	 * @param predicate
	 * @return
	 */
	public static final List<TileEntity> getFilteredTileEntityList(final Predicate<TileEntity> predicate) {
		return MC.theWorld.loadedTileEntityList.stream().filter(predicate).collect(Collectors.<TileEntity>toList());
	}
	
	/**
	 * Returns a list of entities which are in a given area
	 * @param x
	 * @param y
	 * @param z
	 * @param radius
	 * @return
	 */
	public static final List<Entity> getEntitiesByPosition(final double x, final double y, final double z, final double radius) {
		return getFilteredEntityList(new Predicate<Entity>() {

			@Override
			public boolean test(Entity t) {
				return Math.sqrt(t.getDistanceSqToCenter(new BlockPos(x, y, z))) <= radius;
			}
		});
	}

}
