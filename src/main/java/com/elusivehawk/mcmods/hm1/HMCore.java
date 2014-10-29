
package com.elusivehawk.mcmods.hm1;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import com.elusivehawk.mcmods.hm1.block.HMBlockEndiumChunkloader;
import com.elusivehawk.mcmods.hm1.block.HMBlockMetalStorage;
import com.elusivehawk.mcmods.hm1.block.HMBlockMulti;
import com.elusivehawk.mcmods.hm1.block.HMBlockOre;
import com.elusivehawk.mcmods.hm1.item.HMItemIngots;
import com.elusivehawk.mcmods.hm1.item.HMItemParts;
import com.elusivehawk.mcmods.hm1.item.HMItemPlating;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
@Mod(modid = "HawksMachinery", name = "Hawk's Core", version = HMCore.VERSION, useMetadata = true)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class HMCore
{
	public static final String VERSION = "Beta v1.1.0";
	
	@SidedProxy(clientSide = "hawksmachinery.core.common.HMClientProxy", serverSide = "hawksmachinery.core.common.HMCommonProxy")
	public static HMCommonProxy PROXY;
	
	@Instance("HawksMachinery")
	private static HMCore INSTANCE;
	
	public CreativeTabs tab = new HMCreativeTab("HMTab");
	
	public static Block ore;
	public static Block metalBlock;
	public static Block endiumChunkloader;
	public static Block technicalBlock;
	
	/**
	 * Parts! 0 - Electric Pistons, 1 - Laser, 2 - Being Redone, 3 - Light Bulb, 4 - Heating Coil, 5 - Electric Magnet, 6 - Engine.
	 */
	public static Item parts;
	public static Item plating;
	public static Item ingots;
	
	public static final String RESOURCES_PATH = "/hawksmachinery/core/client/resources";
	
	public static final String ARMOR_PATH = RESOURCES_PATH + "/armor"; 
	public static final String BLOCK_TEXTURE_FILE = RESOURCES_PATH + "/textures/blocks.png";
	public static final String GUI_PATH = RESOURCES_PATH + "/gui";
	public static final String ITEM_TEXTURE_FILE = RESOURCES_PATH + "/textures/items.png";
	public static final String LANG_PATH = RESOURCES_PATH + "/lang";
	public static final String SOUND_PATH = RESOURCES_PATH + "/sounds";
	public static final String TEXTURE_PATH = RESOURCES_PATH + "/textures";
	
	public static HMCore instance()
	{
		return INSTANCE;
	}
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		INSTANCE = this;
		
		PROXY.loadConfig();
		
		ore = new HMBlockOre(PROXY.getBlockID("Ore", 2551));
		endiumChunkloader = new HMBlockEndiumChunkloader(PROXY.getBlockID("Endium Chunkloader", 2553));
		metalBlock = new HMBlockMetalStorage(PROXY.getBlockID("Metal Block", 2556));
		technicalBlock = new HMBlockMulti(PROXY.getBlockID("Star Forge Technical", 2558));
		
		parts = new HMItemParts(PROXY.getItemID("Parts", 24152)).setMaxDmg(6);
		plating = new HMItemPlating(PROXY.getItemID("Plating", 24154)).setMaxDmg(1);
		ingots = new HMItemIngots(PROXY.getItemID("Ingots", 24157)).setMaxDmg(1);
		
		GameRegistry.registerCraftingHandler(PROXY);
		ForgeChunkManager.setForcedChunkLoadingCallback(this, PROXY);
		MinecraftForge.EVENT_BUS.register(PROXY);
		OreGenerator.addOre(new HMEndiumOreGen());
		OreGenerator.addOre(new HMCobaltOreGen());
		OreDictionary.registerOre("ingotEndium", new ItemStack(ingots));
		OreDictionary.registerOre("ingotCobalt", new ItemStack(ingots, 1, 1));
		
		VillagerRegistry.instance().registerVillageTradeHandler(0, PROXY);
		VillagerRegistry.instance().registerVillageTradeHandler(2, PROXY);
		VillagerRegistry.instance().registerVillageTradeHandler(3, PROXY);
		VillagerRegistry.instance().registerVillageTradeHandler(4, PROXY);
		
	}
	
	@Init
	public void load(FMLInitializationEvent event)
	{
		PROXY.registerRenderInformation();
		new HMUpdateHandler(ModConverter.getMod(this.getClass()));
		
	}
	
	@PostInit
	public void modsLoaded(FMLPostInitializationEvent event)
	{
		
	}
	
	public void loadRecipes()
	{
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(parts, 1, 0), new Object[]{"IBG", "ICP", "IBG", 'I', Item.ingotIron, 'C', "ingotCobalt", 'P', Block.pistonBase, 'B', Item.blazePowder, 'G', Item.ingotGold}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(parts, 1, 1), new Object[]{" B ", "RGR", "CLC", 'B', Item.blazePowder, 'R', Item.redstone, 'G', Block.glass, 'C', "ingotCobalt", 'L', new ItemStack(parts, 1, 3)}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(parts, 6, 2), new Object[]{"C", "C", "C", 'C', "ingotCobalt"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(parts, 1, 3), new Object[]{" G ", "GBG", "CCC", 'G', Block.thinGlass, 'B', Item.blazeRod, 'C', Item.goldNugget}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(parts, 1, 4), new Object[]{"CCC", "BCB", 'C', Block.fenceIron, 'B', Item.blazePowder}));
		GameRegistry.addRecipe(new ItemStack(parts, 1, 5), new Object[]{"ici", 'i', Item.ingotIron, 'c', new ItemStack(parts, 1, 4)});
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(parts, 1, 6), new Object[]{"OOC", "BPg", "OOC", 'O', Block.obsidian, 'C', "ingotCobalt", 'B', Item.blazePowder, 'P', new ItemStack(parts), 'g', Item.goldNugget}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(HMCore.metalBlock, 1, 0), new Object[]{"EEE", "EEE", "EEE", 'E', "ingotEndium"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(HMCore.metalBlock, 1, 1), new Object[]{"CCC", "CCC", "CCC", 'C', "ingotCobalt"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(HMCore.plating, 1, 0), new Object[]{"MM", "MM", 'M', "ingotEndium"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(HMCore.plating, 1, 1), new Object[]{"MM", "MM", 'M', "ingotCobalt"}));
		
		GameRegistry.addShapelessRecipe(new ItemStack(HMCore.ingots, 9, 0), new ItemStack(HMCore.metalBlock, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(HMCore.ingots, 9, 1), new ItemStack(HMCore.metalBlock, 1, 1));
		
		FurnaceRecipes.smelting().addSmelting(HMCore.plating.itemID, 0, new ItemStack(HMCore.ingots, 4, 0), 0.0F);
		FurnaceRecipes.smelting().addSmelting(HMCore.plating.itemID, 1, new ItemStack(HMCore.ingots, 4, 1), 0.0F);
		
	}
	
	public class HMUpdateHandler extends UpdateManagerMod
	{
		public HMUpdateHandler(Mod m)
		{
			super(m);
			
		}
		
		@Override
		public String getModURL()
		{
			return "http://bit.ly/TFrC6z";
		}
		
		@Override
		public String getUpdateURL()
		{
			return "https://dl.dropbox.com/u/100525141/HawksMachineryVersion.txt";
		}
		
		@Override
		public ModType getModType()
		{
			return ModType.ADDON;
		}
		
		@Override
		public String getModName()
		{
			return "Hawk's Machinery";
		}
		
		@Override
		public String getChangelogURL()
		{
			return "https://dl.dropbox.com/u/100525141/HawksMachinery" + VERSION.replace(".", "").replace(" ", "") + "Changelog.txt";
		}
		
		@Override
		public String getDirectDownloadURL()
		{
			return "https://dl.dropbox.com/u/100525141/HawksMachneryDLLink.txt";
		}
		
		@Override
		public String getDisclaimerURL()
		{
			return "https://dl.dropbox.com/u/100525141/HawksMachineryDisclaimer.txt";
		}
		
		@Override
		public boolean enableAutoDownload()
		{
			return PROXY.enableAutoDL;
		}
		
		@Override
		public CheckingMethod getCheckingMethod()
		{
			return CheckingMethod.LEXICOGRAPHICAL;
		}
		
		@Override
		public boolean disableChecks()
		{
			return UpdateManager.isDebug ? true : !PROXY.enableUpdateChecking;
		}
		
		@Override
		public boolean srcOnly()
		{
			return false;
		}
		
		@Override
		public ModReleaseType getReleaseType()
		{
			return ModReleaseType.BETA;
		}
		
		@Override
		public ItemStack getIconStack()
		{
			return new ItemStack(ingots);
		}
		
	}
	
}
