
package com.elusivehawk.mcmods.hm2.api;

/**
 * 
 * Mainly here for WAILA support.
 * 
 * @author Elusivehawk
 */
public interface IHMMechStorage extends IHMMechProvider, IHMMechReceiver
{
	int getStoredMechUnits();
	
	int getMaxMechUnits();
	
}
