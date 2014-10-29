
package com.elusivehawk.mcmods.hm1.machine.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import com.elusivehawk.mcmods.hm1.HMCore;
import com.elusivehawk.mcmods.hm1.api.HMRepairInterfaces.IHMSappable;
import com.elusivehawk.mcmods.hm1.api.HMRepairInterfaces.IHMSapper;

/**
 * 
 * Just a wrapper for {@link BlockMachine}.
 * 
 * @author Elusivehawk
 */
public abstract class HMBlockMachine extends BlockMachine
{
	public HMBlockMachine(String name, int id, Material mat)
	{
		super(name, id, mat);
		setHardness(1.0F);
		setResistance(5.0F);
		setStepSound(Block.soundMetalFootstep);
		setTextureFile(HMCore.BLOCK_TEXTURE_FILE);
		setCreativeTab(HMCore.instance().tab);
		
	}
	
	@Override
	public boolean onMachineActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		ItemStack playerItem = player.getCurrentEquippedItem();
		
		if (playerItem != null && !player.isSneaking())
		{
			if (playerItem.getItem() instanceof IHMSapper)
			{
				if (((IHMSapper)playerItem.getItem()).sappersRequired(playerItem) > 0)
				{
					IHMSapper sapper = ((IHMSapper)playerItem.getItem());
					
					if (world.getBlockTileEntity(x, y, z) instanceof IHMSappable)
					{
						return ((IHMSappable)world.getBlockTileEntity(x, y, z)).setSapper(new ItemStack(playerItem.getItem(), sapper.sappersRequired(playerItem), playerItem.getItemDamage()));
					}
					
				}
				
			}
			
		}
		
		return false;
	}
	
	@Override
	public boolean onSneakUseWrench(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if (world.getBlockTileEntity(x, y, z) instanceof IHMSappable)
		{
			if (((IHMSappable)world.getBlockTileEntity(x, y, z)).isBeingSapped())
			{
				((IHMSappable)world.getBlockTileEntity(x, y, z)).attemptToUnSap(player);
				return true;
			}
			
		}
		
		return false;
	}
	
	@Override
	public boolean isBlockSolid(IBlockAccess world, int x, int y, int z, int side)
	{
		return side != world.getBlockMetadata(x, y, z) && side != 1;
	}
	
	@Override
	public int damageDropped(int metadata)
	{
		return 0;
	}
	
}
