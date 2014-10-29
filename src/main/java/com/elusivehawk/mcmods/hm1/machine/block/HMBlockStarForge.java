
package com.elusivehawk.mcmods.hm1.machine.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import com.elusivehawk.mcmods.hm1.machine.HMMachines;
import com.elusivehawk.mcmods.hm1.machine.tileentity.HMTileEntityStarForge;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMBlockStarForge extends HMBlockMachine
{
	public HMBlockStarForge(int id)
	{
		super("HMStarForge", id, Material.iron);
		setHardness(5.0F);
		setResistance(20.0F);
		setRequiresSelfNotify();
		
	}
	
	@Override
	public boolean onMachineActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if (super.onMachineActivated(world, x, y, z, player, side, hitX, hitY, hitZ))
		{
			return false;
		}
		
		if (!world.isRemote)
		{
			player.openGui(HMMachines.instance(), 4, world, x, y, z);
			
		}
		
		return true;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entity)
	{
		((HMTileEntityStarForge)world.getBlockTileEntity(x, y, z)).onCreate(new Vector3(x, y, z));
		
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
	{
		((HMTileEntityStarForge)world.getBlockTileEntity(x, y, z)).onDestroy(world.getBlockTileEntity(x, y, z), false);
		super.breakBlock(world, x, y, z, par5, par6);
		
	}
	
	@Override
	public int getRenderType()
	{
		return HMMachines.PROXY.getHMRenderID();
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side)
	{
		return false;
	}
	
	@Override
	public boolean hasTileEntity(int metadata)
	{
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, int meta)
	{
		return new HMTileEntityStarForge();
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		for (int x2 = -1; x2 < 2; ++x2)
		{
			for (int z2 = -1; z2 < 2; ++z2)
			{
				if (!super.canPlaceBlockAt(world, x + x2, y, z + z2))
				{
					return false;
				}
				
			}
			
		}
		
		return true;
	}
	
}
