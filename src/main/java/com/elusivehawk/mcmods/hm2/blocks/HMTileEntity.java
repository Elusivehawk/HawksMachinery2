
package com.elusivehawk.mcmods.hm2.blocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.common.FMLCommonHandler;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public abstract class HMTileEntity extends TileEntity
{
	protected boolean hasInitiated = false;
	
	public abstract void initiate();
	
	public abstract void update();
	
	public boolean onRightClick(EntityPlayer player, ForgeDirection s, float hitX, float hitY, float hitZ)
	{
		return false;
	}
	
	@Override
	public final void updateEntity()
	{
		if (this.hasInitiated)
		{
			this.update();
			
		}
		else
		{
			this.initiate();
			this.hasInitiated = true;
			
		}
		
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		
		if (this.canUpdate())
		{
			this.hasInitiated = nbt.getBoolean("initiated");
			
		}
		
		this.readExtraDataFromNBT(nbt);
		
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		
		if (this.canUpdate())
		{
			nbt.setBoolean("initiated", this.hasInitiated);
			
		}
		
		this.writeExtraDataToNBT(nbt);
		
	}
	
	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 0, nbt);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	{
		this.readFromNBT(pkt.func_148857_g());
		
	}
	
	public void readExtraDataFromNBT(NBTTagCompound nbt){}
	
	public void writeExtraDataToNBT(NBTTagCompound nbt){}
	
	protected float getTransmissionDistance()
	{
		return 128f;
	}
	
	protected void transmitUpdateToClients()
	{
		if (!this.worldObj.isRemote)
		{
			FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().sendToAllNear(this.xCoord, this.yCoord, this.zCoord, this.getTransmissionDistance(), this.worldObj.provider.dimensionId, this.getDescriptionPacket());
			
		}
		
	}
	
}
