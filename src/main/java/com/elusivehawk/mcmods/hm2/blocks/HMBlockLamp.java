
package com.elusivehawk.mcmods.hm2.blocks;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Direction;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMBlockLamp extends HMBlock
{
	@SideOnly(Side.CLIENT)
	private final IIcon[] icons = new IIcon[16];
	
	public HMBlockLamp()
	{
		super(Material.glass, "lamp");
		setStepSound(soundTypeGlass);
		
	}
	
	@Override
	public boolean isNormalCube()
	{
		return true;
	}
	
	@Override
	public boolean canConnectRedstone(IBlockAccess w, int x, int y, int z, int side)
	{
		return side != -1;
	}
	
	@Override
	public void registerBlockIcons(IIconRegister register)
	{
		for (int c = 0; c < this.icons.length; c++)
		{
			this.icons[c] = register.registerIcon(this.getTextureName() + "_" + c);
			
		}
		
	}
	
	@Override
	public IIcon getIcon(int side, int meta)
	{
		return this.icons[meta];
	}
	
	@Override
	public int getLightValue(IBlockAccess w, int x, int y, int z)
	{
		return w.getBlockMetadata(x, y, z);
	}
	
	@Override
	public boolean isSideSolid(IBlockAccess w, int x, int y, int z, ForgeDirection side)
	{
		return true;
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
	public void updateTick(World w, int x, int y, int z, Random rng)
	{
		if (w.isRemote)
		{
			return;
		}
		
		int power = w.getStrongestIndirectPower(x, y, z);
		
		if (power != w.getBlockMetadata(x, y, z))
		{
			w.setBlockMetadataWithNotify(x, y, z, power, 2);
			
		}
		
	}
	
	@Override
	public int damageDropped(int meta)//Explicitly make sure this only drops 0.
	{
		return 0;
	}
	
}
