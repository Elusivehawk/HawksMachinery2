
package com.elusivehawk.mcmods.hm1.tools;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.item.ItemStack;
import com.elusivehawk.mcmods.hm1.item.HMItem;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMItemWolfTamer extends HMItem
{
	public HMItemWolfTamer(int id)
	{
		super(id);
		
	}
	
	@Override
	public boolean itemInteractionForEntity(ItemStack item, EntityLiving entity)
	{
		if (!entity.worldObj.isRemote && entity instanceof EntityWolf)
		{
			if (((EntityWolf)entity).isAngry())
			{
				((EntityWolf)entity).setAngry(false);
				((EntityWolf)entity).setAttackTarget(null);
				--item.stackSize;
				if (item.stackSize == 0)
					item = null;
				
			}
			
		}
		
		return true;
	}
	
}
