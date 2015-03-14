
package com.elusivehawk.mcmods.hm2.api;

import net.minecraftforge.common.util.ForgeDirection;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public interface IHMMechReceiver
{
	int giveMechUnits(ForgeDirection from, int mu, boolean simulate);
	
}
