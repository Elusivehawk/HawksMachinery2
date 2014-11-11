
package com.elusivehawk.mcmods.hm2.client;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public interface IHMClientGuiHandler
{
	public GuiScreen createGui(EntityPlayer player, World w, int x, int y, int z);
	
}
