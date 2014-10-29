
package com.elusivehawk.mcmods.hm2;

import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.item.ItemStack;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public final class HMItemStackHelper
{
	public static final int WILDCARD = -1;
	
	private HMItemStackHelper(){}
	
	public static boolean compareItems(ItemStack one, ItemStack two)
	{
		if (one == null)
		{
			return two == null;
		}
		
		if (one.getItem() != two.getItem())
		{
			return false;
		}
		
		if (one.getItemDamage() != two.getItemDamage() && (one.getItemDamage() != WILDCARD || two.getItemDamage() != WILDCARD))
		{
			return false;
		}
		
		if (one.stackTagCompound == null)
		{
			return two.stackTagCompound == null;
		}
		
		return one.stackTagCompound.equals(two.stackTagCompound);
	}
	
	public static <T> T getItem(ItemStack item, Map<ItemStack, T> map)
	{
		for (Entry<ItemStack, T> entry : map.entrySet())
		{
			if (compareItems(item, entry.getKey()))
			{
				return entry.getValue();
			}
			
		}
		
		return null;
	}
	
}
