package com.unrelentless.fcraft.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.unrelentless.fcraft.items.FCraftItemMateria;

public class SlotSupport extends Slot
{
	public SlotSupport(IInventory inventory, int slotIndex, int x, int y) {
		super(inventory, slotIndex, x, y);
	}

	/**
	 * Check if the stack is a valid item for this slot. Always true beside for the armor slots
	 * (and now also not always true for our custom inventory slots)
	 */
	@Override
	public boolean isItemValid(ItemStack stack) {
		// We only want our custom item to be storable in this slot
		if(stack.getItem() instanceof FCraftItemMateria && stack.getItemDamage() == 4){
			return true;
		}
		return false;
	}

	@Override
	public int getSlotStackLimit() {
		return 1;
	}
}