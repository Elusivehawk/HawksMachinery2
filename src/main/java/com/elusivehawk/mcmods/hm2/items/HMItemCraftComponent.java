
package com.elusivehawk.mcmods.hm2.items;

import java.util.Random;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMItemCraftComponent extends HMItem
{
	public HMItemCraftComponent(String name, int dur)
	{
		super(name, 1);
		setHasSubtypes(false);
		setMaxDamage(dur - 1);
		
	}
	
	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack item)
    {
        return false;
    }
	
	@Override
	public Item getContainerItem()
	{
		return this;
	}
	
	@Override
	public boolean hasContainerItem()
	{
		return true;
	}
	
	@Override
	public ItemStack getContainerItem(ItemStack item)
	{
		return item.attemptDamageItem(1, new Random()) ? null : item;
	}
	
}
