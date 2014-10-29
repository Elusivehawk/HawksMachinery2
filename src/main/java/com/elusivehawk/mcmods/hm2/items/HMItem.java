
package com.elusivehawk.mcmods.hm2.items;

import com.elusivehawk.mcmods.hm2.HawksMachinery2;
import net.minecraft.creativetab.CreativeTabs;
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
