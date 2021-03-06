
package com.elusivehawk.mcmods.hm1.machine.client;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import com.elusivehawk.mcmods.hm1.HMCore;
import com.elusivehawk.mcmods.hm1.machine.container.HMContainerSinterer;
import com.elusivehawk.mcmods.hm1.machine.tileentity.HMTileEntitySinterer;

/**
 *
 *
 *
 * @author Elusivehawk
 */
public class HMGUISinterer extends GuiContainer
{
	private HMTileEntitySinterer tileEntity;
	
	private int containerWidth;
	private int containerHeight;
	
	public HMGUISinterer(InventoryPlayer playerInv, HMTileEntitySinterer tileEntity)
	{
		super(new HMContainerSinterer(playerInv, tileEntity));
		this.tileEntity = tileEntity;
		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		this.fontRenderer.drawString(StatCollector.translateToLocal("tile.HMSinterer.name"), 65, 6, 4210752);
		this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
		
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(this.mc.renderEngine.getTexture(HMCore.GUI_PATH + "/Sinterer.png"));
		this.containerWidth = (this.width - this.xSize) / 2;
		this.containerHeight = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(this.containerWidth, this.containerHeight, 0, 0, this.xSize, this.ySize);
		
		if (this.tileEntity.workTicks > 0)
		{
			int scale = this.tileEntity.workTicks * 18 / this.tileEntity.TICKS_REQUIRED;
			this.drawTexturedModalRect(this.containerWidth + 73, this.containerHeight + 35, 176, 0, (16 - scale) * 2, 36);
			
		}
		
	}
	
}
