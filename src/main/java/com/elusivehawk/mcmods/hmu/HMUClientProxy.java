
package com.elusivehawk.mcmods.hmu;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
@SideOnly(Side.CLIENT)
public class HMUClientProxy extends HMUCommonProxy
{
	private List<ModInfo> mods = new ArrayList<ModInfo>();
	private boolean hasReportedModUpdates = false;
	
	@Override
	public void initiate()
	{
		MinecraftForge.EVENT_BUS.register(this);
		
	}
	
	@Override
	public void reportOrDlModUpdate(ModInfo info)
	{
		this.mods.add(info);
		
	}
	
	@SubscribeEvent
	public void onWorldLoad(WorldEvent.Load event)
	{
		if (!this.hasReportedModUpdates)
		{
			this.hasReportedModUpdates = true;
			
			for (ModInfo info : this.mods)
			{
				super.reportOrDlModUpdate(info);//TODO Consider removing after mod DLing is implemented
				Minecraft.getMinecraft().thePlayer.sendChatMessage(String.format(LanguageRegistry.instance().getStringLocalization("hmu.log.updatefound.client"), info.mod.getName(), info.latestVersion, info.dlUrl.toString()));
				
			}
			
		}
		
	}
	
}
