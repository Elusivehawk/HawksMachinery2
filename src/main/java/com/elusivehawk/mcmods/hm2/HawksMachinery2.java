
package com.elusivehawk.mcmods.hm2;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
@Mod(name = "Hawk's Machinery 2", modid = "HM2", version = HawksMachinery2.VERSION, acceptedMinecraftVersions = HawksMachinery2.MCVERSION, dependencies = HawksMachinery2.REQUIREMENTS)
public final class HawksMachinery2
{
	public static final String VERSION = "Alpha v6 KyzerTest";
	public static final String MCVERSION = "1.7.2";
	public static final String REQUIREMENTS = "";
	
	public static final String[] PART_TYPES = {"eye", "spring"};
	
	@Instance("HM2")
	public static HawksMachinery2 instance;
	
	@SidedProxy(modId = "HM2", clientSide = "com.elusivehawk.mcmods.hm2.client.HMClientProxy", serverSide = "com.elusivehawk.mcmods.hm2.HMCommonProxy")
	public static HMCommonProxy proxy;
	
	//private final CreativeTabs blocks = new HMTabBlocks();
	//private final CreativeTabs items = new HMTabItems();
	
	private final HMOreGenerator oregen = new HMOreGenerator();
	private final HMEvents events = new HMEvents();
	private final HMReflectHelper refl = new HMReflectHelper();
	
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
		HMBlocks.instance().initBlocks();
		HMItems.instance().initItems();
		
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
	
	private static final class HMReflectHelper
	{
		private Field fin = null;
		
		void init()
		{
			try
			{
				this.fin = Field.class.getDeclaredField("modifiers");
				
				this.fin.setAccessible(true);
				this.fin.setInt(this.fin, this.fin.getModifiers() & ~Modifier.FINAL);
				
			}
			catch (Exception e){}
			
		}
		
		void replaceBlock(int id, String name, Block rep)
		{
			GameRegistry.registerBlock(rep, ItemBlock.class, name, "minecraft");
			this.reflectField(Blocks.class, name, null, rep);
			
		}
		
		void replaceItem(int id, String name, Item rep)
		{
			GameRegistry.registerItem(rep, name, "minecraft");
			this.reflectField(Items.class, name, null, rep);
			
		}
		
		void reflectObjField(Object target, String field, Object rep)
		{
			assert target != null;
			
			this.reflectField(target.getClass(), field, target, rep);
			
		}
		
		void reflectField(Class<?> cl, String field, Object target, Object rep)
		{
			try
			{
				this.setFinalField(cl.getDeclaredField(field), target, rep);
				
			}
			catch (Exception e){}
			
		}
		
		void setFinalField(Field f, Object target, Object rep)
		{
			try
			{
				if (!f.isAccessible())
				{
					f.setAccessible(true);
					
				}
				
				int tmp = f.getModifiers();
				this.fin.setInt(f, f.getModifiers() & ~Modifier.FINAL);
				f.set(target, rep);
				this.fin.setInt(f, tmp);
				
			}
			catch (Exception e){}
			
		}
		
		void setField(Class<?> cl, String field, Object target, Object rep)
		{
			try
			{
				this.setField(target, cl.getDeclaredField(field), rep);
				
			}
			catch (Exception e){}
			
		}
		
		void setField(Object target, Field f, Object rep)
		{
			try
			{
				f.set(target, rep);
				
			}
			catch (Exception e){}
			
		}
		
		void finish()
		{
			try
			{
				this.fin.setInt(this.fin, this.fin.getModifiers() | Modifier.FINAL);
				
			}
			catch (Exception e){}
			
		}
		
	}
	
}
