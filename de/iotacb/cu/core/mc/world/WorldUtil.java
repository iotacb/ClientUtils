package de.iotacb.cu.core.mc.world;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

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

}
