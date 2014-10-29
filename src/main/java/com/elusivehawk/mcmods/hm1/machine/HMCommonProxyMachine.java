
package com.elusivehawk.mcmods.hm1.machine;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import com.elusivehawk.mcmods.hm1.machine.container.HMContainerCrusher;
import com.elusivehawk.mcmods.hm1.machine.container.HMContainerFisher;
import com.elusivehawk.mcmods.hm1.machine.container.HMContainerSinterer;
import com.elusivehawk.mcmods.hm1.machine.container.HMContainerStarForge;
import com.elusivehawk.mcmods.hm1.machine.container.HMContainerWasher;
import com.elusivehawk.mcmods.hm1.machine.tileentity.HMTileEntityCrusher;
import com.elusivehawk.mcmods.hm1.machine.tileentity.HMTileEntityFisher;
import com.elusivehawk.mcmods.hm1.machine.tileentity.HMTileEntitySinterer;
import com.elusivehawk.mcmods.hm1.machine.tileentity.HMTileEntityStarForge;
import com.elusivehawk.mcmods.hm1.machine.tileentity.HMTileEntityWasher;
import cpw.mods.fml.common.network.IGuiHandler;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMCommonProxyMachine implements IGuiHandler
{
	public void registerRenderInformation(){}
	
	public int getHMRenderID()
	{
		return 0;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}
	
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) 
	{
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		
		switch (id)
		{
			case 0: return new HMContainerCrusher(player.inventory, (HMTileEntityCrusher)tileEntity);
			case 1: return new HMContainerWasher(player.inventory, (HMTileEntityWasher)tileEntity);
			//case 2: return new HMContainerTeleporter(player.inventory);
			case 3: return new HMContainerFisher(player.inventory, (HMTileEntityFisher)tileEntity);
			case 4: return new HMContainerStarForge(player.inventory, (HMTileEntityStarForge)tileEntity);
			case 5: return new HMContainerSinterer(player.inventory, (HMTileEntitySinterer)tileEntity);
			default: return null;
			
		}
		
	}
	
}
