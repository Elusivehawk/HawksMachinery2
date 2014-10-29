
package com.elusivehawk.mcmods.hm1.machine.client;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import com.elusivehawk.mcmods.hm1.HMCore;
import com.elusivehawk.mcmods.hm1.machine.container.HMContainerFisher;
import com.elusivehawk.mcmods.hm1.machine.tileentity.HMTileEntityFisher;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMGUIFisher extends GuiContainer
{
	private HMTileEntityFisher tileEntity;
	
	private int containerWidth;
	private int containerHeight;	
	
	public HMGUIFisher(InventoryPlayer playerInv, HMTileEntityFisher tileEntity)
	{
		super(new HMContainerFisher(playerInv, tileEntity));
		this.tileEntity = tileEntity;
		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		this.fontRenderer.drawString(StatCollector.translateToLocal("tile.HMFisher.name"), 72, 4, 4210752);
		
		this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 110, this.ySize - 94, 4210752);
		
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(this.mc.renderEngine.getTexture(HMCore.GUI_PATH + "/Fisher.png"));
		this.containerWidth = (this.width - this.xSize) / 2;
		this.containerHeight = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(this.containerWidth, this.containerHeight, 0, 0, this.xSize, this.ySize);
		
	}
	
}
