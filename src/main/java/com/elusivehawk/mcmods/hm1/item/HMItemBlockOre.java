
package com.elusivehawk.mcmods.hm1.item;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMItemBlockOre extends ItemBlock
{
	public HMItemBlockOre(int id)
	{
		super(id);
		setHasSubtypes(true);
		
	}
	
	@Override
	public String getItemNameIS(ItemStack item)
	{
		return "tile.HMOre" + item.getItemDamage();
	}
	
	@Override
	public EnumRarity getRarity(ItemStack item)
	{
		return item.getItemDamage() == 0 ? EnumRarity.rare : EnumRarity.common;
	}
	
	@Override
	public int getMetadata(int meta)
	{
		return meta;
	}
	
}
