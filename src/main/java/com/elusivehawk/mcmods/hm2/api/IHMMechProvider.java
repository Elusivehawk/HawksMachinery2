
package com.elusivehawk.mcmods.hm2.api;

import net.minecraftforge.common.util.ForgeDirection;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public interface IHMMechProvider
{
	int provideMechUnits(ForgeDirection from, int muExp, boolean simulate);
	
}
