
package com.elusivehawk.mcmods.hm2;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMTabItems extends CreativeTabs
{
	@SideOnly(Side.CLIENT)
    private ItemStack item = null;
	
	public HMTabItems()
	{
		super("hm2.items");

	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem()
	{
		return null;//HawksMachinery2.instance().plans;
	}
	
}
