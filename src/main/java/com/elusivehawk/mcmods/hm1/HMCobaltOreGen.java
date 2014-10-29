
package com.elusivehawk.mcmods.hm1;

import net.minecraft.item.ItemStack;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMCobaltOreGen extends OreGenReplace
{
	public HMCobaltOreGen()
	{
		super("Cobalt Ore", "oreCobalt", new ItemStack(HMCore.ore, 1, 1), 1, 12, 30, 32, 1, "pickaxe", 2);
		this.shouldGenerate = HMCore.PROXY.generateCobalt;
		
	}
	
}
