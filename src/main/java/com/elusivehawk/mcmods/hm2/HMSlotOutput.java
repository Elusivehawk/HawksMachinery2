
package com.elusivehawk.mcmods.hm2;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMSlotOutput extends Slot
{
	private final EntityPlayer player;
	private int quantity = 0;
	
	public HMSlotOutput(EntityPlayer thePlayer, IInventory inv, int slot, int x, int y)
	{
		super(inv, slot, x, y);
		player = thePlayer;
		
	}
	
	@Override
	public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack)
	{
		this.onCrafting(par2ItemStack);
		super.onPickupFromSlot(par1EntityPlayer, par2ItemStack);
		
	}
	
	@Override
	protected void onCrafting(ItemStack par1ItemStack, int count)
	{
		this.quantity += count;
		this.onCrafting(par1ItemStack);
		
	}
	
	@Override
	protected void onCrafting(ItemStack par1ItemStack)
	{
		par1ItemStack.onCrafting(this.player.worldObj, this.player, this.quantity);
		this.quantity = 0;
		
	}
	
}
