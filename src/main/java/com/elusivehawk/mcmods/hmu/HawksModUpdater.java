
package com.elusivehawk.mcmods.hmu;

import java.net.URL;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCMessage;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
@Mod(name = "Hawk's Mod Updater", modid = "HMU", version = HawksModUpdater.VERSION, acceptedMinecraftVersions = HawksModUpdater.MCVERSION)
public class HawksModUpdater
{
	public static final String VERSION = "1.0.0";
	public static final String MCVERSION = "1.7.2";
	
	@Instance("HMU")
	public static HawksModUpdater instance;
	
	@SidedProxy(modId = "HMU", clientSide = "com.elusivehawk.mcmods.hmu.HMUClientProxy", serverSide = "com.elusivehawk.mcmods.hmu.HMUCommonProxy")
	public static HMUCommonProxy proxy;
	
	private ThreadUpdateMods updThread = null;
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		this.updThread = new ThreadUpdateMods();
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		this.updThread.start();
		
	}
	
	@EventHandler
	public void readMsgs(IMCEvent event)
	{
		for (IMCMessage msg : event.getMessages())
		{
			if (!msg.isNBTMessage())
			{
				continue;
			}
			
			NBTTagCompound nbt = msg.getNBTValue();
			
			String versionLocation = nbt.getString("versionCheckUrl");
			String dlLoc = nbt.getString("downloadLocation");
			
			if ("".equals(versionLocation))
			{
				continue;
			}
			
			if ("".equals(dlLoc))
			{
				dlLoc = null;
				
			}
			
			URL versionUrl = null;
			URL dlUrl = null;
			
			try
			{
				versionUrl = new URL(versionLocation);
				
			}
			catch (Exception e)
			{
				continue;
			}
			
			try
			{
				dlUrl = new URL(dlLoc);
				
			}
			catch (Exception e){}
			
			ModInfo info = new ModInfo();
			
			info.mod = FMLCommonHandler.instance().findContainerFor(msg.getSender());
			info.versionUrl = versionUrl;
			info.dlUrl = dlUrl;
			
			this.updThread.addModInfo(info);
			
		}
		
	}
	
	public void onModUpdateDetected(ModInfo info)
	{
		assert info.latestVersion != null;
		assert info.versionUrl == null;
		
		proxy.reportOrDlModUpdate(info);
		
	}
	
	public static HawksModUpdater instance()
	{
		return instance;
	}
	
}
