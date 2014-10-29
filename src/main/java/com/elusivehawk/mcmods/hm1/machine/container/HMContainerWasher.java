
package com.elusivehawk.mcmods.hm1.machine.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import com.elusivehawk.mcmods.hm1.api.HMRecipes;
import com.elusivehawk.mcmods.hm1.machine.SlotProcessorsOutput;
import com.elusivehawk.mcmods.hm1.machine.tileentity.HMTileEntityWasher;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMContainerWasher extends Container
{
	private HMTileEntityWasher tileEntity;
	
	public HMContainerWasher(InventoryPlayer playerInventory, HMTileEntityWasher tileEntity)
	{
		this.tileEntity = tileEntity;
		this.tileEntity.openChest();
		this.addSlotToContainer(new SlotElectricItem(tileEntity, 0, 34, 60));//Electric item
		this.addSlotToContainer(new Slot(tileEntity, 1, 58, 60));//Water input
		this.addSlotToContainer(new Slot(tileEntity, 2, 34, 33));//Actual input
		this.addSlotToContainer(new SlotProcessorsOutput(playerInventory.player, tileEntity, 3, 107, 32));
		this.addSlotToContainer(new SlotProcessorsOutput(playerInventory.player, tileEntity, 4, 125, 32));
		this.addSlotToContainer(new SlotProcessorsOutput(playerInventory.player, tileEntity, 5, 143, 32));
		
		for (int counter = 0; counter < 3; ++counter)
		{
			for (int var4 = 0; var4 < 9; ++var4)
			{
				this.addSlotToContainer(new Slot(playerInventory, var4 + counter * 9 + 9, 8 + var4 * 18, 84 + counter * 18));
				
			}
			
		}
		
		for (int counter = 0; counter < 9; ++counter)
		{
			this.addSlotToContainer(new Slot(playerInventory, counter, 8 + counter * 18, 142));
			
		}
		
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer var1)
	{
		return true;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int par1)
	{
		ItemStack var2 = null;
		Slot var3 = (Slot)this.inventorySlots.get(par1);
		
		if (var3 != null && var3.getHasStack())
		{
			ItemStack var4 = var3.getStack();
			var2 = var4.copy();
			
			if (par1 == 2)
			{
				if (!this.mergeItemStack(var4, 3, 39, true))
				{
					return null;
				}
				
				var3.onSlotChange(var4, var2);
			}
			else if (par1 != 1 && par1 != 0)
			{
				if (var4.getItem() instanceof IItemElectric)
				{
					if (((IItemElectric)var4.getItem()).canProduceElectricity())
					{
						if (!this.mergeItemStack(var4, 0, 3, false))
						{
							return null;
						}
						
					}
					
				}
				else if (HMRecipes.getResult(var4, HMRecipes.HMEnumProcessing.WASHING) != null)
				{
					if (!this.mergeItemStack(var4, 2, 3, false))
					{
						return null;
					}
					
				}
				else if (var4.getItem() == Item.bucketWater)
				{
					if (!this.mergeItemStack(var4, 1, 3, false))
					{
						return null;
					}
					
				}
				else if (par1 >= 3 && par1 < 30)
				{
					if (!this.mergeItemStack(var4, 30, 39, false))
					{
						return null;
					}
					
				}
				
			}
			
			if (var4.stackSize == 0)
			{
				var3.putStack((ItemStack)null);
				
			}
			else
			{
				var3.onSlotChanged();
				
			}
			
			if (var4.stackSize == var2.stackSize)
			{
				return null;
			}
			
			var3.onPickupFromSlot(player, var4);
			
		}
		
		return var2;
	}
	
	@Override
	public void onCraftGuiClosed(EntityPlayer par1EntityPlayer)
    {
        super.onCraftGuiClosed(par1EntityPlayer);
        this.tileEntity.closeChest();
        
    }
	
}
