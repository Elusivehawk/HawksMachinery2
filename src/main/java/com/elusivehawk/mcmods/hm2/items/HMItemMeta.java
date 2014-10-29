
package com.elusivehawk.mcmods.hm2.items;

import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
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
public class HMItemMeta extends HMItem
{
	@SideOnly(Side.CLIENT)
	protected final IIcon[] icons = new IIcon[16];
	protected final String[] types = new String[16];
	protected final boolean[] effect = new boolean[16];
	protected final EnumRarity[] rarity = new EnumRarity[16];
	protected final int limit;
	
	public HMItemMeta(String name, String... enums)
	{
		super(name);
		
		limit = enums.length;
		
		setHasSubtypes(true);
		
		for (int i = 0; i < limit; i++)
		{
			types[i] = enums[i];
			
		}
		
		for (int c = 0; c < rarity.length; c++)
		{
			rarity[c] = EnumRarity.common;
			
		}
		
	}
	
	public HMItemMeta setHasEffect(String... effected)
	{
		for (String str : effected)
		{
			this.effect[this.indexOf(str)] = true;
			
		}
		
		return this;
	}
	
	public HMItemMeta setRarity(String e, EnumRarity rarity)
	{
		this.rarity[this.indexOf(e)] = rarity;
		
		return this;
	}
	
	public int indexOf(String e)
	{
		for (int c = 0; c < this.types.length; c++)
		{
			if (e.equalsIgnoreCase(this.types[c]))
			{
				return c;
			}
			
		}
		
		return -1;
	}
	
	@Override
	public void registerIcons(IIconRegister register)
    {
		for (int c = 0; c < this.limit; c++)
		{
			this.icons[c] = register.registerIcon(this.getIconString() + "_" + this.types[c]);
			
		}
		
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int dmg)
    {
		return this.icons[dmg];
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public String getUnlocalizedName(ItemStack item)
	{
		return super.getUnlocalizedName(item) + "." + this.types[item.getItemDamage()];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		for (int c = 0; c < this.limit; c++)
		{
			list.add(new ItemStack(item, 1, c));
			
		}
		
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack item, int pass)
	{
		return this.effect[item.getItemDamage()];
	}
	
	@Override
	public EnumRarity getRarity(ItemStack item)
	{
		return this.rarity[item.getItemDamage()];
	}
	
}
