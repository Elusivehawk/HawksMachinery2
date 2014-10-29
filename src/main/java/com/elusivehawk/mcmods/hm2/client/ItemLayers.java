
package com.elusivehawk.mcmods.hm2.client;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
@SideOnly(Side.CLIENT)
public final class ItemLayers
{
	private final Map<Integer, Layer[]> info = new HashMap<Integer, Layer[]>();
	
	public ItemLayers(int layerCount)
	{
		this(layerCount, 0);
		
	}
	
	public ItemLayers(int layerCount, int... metas)
	{
		for (int m : metas)
		{
			info.put(m, new Layer[layerCount]);
			
		}
		
	}
	
	public void addLayer(IIcon icon)
	{
		this.addLayer(0, icon);
		
	}
	
	public void addLayer(IIcon icon, int color)
	{
		this.addLayer(0, icon, color);
		
	}
	
	public void addLayer(int meta, IIcon icon)
	{
		this.addLayer(meta, icon, 0xFFFFFF);
		
	}
	
	public void addLayer(int meta, IIcon icon, int color)
	{
		Layer[] l = this.info.get(meta);
		
		if (l == null)
		{
			return;
		}
		
		for (int c = 0; c < l.length; c++)
		{
			if (l[c] == null)
			{
				l[c] = new Layer(icon, color);
				break;
			}
			
		}
		
	}
	
	public IIcon getIcon(int meta, int layer)
	{
		return this.getLayerSafe(meta, layer).icon;
	}
	
	public int getColor(int meta, int layer)
	{
		return this.getLayerSafe(meta, layer).color;
	}
	
	private Layer getLayerSafe(int meta, int layer)
	{
		Layer[] layers = this.info.get(meta);
		
		if (layers == null)
		{
			return new Layer(null, 0xFFFFFF);
		}
		
		return layers[layer];
	}
	
	private static class Layer
	{
		public final IIcon icon;
		public final int color;
		
		public Layer(IIcon i, int c)
		{
			icon = i;
			color = c;
			
		}
		
	}
	
}
