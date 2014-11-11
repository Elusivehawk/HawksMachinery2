
package com.elusivehawk.mcmods.hm2.blocks;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
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
public abstract class HMBlockSensor extends HMBlockRSDirectional
{
	@SideOnly(Side.CLIENT)
	protected IIcon back_off, back_on, side, front;
	
	public HMBlockSensor(Material m, String name)
	{
		super(m, name);
		
	}
	
	@Override
	public void registerBlockIcons(IIconRegister r)
	{
		this.back_off = r.registerIcon(this.getTextureName() + "_back_off");
		this.back_on = r.registerIcon(this.getTextureName()  + "_back_on");
		this.side = r.registerIcon(this.getTextureName() + "_side");
		this.front = r.registerIcon(this.getTextureName() + "_front");
		
	}
	
	@Override
	public IIcon getIcon(IBlockAccess w, int x, int y, int z, int side)
    {
		return getIcon0(side,w.getBlockMetadata(x, y, z));
    }
	
	@Override
	public IIcon getIcon(int side, int meta)
	{
		return this.getIcon0(side, 4);
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon0(int side, int meta)
	{
		ForgeDirection fs = ForgeDirection.getOrientation(side);
		ForgeDirection faceDir = this.getFacingDirection(meta);
		
		if (fs == faceDir)
		{
			return this.front;
		}
		else if (fs.getOpposite() == faceDir)
		{
			if (this.isActive(meta))
			{
				return this.back_on;
			}
			
			return this.back_off;
		}
		
		return this.side;
	}
	
	@Override
	public void updateTick(World w, int x, int y, int z, Random rng)
	{
		if (w.isRemote)
		{
			return;
		}
		
		int meta = w.getBlockMetadata(x, y, z);
		ForgeDirection dir = this.getFacingDirection(meta);
		
		boolean covered = this.isActivated(w, x, y, z, dir);
		
		if (covered != this.isActive(meta))
		{
			w.setBlockMetadataWithNotify(x, y, z, dir.ordinal() | (covered ? 8 : 0), 3);
			
		}
		
	}
	
}
