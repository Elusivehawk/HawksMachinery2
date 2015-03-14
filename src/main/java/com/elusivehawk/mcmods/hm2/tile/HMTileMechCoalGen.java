
package com.elusivehawk.mcmods.hm2.tile;

import net.minecraftforge.common.util.ForgeDirection;
import com.elusivehawk.mcmods.hm2.HawksMachinery2;
import com.elusivehawk.mcmods.hm2.api.IHMMechProvider;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMTileMechCoalGen extends HMTileRotatable implements IHMMechProvider
{
	public HMTileMechCoalGen()
	{
		super(ForgeDirection.NORTH, HawksMachinery2.MACHINE_DIRS);
		
	}
	
	@Override
	public int provideMechUnits(ForgeDirection from, int muExp, boolean simulate)
	{
		if (from != this.getDirection().getOpposite())
		{
			return 0;
		}
		
		return 0;
	}
	
}
