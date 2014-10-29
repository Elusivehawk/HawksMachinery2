
package com.elusivehawk.mcmods.hm1.redstone.client;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMRenderRedWire extends TileEntitySpecialRenderer
{
	private HMModelRedWire model;
	
	public HMRenderRedWire()
	{
		this.model = new HMModelRedWire();
		
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double var2, double var4, double var6, float var8)
	{
		
		
	}
	
}
