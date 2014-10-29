
package com.elusivehawk.mcmods.hm1.machine.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import com.elusivehawk.mcmods.hm1.api.HMRepairInterfaces.IHMRepairable;
import com.elusivehawk.mcmods.hm1.api.HMRepairInterfaces.IHMRivet;
import com.elusivehawk.mcmods.hm1.item.HMItemElectric;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMItemRivetGun extends HMItemElectric implements IToolRepair
{
	public HMItemRivetGun(int id)
	{
		super(id, 30000, 120);
		setItemName("HMRivetGun");
		setIconIndex(136);
		
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
	{
		if (player.isSneaking() && (item.getItemDamage() < (this.getMaxDamage() - 1) || !item.isItemDamaged()))
		{
			player.setItemInUse(item, this.getMaxItemUseDuration(item));
			
		}
		
		return item;
	}
	
	@Override
	public ItemStack onFoodEaten(ItemStack item, World world, EntityPlayer player)
	{
		MovingObjectPosition locatedBlock = this.getMovingObjectPositionFromPlayer(world, player, true);
		
		if (locatedBlock != null)
		{
			if (locatedBlock.typeOfHit == EnumMovingObjectType.TILE);
			{
				TileEntity foundBlock = world.getBlockTileEntity(locatedBlock.blockX, locatedBlock.blockY, locatedBlock.blockZ);
				
				if (foundBlock != null)
				{
					if (foundBlock instanceof TileEntityMulti) foundBlock = ((TileEntityMulti)foundBlock).mainBlockPosition.getTileEntity(world);
					
					if (foundBlock instanceof IHMRepairable)
					{
						if (((IHMRepairable)foundBlock).getMaxHP() > 0)
						{
							return this.tryRepairTile(foundBlock, player, item);
						}
						
					}
					else if (foundBlock instanceof IRepairable)
					{
						if (((IRepairable)foundBlock).getMaxDamage() > 0)
						{
							return this.tryRepairTile(foundBlock, player, item);
						}
						
					}
					
				}
				
			}
			
		}
		
		return item;
	}
	
	public ItemStack tryRepairTile(TileEntity machine, EntityPlayer player, ItemStack item)
	{
		for (int counter = 0; counter < player.inventory.getSizeInventory(); ++counter)
		{
			ItemStack invItem = player.inventory.getStackInSlot(counter);
			
			if (invItem != null)
			{
				if (invItem.getItem() instanceof IHMRivet)
				{
					int potentialRepairAmount = ((IHMRivet)invItem.getItem()).getRepairAmount(invItem);
					
					if (potentialRepairAmount > 0)
					{
						if (machine instanceof IHMRepairable)
						{
							if (((IHMRepairable)machine).attemptToRepair(potentialRepairAmount))
							{
								if (!player.capabilities.isCreativeMode) player.inventory.decrStackSize(counter, 1);
								player.swingItem();
								this.onUse(10, item);
								
							}
							
						}
						else if (machine instanceof IRepairable)
						{
							item.stackTagCompound.setInteger("repairValue", potentialRepairAmount);
							((IRepairable)machine).onRepair(this, player);
							item.stackTagCompound.setInteger("repairValue", 0);
							
						}
						
					}
					
				}
				
			}
			
		}
		
		return item;
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack item)
	{
		return EnumAction.bow;
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack item)
	{
		return 25;
	}
	
	@Override
	public boolean isFull3D()
	{
		return true;
	}
	
	@Override
	public String getID()
	{
		return "HMRivetGun";
	}
	
	@Override
	public int getEffectiveness(ItemStack item)
	{
		return item.stackTagCompound.getInteger("repairValue");
	}
	
}
