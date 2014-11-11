
package com.elusivehawk.mcmods.hm1.machine.client;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import com.elusivehawk.mcmods.hm1.HMCore;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMRenderWasher extends TileEntitySpecialRenderer
{
	private final ResourceLocation texture = new ResourceLocation(HMCore.TEXTURE_PATH, "/Washer.png");
	private final HMModelWasher model = new HMModelWasher();
	
	@Override
	public void renderTileEntityAt(TileEntity var1, double var2, double var3, double var4, float var5)
	{
		this.bindTexture(this.texture);
		
		GL11.glPushMatrix();
		GL11.glTranslatef((float) var2 + 0.5F, (float) var3 + 1.5F, (float) var4 + 0.5F);
		
		switch (((IRotatable)var1).getDirection().ordinal())
		{
			case 2: GL11.glRotatef(90, 0.0F, 1.0F, 0.0F); break;
			case 3: GL11.glRotatef(270, 0.0F, 1.0F, 0.0F); break;
			case 4: GL11.glRotatef(180, 0.0F, 1.0F, 0.0F); break;
			case 5: GL11.glRotatef(0, 0.0F, 1.0F, 0.0F); break;
			
		}
		
		GL11.glScalef(1.0F, -1F, -1F);
		
		this.model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		
		GL11.glPopMatrix();
		
	}
	
}
