
package com.elusivehawk.mcmods.hm2.blocks;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMBlockMeta extends HMBlock
{
	@SideOnly(Side.CLIENT)
	protected IIcon[] icons = new IIcon[16];
	protected final String[] types = new String[16];
	protected final int limit;
	
	public HMBlockMeta(Material m, String name, String... kinds)
	{
		super(m, name);
		
		limit = kinds.length;
		
		for (int c = 0; c < kinds.length; c++)
		{
			types[c] = kinds[c];
			
		}
		
	}
	
	@Override
	public void registerBlockIcons(IIconRegister register)
    {
		for (int c = 0; c < this.limit; c++)
		{
			this.icons[c] = register.registerIcon(this.getTextureName() + "_" + this.types[c]);
			
		}
		
    }
	
	@Override
	public IIcon getIcon(int s, int m)
	{
		return this.icons[m];
	}
	
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List l)
    {
		for (int c = 0; c < this.limit; c++)
		{
			l.add(new ItemStack(item, 1, c));
			
		}
		
    }
	
	@Override
	public int damageDropped(int meta)
    {
		return meta;
    }
	
	public String[] getTypes()
	{
		return this.types;
	}
	
}
