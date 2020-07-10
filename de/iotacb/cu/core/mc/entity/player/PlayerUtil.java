package de.iotacb.cu.core.mc.entity.player;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

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
	 * Return the nearest entity to the player
	 * @return
	 */
	public static final Entity getNearestEntity() {
		final List<Entity> entities = MC.theWorld.loadedEntityList.stream().filter(ent -> ent != MC.thePlayer).collect(Collectors.toList());
		entities.sort(Comparator.comparingDouble(ent -> MC.thePlayer.getDistanceToEntity(ent)));
		return entities.get(0);
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
	
}
