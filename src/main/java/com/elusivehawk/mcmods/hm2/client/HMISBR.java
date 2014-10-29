
package com.elusivehawk.mcmods.hm2.client;

import org.lwjgl.opengl.GL11;
import com.elusivehawk.mcmods.hm2.HawksMachinery2;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMISBR implements ISimpleBlockRenderingHandler
{
	public HMISBR(){}
	
	@Override
	public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks r)
	{
		if (!(block instanceof IHMBlockSpecialRender))
		{
			return;
		}
		
		((IHMBlockSpecialRender)block).renderInv(meta, r, Tessellator.instance);
		
		/*for (float[] box : m)
		{
			r.setRenderBounds(box[0], box[1], box[2], box[3], box[4], box[5]);
			
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			t.startDrawingQuads();
			t.setNormal(0.0F, -1.0F, 0.0F);
			r.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, r.getBlockIconFromSideAndMetadata(block, 0, meta));
			t.draw();
			t.startDrawingQuads();
			t.setNormal(0.0F, 1.0F, 0.0F);
			r.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, r.getBlockIconFromSideAndMetadata(block, 1, meta));
			t.draw();
			t.startDrawingQuads();
			t.setNormal(0.0F, 0.0F, -1.0F);
			r.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, r.getBlockIconFromSideAndMetadata(block, 2, meta));
			t.draw();
			t.startDrawingQuads();
			t.setNormal(0.0F, 0.0F, 1.0F);
			r.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, r.getBlockIconFromSideAndMetadata(block, 3, meta));
			t.draw();
			t.startDrawingQuads();
			t.setNormal(-1.0F, 0.0F, 0.0F);
			r.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, r.getBlockIconFromSideAndMetadata(block, 4, meta));
			t.draw();
			t.startDrawingQuads();
			t.setNormal(1.0F, 0.0F, 0.0F);
			r.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, r.getBlockIconFromSideAndMetadata(block, 5, meta));
			t.draw();
			
		}*/
		
	}
	
	@Override
	public boolean renderWorldBlock(IBlockAccess w, int x, int y, int z, Block block, int modelId, RenderBlocks r)
	{
		if (!(block instanceof IHMBlockSpecialRender))
		{
			return false;
		}
		
		((IHMBlockSpecialRender)block).renderWorld(r, Tessellator.instance, w, x, y, z);
		
		return true;
	}
	
	@Override
	public boolean shouldRender3DInInventory(int modelId)
	{
		return modelId == HawksMachinery2.proxy.getHMRenderId(true);
	}
	
	@Override
	public int getRenderId()
	{
		return 0;
	}
	
}
