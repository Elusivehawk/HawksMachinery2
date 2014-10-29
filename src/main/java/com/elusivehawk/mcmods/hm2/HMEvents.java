
package com.elusivehawk.mcmods.hm2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockOre;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.ZombieEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.event.world.BlockEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public final class HMEvents
{
	private final Random rng = new Random();
	
	HMEvents()
	{
		
	}
	
	@SubscribeEvent
	public void onItemCrafted(PlayerEvent.ItemCraftedEvent event)
	{
		
		
	}
	
	@SubscribeEvent
	public void onBlockBroken(BlockEvent.BreakEvent event)
	{
		
		
	}
	
	@SubscribeEvent
	public void onBlockHarvested(BlockEvent.HarvestDropsEvent event)
	{
		
		
	}
	
	@SubscribeEvent
	public void onPlayerUseItem(PlayerUseItemEvent.Start event)
	{
		
		
	}
	
	@SubscribeEvent
	public void onEntityHurt(LivingAttackEvent event)
	{
		
		
	}
	
	@SubscribeEvent
	public void onEntitySpawned(EntityJoinWorldEvent event)
	{
		
		
	}
	
	@SubscribeEvent
	public void onEntityKilled(LivingDropsEvent event)
	{
		
		
	}
	
}
