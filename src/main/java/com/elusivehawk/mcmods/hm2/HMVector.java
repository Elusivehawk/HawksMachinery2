
package com.elusivehawk.mcmods.hm2;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMVector
{
	public final World worldObj;
	
	public int xCoord, yCoord, zCoord;
	
	public HMVector(TileEntity tileEntity)
	{
		this(tileEntity.getWorldObj(), tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
		
	}
	
	public HMVector(NBTTagCompound tag)
	{
		this(DimensionManager.getWorld(tag.getInteger("Dim")), tag.getInteger("xCoord"), tag.getInteger("yCoord"), tag.getInteger("zCoord"));
		
	}
	
	@SuppressWarnings("unqualified-field-access")
	public HMVector(World world, int x, int y, int z)
	{
		worldObj = world;
		xCoord = x;
		yCoord = y;
		zCoord = z;
		
	}
	
	public HMVector(HMVector oldVec)
	{
		this(oldVec.worldObj, oldVec.xCoord, oldVec.yCoord, oldVec.zCoord);
		
	}
	
	public boolean setBlockId(Block block)
	{
		return this.setBlockWithMeta(block, 0);
	}
	
	public boolean setBlockIdWithDir(Block block, ForgeDirection dir)
	{
		return this.setBlockWithMetaPlusDir(block, 0, dir);
	}
	
	public boolean setBlockWithMeta(Block block, int meta)
	{
		return this.setBlockWithMetaPlusDir(block, meta, ForgeDirection.UNKNOWN);
	}
	
	public boolean setBlockWithMetaPlusDir(Block block, int meta, ForgeDirection dir)
	{
		return this.worldObj.setBlock(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ, block, meta, 3);
	}
	
	public Block getBlockId()
	{
		return this.getBlockWithDir(ForgeDirection.UNKNOWN);
	}
	
	public Block getBlockWithDir(ForgeDirection dir)
	{
		return this.worldObj.getBlock(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ);
	}
	
	public Block getBlock()
	{
		return this.getBlockWithDir(ForgeDirection.UNKNOWN);
	}
	
	public boolean setMeta(int meta)
	{
		return this.setMetaWithDir(meta, ForgeDirection.UNKNOWN);
	}
	
	public boolean setMetaWithDir(int meta, ForgeDirection dir)
	{
		return this.setBlockWithMetaPlusDir(this.getBlockId(), meta, dir);
	}
	
	public int getMetadata()
	{
		return this.getMetadataWithDir(ForgeDirection.UNKNOWN);
	}
	
	public int getMetadataWithDir(ForgeDirection dir)
	{
		return this.worldObj.getBlockMetadata(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ);
	}
	
	public TileEntity getTileEntity()
	{
		return this.getTileEntityWithDir(ForgeDirection.UNKNOWN);
	}
	
	public TileEntity getTileEntityWithDir(ForgeDirection dir)
	{
		return this.worldObj.getTileEntity(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ);
	}
	
	public void setLightValue(EnumSkyBlock lightType, int value)
	{
		this.setLightValueWithDir(lightType, value, ForgeDirection.UNKNOWN);
	}
	
	public void setLightValueWithDir(EnumSkyBlock lightType, int value, ForgeDirection dir)
	{
		this.worldObj.setLightValue(lightType, this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ, value);
	}
	
	public int getLightValue()
	{
		return this.getLightValueWithDir(ForgeDirection.UNKNOWN);
	}
	
	public int getLightValueWithDir(ForgeDirection dir)
	{
		return this.worldObj.getBlockLightValue(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ);
	}
	
	public int getDim()
	{
		return this.worldObj.provider.dimensionId;
	}
	
	public int getRedstonePower()
	{
		return this.getRedstonePower(ForgeDirection.UNKNOWN);
	}
	
	public int getRedstonePower(ForgeDirection dir)
	{
		return this.worldObj.getIndirectPowerLevelTo(this.xCoord, this.yCoord, this.zCoord, dir.ordinal());
	}
	
	public boolean isGettingIndirectlyRedstoned()
	{
		return this.isGettingIndirectlyRedstoneWithDir(ForgeDirection.UNKNOWN);
	}
	
	public boolean isGettingIndirectlyRedstoneWithDir(ForgeDirection dir)
	{
		return this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ);
	}
	
	public boolean canSeeTheSky()
	{
		return this.canSeeTheSkyWithDir(ForgeDirection.UNKNOWN);
	}
	
	public boolean canSeeTheSkyWithDir(ForgeDirection dir)
	{
		return this.worldObj.canBlockSeeTheSky(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ);
	}
	
	public boolean isDaytime()
	{
		return this.worldObj.isDaytime();
	}
	
	public void markBlockForUpdate()
	{
		this.markNeighborBlockForUpdate(ForgeDirection.UNKNOWN);
		
	}
	
	public void markNeighborBlockForUpdate(ForgeDirection dir)
	{
		this.worldObj.markBlockForUpdate(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ);
		
	}
	
	public void updateNeighboringBlocks()
	{
		this.updateNeighboringBlocksWithDir(ForgeDirection.UNKNOWN);
		
	}
	
	public void updateNeighboringBlocksWithDir(ForgeDirection dir)
	{
		this.worldObj.notifyBlocksOfNeighborChange(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ, this.getBlockWithDir(dir));
		
	}
	
	public void explode(float str, Entity entity, boolean idk)
	{
		this.worldObj.createExplosion(entity, this.xCoord, this.yCoord, this.zCoord, str, idk);
	}
	
	public EntityPlayer getPlayerInWorld(String name)
	{
		if (!this.worldObj.playerEntities.isEmpty())
		{
			for (EntityPlayer player : (ArrayList<EntityPlayer>)this.worldObj.playerEntities)
			{
				if (player.getCommandSenderName().equalsIgnoreCase(name))
				{
					return player;
				}
				
			}
			
		}
		
		return null;
	}
	
	public NBTTagCompound writeToNBTTag(NBTTagCompound tag)
	{
		tag.setInteger("xCoord", this.xCoord);
		tag.setInteger("yCoord", this.yCoord);
		tag.setInteger("zCoord", this.zCoord);
		tag.setInteger("Dim", this.worldObj.provider.dimensionId);
		return tag;
	}
	
	public HMVector modifyFromDir(ForgeDirection dir)
	{
		return this.modifyFromDir(dir, 1);
	}
	
	public HMVector modifyFromDir(ForgeDirection dir, int amount)
	{
		this.xCoord += (dir.offsetX * amount);
		this.yCoord += (dir.offsetY * amount);
		this.zCoord += (dir.offsetZ * amount);
		return this;
	}
	
	public HMVector navigatePath(ArrayList<ForgeDirection> directions, int[] amount)
	{
		for (int counter = 0; counter < directions.size(); ++counter)
		{
			this.modifyFromDir(directions.get(counter), amount[counter]);
			
		}
		
		return this;
	}
	
	public HMVector reset(TileEntity tile)
	{
		this.xCoord = tile.xCoord;
		this.yCoord = tile.yCoord;
		this.zCoord = tile.zCoord;
		return this;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (obj != null)
		{
			if (obj instanceof HMVector)
			{
				return ((HMVector)obj).getDim() == this.getDim() && ((HMVector)obj).xCoord == this.xCoord && ((HMVector)obj).yCoord == this.yCoord && ((HMVector)obj).zCoord == this.zCoord;
			}
			
		}
		
		return false;
	}
	
	@Override
	public HMVector clone()
	{
		return new HMVector(this);
	}
	
}
