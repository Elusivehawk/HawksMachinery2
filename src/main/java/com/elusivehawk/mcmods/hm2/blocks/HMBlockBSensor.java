
package com.elusivehawk.mcmods.hm2.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMBlockBSensor extends HMBlockSensor
{
	public HMBlockBSensor(Material mat)
	{
		super(mat, "sensor.block");
		
	}
	
	@Override
	public boolean isActivated(World w, int x, int y, int z, ForgeDirection dir)
	{
		Block b = w.getBlock(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
		
		return b.getMaterial() != Material.air && (b.isOpaqueCube() || b.isBlockNormalCube());
	}
	
}
