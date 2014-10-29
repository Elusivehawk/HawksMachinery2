
package com.elusivehawk.mcmods.hm1.machine.item;

import net.minecraft.item.ItemBlock;
import com.elusivehawk.mcmods.hm1.HMCore;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMItemBlockMachine extends ItemBlock
{
	public HMItemBlockMachine(int id)
	{
		super(id);
		setTextureFile(HMCore.instance().ITEM_TEXTURE_FILE);
		
	}
	
	@Override
	public int getMetadata(int meta)
    {
        return meta;
    }
	
}
