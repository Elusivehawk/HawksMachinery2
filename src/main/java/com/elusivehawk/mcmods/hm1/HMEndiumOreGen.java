
package com.elusivehawk.mcmods.hm1;

import net.minecraft.item.ItemStack;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMEndiumOreGen extends OreGenReplace
{
	public HMEndiumOreGen()
	{
		super("Endium Ore", "oreEndium", new ItemStack(HMCore.ore), 121, 12, 64, 8, 8, "pickaxe", 3);
		this.ignoreNether = true;
		this.ignoreSurface = true;
		this.ignoreEnd = false;
		this.shouldGenerate = HMCore.PROXY.generateEndium;
		
	}
	
}
