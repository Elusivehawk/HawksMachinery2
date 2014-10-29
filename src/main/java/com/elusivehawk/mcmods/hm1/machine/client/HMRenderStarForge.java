
package com.elusivehawk.mcmods.hm1.machine.client;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import com.elusivehawk.mcmods.hm1.HMCore;

/**
 *
 *
 *
 * @author Elusivehawk
 */
public class HMRenderStarForge extends TileEntitySpecialRenderer
{
	private HMModelStarForge model;
	
	public HMRenderStarForge()
	{
		this.model = new HMModelStarForge();
		
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double par2, double par3, double par4, float par5)
	{
		this.bindTextureByName(HMCore.TEXTURE_PATH + "/StarForge.png");
		GL11.glPushMatrix();
		GL11.glTranslatef((float)par2 + 0.5F, (float)par3 + 1.5F, (float)par4 + 0.5F);
		GL11.glScalef(1.0F, -1F, -1F);
		this.model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GL11.glPopMatrix();
		
	}
	
}
