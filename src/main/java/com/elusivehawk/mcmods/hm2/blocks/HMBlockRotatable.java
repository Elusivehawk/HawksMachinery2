
package com.elusivehawk.mcmods.hm2.blocks;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMBlockRotatable extends HMBlock
{
	public HMBlockRotatable(Material m, String name)
	{
		super(m, name);
		
	}
	
	public ForgeDirection getFacingDirection(IBlockAccess w, int x, int y, int z)
	{
		return this.getFacingDirection(w.getBlockMetadata(x, y, z));
	}
	
	public ForgeDirection getFacingDirection(int meta)
	{
		return ForgeDirection.getOrientation(meta & 7);
	}
	
	@Override
	public int onBlockPlaced(World w, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int blockMeta)
	{
		return this.regulateMetadata(w, x, y, z, side);
	}
	
	@Override
	public ForgeDirection[] getValidRotations(World world, int x, int y, int z)
	{
		return ForgeDirection.VALID_DIRECTIONS;
	}
	
	@Override
	public boolean rotateBlock(World w, int x, int y, int z, ForgeDirection axis)
	{
		w.setBlockMetadataWithNotify(x, y, z, this.regulateMetadata(w, x, y, z, axis.ordinal()), 2);
		
		return true;
	}
	
}
