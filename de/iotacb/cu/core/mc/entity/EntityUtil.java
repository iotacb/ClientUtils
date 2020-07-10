package de.iotacb.cu.core.mc.entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class EntityUtil {

	private static final Minecraft MC = Minecraft.getMinecraft();
	
	/**
	 * Returns the nearest entity to the given entity
	 * @param entity
	 * @return
	 */
	public static final Entity getNearestEntity(final Entity entity) {
		final List<Entity> entities = MC.theWorld.loadedEntityList.stream().filter(ent -> ent != MC.thePlayer).collect(Collectors.toList());
		entities.sort(Comparator.comparingDouble(ent -> entity.getDistanceToEntity(ent)));
		return entities.get(0);
	}
	
}
