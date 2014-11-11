
package com.elusivehawk.mcmods.hm2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.event.terraingen.OreGenEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMOreGenerator
{
	public static final class OreGenEntry
	{
		public final Block block, stone;
		public final int meta, veinSize, height, veinsPerChunk, dimension;
		public final List<BiomeGenBase> whitelist;
		
		public OreGenEntry(Block b, int dmg, int size, int altitude, int tries, int dim, Block replace, BiomeGenBase... biomes)
		{
			assert b != null;
			assert replace != null;
			
			block = b;
			stone = replace;
			
			meta = dmg;
			veinSize = size;
			height = altitude;
			veinsPerChunk = tries;
			dimension = dim;
			
			whitelist = Arrays.asList(biomes);
			
		}
		
	}

	private final Map<OreGenEntry, WorldGenMinable> entries = new HashMap<OreGenEntry, WorldGenMinable>();
	
	public void addOre(OreGenEntry entry)
	{
		this.entries.put(entry, new WorldGenMinable(entry.block, entry.meta, entry.veinSize, entry.stone));
		
	}
	
	@SubscribeEvent
	public void onOresGenerated(OreGenEvent.Post event)
	{
		for (Entry<OreGenEntry, WorldGenMinable> mapEntry : this.entries.entrySet())
		{
			OreGenEntry entry = mapEntry.getKey();
			
			if (entry.dimension == event.world.provider.dimensionId)
			{
				Random rng = event.rand;
				int x;
				int z;
				
				for (int c = 0; c < entry.veinsPerChunk; c++)
				{
					x = event.worldX + rng.nextInt(16);
					z = event.worldZ + rng.nextInt(16);
					
					if (entry.whitelist.isEmpty() || entry.whitelist.contains(event.world.getBiomeGenForCoords(x, z)))
					{
						mapEntry.getValue().generate(event.world, rng, x, rng.nextInt(entry.height), z);
						
					}
					
				}
				
			}
			
		}
		
	}
	
}
