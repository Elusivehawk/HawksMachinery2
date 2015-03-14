
package com.elusivehawk.mcmods.hm2;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import com.elusivehawk.mcmods.hm2.blocks.HMBlocks;
import com.elusivehawk.mcmods.hm2.items.HMItems;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
@Mod(name = "Hawk's Machinery 2", modid = "HM2", version = HawksMachinery2.VERSION, acceptedMinecraftVersions = HawksMachinery2.MCVERSION, dependencies = HawksMachinery2.REQUIREMENTS)
public final class HawksMachinery2
{
	public static final String VERSION = "1.0.0";
	public static final String MCVERSION = "1.7.2";
	public static final String REQUIREMENTS = "";
	
	public static final String[] PART_TYPES = {"eye", "spring"};
	
	public static final ForgeDirection[] MACHINE_DIRS = {ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.WEST, ForgeDirection.EAST};
	
	@Instance("HM2")
	public static HawksMachinery2 instance;
	
	@SidedProxy(modId = "HM2", clientSide = "com.elusivehawk.mcmods.hm2.client.HMClientProxy", serverSide = "com.elusivehawk.mcmods.hm2.HMCommonProxy")
	public static HMCommonProxy proxy;
	
	//private final CreativeTabs blocks = new HMTabBlocks();
	//private final CreativeTabs items = new HMTabItems();
	
	private final HMOreGenerator oregen = new HMOreGenerator();
	private final HMEvents events = new HMEvents();
	
	public static HawksMachinery2 instance()
	{
		return instance;
	}
	
	/*public static CreativeTabs getBlockTab()
	{
		return instance().blocks;
	}
	
	public static CreativeTabs getItemTab()
	{
		return instance().items;
	}*/
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		HMBlocks.initBlocks();
		HMItems.initItems();
		
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		//Proxy
		
		proxy.init();
		
		//Misc
		
		MinecraftForge.ORE_GEN_BUS.register(this.oregen);
		MinecraftForge.EVENT_BUS.register(this.events);
		FMLCommonHandler.instance().bus().register(this.events);
		
		/*Send a message to our buddy over at HMU
		
		NBTTagCompound nbt = new NBTTagCompound();
		
		nbt.setString("versionCheckUrl", "https://www.dropbox.com/user/KH'N/HM2Version.txt");
		nbt.setString("downloadLocation", "https://www.dropbox.com/user/KH'N/HM2DownloadLink.txt");
		
		FMLInterModComms.sendMessage("HMU", null, nbt);
		*/
		
	}
	
	@EventHandler
	public void readMsgs(IMCEvent event)
	{
		
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
		
	}
	
}
