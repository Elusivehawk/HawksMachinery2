
package com.elusivehawk.mcmods.hm2;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import com.google.common.collect.Lists;
import cpw.mods.fml.common.network.IGuiHandler;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMCommonProxy implements IGuiHandler
{
	protected final List<IHMServerGuiHandler> containerFactories = Lists.newArrayList();
	
	public void init()
	{
		
		
	}
	
	public int getHMRenderId(boolean is3d)
	{
		return 0;
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World w, int x, int y, int z)
	{
		IHMServerGuiHandler h = this.containerFactories.get(ID);
		
		if (h == null)
		{
			return null;
		}
		
		return h.createContainer(player, w, x, y, z);
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World w, int x, int y, int z)
	{
		return null;
	}
	
}
