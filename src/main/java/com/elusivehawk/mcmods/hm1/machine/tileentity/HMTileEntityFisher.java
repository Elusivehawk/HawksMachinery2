
package com.elusivehawk.mcmods.hm1.machine.tileentity;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.util.ForgeDirection;
import com.elusivehawk.mcmods.hm1.machine.HMMachines;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMTileEntityFisher extends HMTileEntityMachine
{
	private Random random = new Random();
	
	public HMTileEntityFisher()
	{
		super();
		ELECTRICITY_REQUIRED = 5;
		ELECTRICITY_LIMIT = 1000;
		containingItems = new ItemStack[19];
		VOLTAGE = 120;
		
	}
	
	@Override
	public ForgeDirection getDefaultCableDirection()
	{
		return ForgeDirection.UP;
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		
		if (this.worldObj.getTotalWorldTime() % 20L == 0L && !this.isDisabled())
		{
			if (this.containingItems[0] != null)
			{
				if (this.containingItems[0].getItem() instanceof IItemElectric)
				{
					IItemElectric electricItem = (IItemElectric)this.containingItems[0].getItem();
					
					if (electricItem.canProduceElectricity() && this.electricityStored > this.ELECTRICITY_LIMIT)
					{
						double receivedElectricity = electricItem.onUse(Math.min(electricItem.getMaxJoules()*0.01, ElectricInfo.getWattHours(this.ELECTRICITY_REQUIRED)), this.containingItems[0]);
						this.electricityStored += ElectricInfo.getWatts(receivedElectricity);
					}
				}
			}
			
			if (this.worldObj.getBlockId(this.xCoord, this.yCoord + 1, this.zCoord) == Block.waterStill.blockID && this.canWork())
			{
				this.worldObj.setBlockWithNotify(this.xCoord, this.yCoord - 1, this.zCoord, 0);
				this.electricityStored -= this.ELECTRICITY_REQUIRED;
				
				if (this.random.nextInt(100) < 5)
				{
					this.addFishAndRemoveFood();
					
				}
				
			}
			
		}
		
	}
	
	@Override
	public boolean canWork()
	{
		int fullStacks = 0;
		boolean hasFood = false;
		
		for (int counter = 1; counter < 10; ++counter)
		{
			if (this.containingItems[counter] != null)
			{
				if (this.containingItems[counter].stackSize == this.containingItems[counter].getMaxStackSize())
				{
					++fullStacks;
				}
				
			}
			
			if (this.containingItems[counter + 9] != null && !hasFood)
			{
				hasFood = this.containingItems[counter + 9].itemID == HMMachines.fishFood.itemID && this.containingItems[counter + 9].stackSize > 0;
			}
			
		}
		
		return fullStacks != 9 && hasFood && this.electricityStored >= (this.ELECTRICITY_REQUIRED * 2);
	}
	
	public void addFishAndRemoveFood()
	{
		if (this.canWork())
		{
			boolean removedFood = false;
			
			for (int counter = 1; counter < 10; ++counter)
			{
				if (this.containingItems[counter + 9] != null && !removedFood)
				{
					if (this.containingItems[counter + 9].isItemEqual(new ItemStack(HMMachines.fishFood)) && this.containingItems[counter + 9].stackSize > 0)
					{
						this.decrStackSize(counter + 9, 1);
						removedFood = true;
						break;
						
					}
					
				}
				
			}
			
			for (int counter = 1; counter < 10; ++counter)
			{
				if (removedFood)
				{
					if (this.containingItems[counter] != null)
					{
						if (this.containingItems[counter].stackSize < this.containingItems[counter].getMaxStackSize() && this.containingItems[counter].stackSize > 0)
						{
							++this.containingItems[counter].stackSize;
							return;
						}
						
					}
					else
					{
						this.containingItems[counter] = new ItemStack(Item.fishRaw);
						return;
					}
					
				}
				
			}
			
		}
		
	}
	
	@Override
	public int getMaxHP()
	{
		return 0;
	}
	
	@Override
	public boolean isDisabled()
	{
		BiomeGenBase currentBiome = this.worldObj.getBiomeGenForCoords(this.xCoord, this.zCoord);
		return super.isDisabled() || (currentBiome != BiomeGenBase.ocean && currentBiome != BiomeGenBase.river && currentBiome != BiomeGenBase.frozenRiver && currentBiome != BiomeGenBase.frozenOcean);
	}
	
}
