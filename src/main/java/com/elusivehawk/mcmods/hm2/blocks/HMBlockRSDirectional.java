
package com.elusivehawk.mcmods.hm2.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public abstract class HMBlockRSDirectional extends HMBlockRotatable
{
	public HMBlockRSDirectional(Material m, String name)
	{
		super(m, name);
		
	}
	
	public abstract boolean isActivated(World w, int x, int y, int z, ForgeDirection dir);
	
	public int getPowerOutput(World w, int x, int y, int z, ForgeDirection dir)
	{
		return this.isActivated(w, x, y, z, dir) ? 15 : 0;
	}
	
	public boolean isActive(IBlockAccess w, int x, int y, int z)
	{
		return this.isActive(w.getBlockMetadata(x, y, z));
	}
	
	public boolean isActive(int meta)
	{
		return (meta & 8) == 8;
	}
	
	@Override
	public boolean canProvidePower()
	{
		return true;
	}
	
	@Override
	public boolean canConnectRedstone(IBlockAccess w, int x, int y, int z, int side)
	{
		if (side == -1)
		{
			return false;
		}
		
		ForgeDirection dir = ForgeDirection.getOrientation(Direction.directionToFacing[side]);
		
		return dir != this.getFacingDirection(w, x, y, z).getOpposite();
	}
	
	@Override
	public int tickRate(World w)
    {
        return 2;
    }
	
	@Override
	public void onBlockAdded(World w, int x, int y, int z)
	{
		w.scheduleBlockUpdate(x, y, z, this, this.tickRate(w));
		
	}
	
	@Override
	public void onNeighborBlockChange(World w, int x, int y, int z, Block b)
	{
		w.scheduleBlockUpdate(x, y, z, this, this.tickRate(w));
		
	}
	
	@Override
	public int isProvidingWeakPower(IBlockAccess w, int x, int y, int z, int side)
	{
		ForgeDirection facing = this.getFacingDirection(w, x, y, z);
		
		if (facing.getOpposite().ordinal() == side)
		{
			return 0;
		}
		
		if (!(w instanceof World))
		{
			return this.isActive(w, x, y, z) ? 15 : 0;
		}
		
		return this.getPowerOutput((World)w, x, y, z, facing);
	}
	
	@Override
	public int regulateMetadata(World w, int x, int y, int z, int meta)
	{
		return meta | (this.isActivated(w, x, y, z, this.getFacingDirection(meta)) ? 8 : 0);
	}
	
	@Override
	public int damageDropped(int meta)//Explicitly make sure this only drops 0.
	{
		return 0;
	}
	
}
