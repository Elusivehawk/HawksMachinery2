
package com.elusivehawk.mcmods.hm1.api;

import java.awt.Color;
import net.minecraft.nbt.NBTTagCompound;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMColorHelper
{
	private static HMColorHelper INSTANCE = new HMColorHelper();
	
	public Color readColorFromNBT(NBTTagCompound tag)
	{
		return new Color(tag.getInteger("red"), tag.getInteger("green"), tag.getInteger("blue"));
	}
	
	public NBTTagCompound writeColortoNBT(NBTTagCompound tag, Color color)
	{
		tag.setInteger("red", color.getRed());
		tag.setInteger("green", color.getGreen());
		tag.setInteger("blue", color.getBlue());
		
		return tag;
	}
	
	public static HMColorHelper instance()
	{
		return INSTANCE;
	}
	
}
