
package com.elusivehawk.mcmods.hm2.client;

import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public interface IHMBlockSpecialRender
{
	@SideOnly(Side.CLIENT)
	public void renderWorld(RenderBlocks rb, Tessellator t, IBlockAccess w, int x, int y, int z);
	
	@SideOnly(Side.CLIENT)
	public void renderInv(int meta, RenderBlocks rb, Tessellator t);
	
}
