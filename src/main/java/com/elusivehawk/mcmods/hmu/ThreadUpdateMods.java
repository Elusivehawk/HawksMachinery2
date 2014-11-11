
package com.elusivehawk.mcmods.hmu;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.google.common.io.ByteStreams;
import cpw.mods.fml.common.Loader;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class ThreadUpdateMods extends Thread
{
	private final List<ModInfo> mods;
	
	public ThreadUpdateMods()
	{
		mods = new ArrayList<ModInfo>(Loader.instance().getModList().size());
		
	}
	
	@Override
	public void run()
	{
		if (this.mods.isEmpty())
		{
			return;
		}
		
		Iterator<ModInfo> itr = this.mods.iterator();
		ModInfo info = null;
		InputStream in = null;
		
		List<ModInfo> updates = new ArrayList<ModInfo>();
		
		while (itr.hasNext())
		{
			info = itr.next();
			
			try
			{
				in = info.versionUrl.openStream();
				String version = new String(ByteStreams.toByteArray(in));
				
				if (!info.mod.getVersion().equalsIgnoreCase(version))
				{
					ModInfo mi = new ModInfo();
					
					mi.mod = info.mod;
					mi.latestVersion = version;
					mi.dlUrl = info.dlUrl;
					
					HawksModUpdater.instance().onModUpdateDetected(mi);
					
				}
				
			}
			catch (Exception e){}
			
		}
		
	}
	
	public void addModInfo(ModInfo info)
	{
		this.mods.add(info);
		
	}
	
}
