
package com.elusivehawk.mcmods.hm1.api;

/**
 * 
 * Mainly used in order to get extra info about a machine.
 * 
 * @author Elusivehawk
 */
public interface IHMMachine
{
	public double getElectricity();
	
	public double getMaxElectricity();
	
	public boolean canWork();
	
}
