
package com.elusivehawk.mcmods.hm1.machine.item;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import com.elusivehawk.mcmods.hm1.api.HMRepairInterfaces.IHMRivet;
import com.elusivehawk.mcmods.hm1.item.HMItem;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMItemRivets extends HMItem implements IHMRivet
{
	public HMItemRivets(int id)
	{
		super(id);
		setHasSubtypes(true);
		
	}
	
	@Override
	public int getIconFromDamage(int dmg)
	{
		return 40 + dmg;
	}
	
	public int getRepairAmount(ItemStack rivet)
	{
		switch (rivet.getItemDamage())
		{
			case 0: return 2;
			case 1: return 4;
			case 2: return 4;
			case 3: return 5;
			case 4: return 3;
			case 5: return 10;
			case 6: return 6;
			default: return 0;
		}
		
	}
	
	@Override
	public String getItemNameIS(ItemStack item)
	{
		return "item.HMRivet" + item.getItemDamage();
	}
	
	@Override
	public boolean hasEffect(ItemStack item)
	{
		return item.getItemDamage() == 5;
	}

	@Override
	public EnumRarity getRarity(ItemStack item)
	{
		return item.getItemDamage() == 5 ? EnumRarity.rare : EnumRarity.common;
	}
	
}
