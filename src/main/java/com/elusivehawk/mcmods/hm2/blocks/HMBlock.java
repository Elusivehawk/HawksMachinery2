
package com.elusivehawk.mcmods.hm2.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMBlock extends Block
{
	public HMBlock(Material m, String name)
	{
		super(m);
		setBlockName("hm2." + name);
		setBlockTextureName("hm2:" + name.replace('.', '_'));
		//setCreativeTab(HawksMachinery2.getBlockTab());
		
	}
	
	@Override
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer player, int s, float hitX, float hitY, float hitZ)
	{
		if (player.isSneaking())
		{
			return false;
		}
		
		TileEntity te = w.getTileEntity(x, y, z);
		
		if (te == null || !(te instanceof HMTileEntity))
		{
			return false;
		}
		
		return ((HMTileEntity)te).onRightClick(player, ForgeDirection.getOrientation(s), hitX, hitY, hitZ);
	}
	
	public int regulateMetadata(World w, int x, int y, int z, int meta)
	{
		return meta;
	}
	
}
