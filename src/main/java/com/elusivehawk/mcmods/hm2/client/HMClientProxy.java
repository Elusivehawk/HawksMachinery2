
package com.elusivehawk.mcmods.hm2.client;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import com.elusivehawk.mcmods.hm2.HMCommonProxy;
import com.elusivehawk.mcmods.hm2.IHMServerGuiHandler;
import com.google.common.collect.Lists;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
@SideOnly(Side.CLIENT)
public class HMClientProxy extends HMCommonProxy
{
	protected final List<IHMClientGuiHandler> guiFactories = Lists.newArrayList();
	private int renderId = 0, renderIdNo3d = 0;
	
	@Override
	public void init()
	{
		super.init();
		
		this.renderId = RenderingRegistry.getNextAvailableRenderId();
		this.renderIdNo3d = RenderingRegistry.getNextAvailableRenderId();
		
		HMISBR isbr = new HMISBR();
		
		RenderingRegistry.registerBlockHandler(this.renderId, isbr);
		RenderingRegistry.registerBlockHandler(this.renderIdNo3d, isbr);
		
	}
	
	@Override
	public int getHMRenderId(boolean is3d)
	{
		return is3d ? this.renderId : this.renderIdNo3d;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World w, int x, int y, int z)
	{
		IHMClientGuiHandler h = this.guiFactories.get(ID);
		
		if (h == null)
		{
			return null;
		}
		
		return h.createGui(player, w, x, y, z);
	}
	
}
