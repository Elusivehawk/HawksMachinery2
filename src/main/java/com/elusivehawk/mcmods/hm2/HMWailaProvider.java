
package com.elusivehawk.mcmods.hm2;

import java.util.List;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import com.elusivehawk.mcmods.hm2.api.IHMMechStorage;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMWailaProvider implements IWailaDataProvider
{
	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return null;
	}
	
	@Override
	public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return currenttip;
	}
	
	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		TileEntity te = accessor.getTileEntity();
		
		if (te instanceof IHMMechStorage)
		{
			IHMMechStorage ms = (IHMMechStorage)te;
			
			currenttip.add(StatCollector.translateToLocalFormatted("hm2.waila.storagehud", ms.getStoredMechUnits(), ms.getMaxMechUnits()));
			
		}
		
		return currenttip;
	}
	
	@Override
	public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return currenttip;
	}
	
}
