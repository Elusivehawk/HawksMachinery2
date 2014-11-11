
package com.elusivehawk.mcmods.hm1.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.ForgeChunkManager;
import com.elusivehawk.mcmods.hm1.HMCore;
import com.elusivehawk.mcmods.hm1.tileentity.HMTileEntityEndiumChunkloader;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMBlockEndiumChunkloader extends BlockContainer
{
	public HMBlockEndiumChunkloader(String id)
	{
		super(Material.iron);
		setBlockName(id);
		setHardness(5.0F);
		setResistance(1000000000.0F);
		setBlockName("HMEndiumChunkloader");
		setCreativeTab(HMCore.instance().tab);
		//MinecraftForge.setBlockHarvestLevel(this, "pickaxe", 3);
		
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new HMTileEntityEndiumChunkloader();
	}
	
	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		if (world.isBlockIndirectlyGettingPowered(x, y + 1, z))
		{
			Chunk chunkInside = world.getChunkFromBlockCoords(x, z);
			int xPosMax = chunkInside.xPosition << 4;
			int zPosMax = chunkInside.zPosition << 4;
			int xPosMin = xPosMax + 16;
			int zPosMin = zPosMax + 16;
			
			for (int xOff = 0; xOff < 5; xOff++)
			{
				for (int zOff = 0; zOff < 5; zOff++)
				{
					world.spawnParticle("portal", xPosMax + (xOff + 0.5), y + 1, zPosMax + (zOff + 0.5), 0, 0, 0);
					world.spawnParticle("portal", xPosMin - (xOff + 0.5), y + 1, zPosMax + (zOff + 0.5), 0, 0, 0);
					world.spawnParticle("portal", xPosMax + (xOff + 0.5), y + 1, zPosMin - (zOff + 0.5), 0, 0, 0);
					world.spawnParticle("portal", xPosMin - (xOff + 0.5), y + 1, zPosMin - (zOff + 0.5), 0, 0, 0);
					
				}
				
			}
			
		}
		
	}
	
	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z)
	{
		return 15;
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta)
	{
		ForgeChunkManager.releaseTicket(((HMTileEntityEndiumChunkloader)world.getTileEntity(x, y, z)).heldChunk);
		super.breakBlock(world, x, y, z, block, meta);
		
	}
	
}
