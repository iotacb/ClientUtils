package de.iotacb.cu.core.mc.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.Comparator;

import de.iotacb.cu.core.string.StringUtil;

public class EntityUtil {

    private static final Minecraft MC = Minecraft.getMinecraft();

    /**
     * Returns the nearest entity to the given entity
     *
     * @param entity
     * @return
     */
    public static final Entity getNearestEntity(final Entity entity) {
        return MC.theWorld.loadedEntityList.stream().filter(entity2 ->
                entity2 != entity).min(Comparator.comparingDouble(entity::getDistanceToEntity)).orElse(null);
    }

    /**
     * Returns the item held by the given entity
     *
     * @param entity
     * @return
     */
    public static final ItemStack getItemHeldByEntity(final Entity entity) {
        if (entity instanceof EntityLivingBase) {
            return ((EntityLivingBase) entity).getHeldItem();
        }
        return null;
    }
    
    /**
     * Checks if the given entity is a player
     * @param entity
     * @return
     */
    public static final boolean isPlayer(final Entity entity) {
    	return entity instanceof EntityPlayer;
    }
    
    /**
     * Checks if the given entity is an animal
     * @param entity
     * @return
     */
    public static final boolean isAnimal(final Entity entity) {
    	return (entity instanceof EntityAnimal || entity instanceof EntitySquid || entity instanceof EntityBat);
    }
    
    /**
     * Checks if the given entity is a mob
     * @param entity
     * @return
     */
    public static final boolean isMob(final Entity entity) {
    	return (entity instanceof EntityMob || entity instanceof EntityVillager || entity instanceof EntitySlime || entity instanceof EntityDragon || entity instanceof EntityGhast);
    }
    
    /**
     * Returns the name of the given entity without any formatting
     * @param entity
     * @return
     */
    public static final String getName(final Entity entity) {
    	return StringUtil.removeFormatting(entity.getName());
    }
    
    /**
     * Returns the speed of the entity
     * @param yMotion Use yMotion of the entity for the calculation
     * @return
     */
	public static final double getSpeed(final Entity entity, final boolean yMotion) {
		return Math.sqrt(MC.thePlayer.motionX * MC.thePlayer.motionX + (yMotion ? MC.thePlayer.motionY * MC.thePlayer.motionY : 0) + MC.thePlayer.motionZ * MC.thePlayer.motionZ);
	}


}
