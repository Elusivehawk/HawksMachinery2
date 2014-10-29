
package com.elusivehawk.mcmods.hm1.machine.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import com.elusivehawk.mcmods.hm1.machine.HMCommonProxyMachine;
import com.elusivehawk.mcmods.hm1.machine.tileentity.HMTileEntityCrusher;
import com.elusivehawk.mcmods.hm1.machine.tileentity.HMTileEntityFisher;
import com.elusivehawk.mcmods.hm1.machine.tileentity.HMTileEntitySinterer;
import com.elusivehawk.mcmods.hm1.machine.tileentity.HMTileEntityStarForge;
import com.elusivehawk.mcmods.hm1.machine.tileentity.HMTileEntityWasher;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
@SideOnly(Side.CLIENT)
public class HMClientProxyMachine extends HMCommonProxyMachine
{
	public static final String[] SUPPORTED_LANGS = new String[]{"en_US"};
	public final int HM_RENDER_ID = RenderingRegistry.getNextAvailableRenderId();
	
	public void registerRenderInformation()
	{
		super.registerRenderInformation();
		
		ClientRegistry.bindTileEntitySpecialRenderer(HMTileEntityCrusher.class, new HMRenderCrusher());
		ClientRegistry.bindTileEntitySpecialRenderer(HMTileEntityWasher.class, new HMRenderWasher());
		ClientRegistry.bindTileEntitySpecialRenderer(HMTileEntityStarForge.class, new HMRenderStarForge());
		ClientRegistry.bindTileEntitySpecialRenderer(HMTileEntitySinterer.class, new HMRenderSinterer());
		
		RenderingRegistry.registerBlockHandler(new HMMachineInvRenderer());
		
	}
	
	public int getHMRenderID()
	{
		return this.HM_RENDER_ID;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		
		if (tileEntity != null)
        {
			switch (ID)
			{
				case 0: return new HMGUICrusher(player.inventory, ((HMTileEntityCrusher)tileEntity));
				case 1: return new HMGUIWasher(player.inventory, ((HMTileEntityWasher)tileEntity));
				//case 2: return new HMGUIEndiumTeleporter(player.inventory, ((HMTileEntityTeleporter)tileEntity));
				case 3: return new HMGUIFisher(player.inventory, (HMTileEntityFisher)tileEntity);
				case 4: return new HMGUIStarForge(player.inventory, (HMTileEntityStarForge)tileEntity);
				case 5: return new HMGUISinterer(player.inventory, (HMTileEntitySinterer)tileEntity);
				default: return null;
				
			}
			
        }
		
		return null;
	}
	
}
