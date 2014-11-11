
package com.elusivehawk.mcmods.hm1.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.Achievement;
import net.minecraft.world.World;
import com.elusivehawk.mcmods.hm1.HMCore;

/**
 * 
 * My personal preferences. Extend this instead of {@link Block} and you'll save yourself some trouble.
 * 
 * @author Elusivehawk
 */
public class HMBlock extends Block
{
	private final Achievement breakAchieve;
	
	public HMBlock(String id, Material mat)
	{
		this(id, mat, null);
		
	}
	
	@SuppressWarnings("unqualified-field-access")
	public HMBlock(String id, Material mat, Achievement a)
	{
		super(mat);
		
		setBlockName(id);
		setHardness(1.0F);
		setResistance(5.0F);
		setCreativeTab(HMCore.instance().tab);
		
		breakAchieve = a;
		
	}
	
	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int meta, EntityPlayer player)
	{
		if (this.breakAchieve != null) player.addStat(this.breakAchieve, 1);
		
	}
	
	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}
	
}
