
package com.elusivehawk.mcmods.hm2;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMTabBlocks extends CreativeTabs
{
	public HMTabBlocks()
	{
		super("hm2.blocks");
		
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem()
	{
		return null;//Item.getItemFromBlock(HawksMachinery2.instance().storageBlock);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int func_151243_f()
	{
		return 3;
	}
	
}
