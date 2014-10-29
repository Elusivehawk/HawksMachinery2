
package com.elusivehawk.mcmods.hm1.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import com.elusivehawk.mcmods.hm1.tileentity.HMTileEntityMulti;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMBlockMulti extends BlockMulti
{
	public HMBlockMulti(int id)
	{
		super(id);
		setBlockName("HMBlockTechnical");
		
	}
	
	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new HMTileEntityMulti();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		return ((HMTileEntityMulti)world.getBlockTileEntity(x, y, z)).onBlockActivated(world, x, y, z, player);
	}
	
}
