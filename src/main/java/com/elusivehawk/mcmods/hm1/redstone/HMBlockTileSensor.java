
package com.elusivehawk.mcmods.hm1.redstone;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import com.elusivehawk.mcmods.hm1.block.HMBlock;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMBlockTileSensor extends HMBlock
{
	public HMBlockTileSensor(int id)
	{
		super(id, Material.iron, -1, null);
		setStepSound(Block.soundTypeMetal);
		
	}
	
	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float blockID, int meta)
	{
		return side;
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		return false;
	}
	
	@Override
	public boolean canProvidePower()
	{
		return true;
	}
	
	@Override
	public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side)
	{
		return 0;
	}
	
}
