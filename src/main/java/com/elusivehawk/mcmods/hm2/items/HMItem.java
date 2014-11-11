
package com.elusivehawk.mcmods.hm2.items;

import net.minecraft.item.Item;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMItem extends Item
{
	public HMItem(String name)
	{
		this(name, 64);
		
	}
	
	public HMItem(String name, int stackSize)
	{
		setUnlocalizedName("hm2." + name.replace('_', '.'));
		setTextureName("hm2:" + name);
		//setCreativeTab(HawksMachinery2.getItemTab());
		setMaxStackSize(stackSize);
		
	}
	
}
