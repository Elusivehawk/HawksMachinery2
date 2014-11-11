
package com.elusivehawk.mcmods.hm1.api;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

/**
 * 
 * Helps ease the annoyance of having to NBTTag {@link ItemStack}s.
 * 
 * @author Elusivehawk
 */
public class HMItemStackHelper
{
	private HMItemStackHelper(){}
	
	public static ItemStack enchantItem(ItemStack item, Enchantment[] enchants, int[] levels)
	{
		for (int counter = 0; counter < enchants.length; ++counter)
		{
			item.addEnchantment(enchants[counter], levels[counter]);
			
		}
		
		return item;
	}
	
	public static ItemStack tagItem(ItemStack item, String[] tagNames, Object[] data)
	{
		if (item.stackTagCompound == null) item.stackTagCompound = new NBTTagCompound();
		
		for (int c = 0; c < tagNames.length; ++c)
		{
			if (data[c] instanceof String) item.stackTagCompound.setString(tagNames[c], (String)data[c]);
			else if (data[c] instanceof Integer) item.stackTagCompound.setInteger(tagNames[c], (Integer)data[c]);
			else if (data[c] instanceof Float) item.stackTagCompound.setFloat(tagNames[c], (Float)data[c]);
			else if (data[c] instanceof Double) item.stackTagCompound.setDouble(tagNames[c], (Double)data[c]);
			else if (data[c] instanceof Long) item.stackTagCompound.setLong(tagNames[c], (Long)data[c]);
			else if (data[c] instanceof Short) item.stackTagCompound.setShort(tagNames[c], (Short)data[c]);
			else if (data[c] instanceof Byte) item.stackTagCompound.setByte(tagNames[c], (Byte)data[c]);
			else if (data[c] instanceof Boolean) item.stackTagCompound.setBoolean(tagNames[c], (Boolean)data[c]);
			else if (data[c] instanceof NBTTagCompound) item.stackTagCompound.setTag(tagNames[c], (NBTTagCompound)data[c]);
			else if (data[c] instanceof NBTBase) item.stackTagCompound.setTag(tagNames[c], (NBTBase)data[c]);
			
		}
		
		return item;
	}
	
}
