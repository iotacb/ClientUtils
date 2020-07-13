package de.iotacb.cu.core.mc.entity;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class EntityUtil {

	private static final Minecraft MC = Minecraft.getMinecraft();
	public static final EntityUtil INSTANCE = new EntityUtil();
	
	/**
	 * Returns the nearest entity to the given entity
	 * @param entity
	 * @return
	 */
	public final Entity getNearestEntity(final Entity entity) {
		final List<Entity> entities = MC.theWorld.loadedEntityList.stream().filter(ent -> ent != MC.thePlayer).collect(Collectors.toList());
		entities.sort(Comparator.comparingDouble(ent -> entity.getDistanceToEntity(ent)));
		return entities.get(0);
	}
	
	/**
	 * Returns the item held by the given entity
	 * @param entity
	 * @return
	 */
	public final ItemStack getItemHeldByEntity(final Entity entity) {
		if (entity instanceof EntityLivingBase) {
			return ((EntityLivingBase) entity).getHeldItem();
		}
		return null;
	}
	
	
}
