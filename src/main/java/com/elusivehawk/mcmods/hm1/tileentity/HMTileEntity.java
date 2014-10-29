
package com.elusivehawk.mcmods.hm1.tileentity;

import com.elusivehawk.mcmods.hm1.api.HMVector;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMTileEntity extends TileEntityAdvanced
{
	protected HMVector selfVec;
	
	@Override
	public void initiate()
	{
		this.selfVec = new HMVector(this);
		
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		
	}
	
}
