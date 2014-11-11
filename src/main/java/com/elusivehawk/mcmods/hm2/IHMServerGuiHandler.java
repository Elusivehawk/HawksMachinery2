
package com.elusivehawk.mcmods.hm2;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public interface IHMServerGuiHandler
{
	public Container createContainer(EntityPlayer player, World w, int x, int y, int z);
	
}
