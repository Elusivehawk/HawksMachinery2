
package com.elusivehawk.mcmods.hmu;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMUCommonProxy
{
	public void initiate(){}
	
	public void reportOrDlModUpdate(ModInfo info)
	{
		FMLLog.fine(LanguageRegistry.instance().getStringLocalization("hmu.log.updatefound.server.0", info.mod.getName()));
		FMLLog.fine(LanguageRegistry.instance().getStringLocalization("hmu.log.updatefound.server.1", info.mod.getVersion()));
		FMLLog.fine(LanguageRegistry.instance().getStringLocalization("hmu.log.updatefound.server.2", info.latestVersion));
		FMLLog.fine(LanguageRegistry.instance().getStringLocalization("hmu.log.updatefound.server.3", info.dlUrl.toString()));
		
	}
	
}
