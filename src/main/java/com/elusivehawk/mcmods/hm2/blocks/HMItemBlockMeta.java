
package com.elusivehawk.mcmods.hm2.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMItemBlockMeta extends ItemBlock
{
	public HMItemBlockMeta(Block block)
	{
		super(block);
		
		assert block instanceof HMBlockMeta;
		
		setHasSubtypes(true);
		setUnlocalizedName(block.getUnlocalizedName());
		
	}
	
	@Override
	public String getUnlocalizedName(ItemStack item)
	{
		return super.getUnlocalizedName(item) + "." + ((HMBlockMeta)this.field_150939_a).getTypes()[item.getItemDamage()];
	}
	
	@Override
	public int getMetadata(int meta)
	{
		return meta;
	}
	
}
