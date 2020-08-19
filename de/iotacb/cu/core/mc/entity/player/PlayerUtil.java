package de.iotacb.cu.core.mc.entity.player;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import de.iotacb.cu.core.mc.entity.EntityUtil;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;

public class PlayerUtil {
	
	private static final Minecraft MC = Minecraft.getMinecraft();

	/**
	 * Returns true if the player is moving
	 * @return
	 */
	public static final boolean isMoving() {
		return MC.thePlayer.movementInput.moveForward != 0 || MC.thePlayer.movementInput.moveStrafe != 0;
	}
	
	/**
	 * Returns the speed of the player
	 * @param yMotion
	 * @return
	 */
	public static final double getSpeed(final boolean yMotion) {
		return EntityUtil.getSpeed(MC.thePlayer, yMotion);
	}
	
	/**
	 * Set the speed of the player
	 * @param speed
	 */
	public static final void setSpeed(final double speed) {
		float yaw = getYaw();
		
		final double x = -Math.sin(yaw);
		final double z = Math.cos(yaw);
		
		MC.thePlayer.motionX = x * speed;
		MC.thePlayer.motionZ = z * speed;
	}
	
	/**
	 * Get the yaw of the player, will pay attention to movekey presses
	 * @return
	 */
    public static final float getYaw() {
        float rotationYaw = MC.thePlayer.rotationYaw;
        float forward = 1F;

        final double moveForward = MC.thePlayer.movementInput.moveForward;
        final double moveStrafing = MC.thePlayer.movementInput.moveStrafe;
        final float yaw = MC.thePlayer.rotationYaw;
        
        if (moveForward < 0) {
        	rotationYaw += 180F;
        }

        if (moveForward < 0) {
        	forward = -0.5F;
        } else if(moveForward > 0) {
        	forward = 0.5F;
        }

        if (moveStrafing > 0) {
        	rotationYaw -= 90F * forward;
        } else if(moveStrafing < 0) {
        	rotationYaw += 90F * forward;
        }

        return (float) Math.toRadians(rotationYaw);
    }
	
	/**
	 * Return the nearest entity to the player
	 * @return
	 */
	public static final Entity getNearestEntity() {
		return EntityUtil.getNearestEntity(MC.thePlayer);
	}
	
	/**
	 * Returns the slot of an item if it is found in the inventory using the given item id
	 * @param itemId
	 * @param hotbar
	 * @return
	 */
	public static final int findItemSlotInInventory(final int itemId, final boolean hotbar) {
		for (int i = hotbar ? 36 : 9; i < 45; i++) {
			final ItemStack stack = MC.thePlayer.inventoryContainer.getSlot(i).getStack();
			if (stack != null) {
				if (Item.getIdFromItem(stack.getItem()) == itemId) {
					return i;
				}
			}
		}
		return -1;
	}
	
	/**
	 * Returns the slot of a block if a block is found in the inventory
	 * @param hotbar
	 * @return
	 */
	public static final int findBlockSlotInInventory(final boolean hotbar) {
		for (int i = hotbar ? 36 : 9; i < 45; i++) {
			final ItemStack stack = MC.thePlayer.inventoryContainer.getSlot(i).getStack();
			if (stack != null) {
				if (stack.getItem() instanceof ItemBlock) {
					final Block block = ((ItemBlock) stack.getItem()).getBlock();
					if (block.isFullBlock()) return i;
				}
			}
		}
		return -1;
	}
	
	/**
	 * Returns true if a block is found in the inventory
	 * @param hotbar
	 * @return
	 */
	public static boolean hasBlockInInventory(final boolean hotbar) {
		return findBlockSlotInInventory(hotbar) != -1;
	}
	
	/**
	 * Return true if an item is found in the inventory using the given item id
	 * @param itemId
	 * @param hotbar
	 * @return
	 */
	public static boolean hasItemInInventory(final int itemId, final boolean hotbar) {
		return findItemSlotInInventory(itemId, hotbar) != -1;
	}
	
	/**
	 * Adds a client side message to the chat
	 * @param message
	 */
	public static final void addChatMessage(final String message) {
		MC.thePlayer.addChatMessage(new ChatComponentText(message));
	}



}
