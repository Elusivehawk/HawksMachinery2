
package com.elusivehawk.mcmods.hm1.redstone;

import net.minecraft.block.Block;
import com.elusivehawk.mcmods.hm1.HMCore;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
@Mod(modid = "HMRedstone", name = "Hawk's Redstone", version = HMCore.VERSION, useMetadata = true, dependencies = "after:HMCore")
@NetworkMod(channels = {"HMRedstone"}, clientSideRequired = true, serverSideRequired = false)
public class HMRedstone
{
	@Instance("HMRedstone")
	private static HMRedstone INSTANCE;
	
	public static Block blockDetector;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		INSTANCE = this;
		
		blockDetector = new HMBlockTileSensor(HMCore.PROXY.getBlockID("Block Detector", 2560));
		
		GameRegistry.registerBlock(blockDetector);
		
	}
	
	@Init
	public void load(FMLInitializationEvent event)
	{
		
		
	}
	
	@PostInit
	public void modsLoaded(FMLPostInitializationEvent event)
	{
		
		
	}
	
}
