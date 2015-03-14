
package com.elusivehawk.mcmods.hm2.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMTileRotatable extends HMTileEntity
{
	private final ForgeDirection[] valid;
	
	private ForgeDirection dir = ForgeDirection.UNKNOWN;
	private int dirIndex = 0;
	
	@SuppressWarnings("unqualified-field-access")
	public HMTileRotatable(ForgeDirection initial, ForgeDirection[] validDirs)
	{
		this(validDirs);
		
		dir = initial;
		
	}
	
	@SuppressWarnings("unqualified-field-access")
	public HMTileRotatable(ForgeDirection[] validDirs)
	{
		assert validDirs != null && validDirs.length > 0;
		
		valid = validDirs;
		
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		
		this.dir = ForgeDirection.getOrientation(tag.getByte("direction"));
		
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		
		tag.setByte("direction", (byte)this.dir.ordinal());
		
	}
	
	public ForgeDirection getDirection()
	{
		return this.dir;
	}
	
	public void rotate(boolean reverse)
	{
		this.dir = this.valid[reverse ? this.dirIndex == 0 ? (this.dirIndex = this.valid.length - 1) : this.dirIndex-- : this.dirIndex == this.valid.length ? (this.dirIndex = 0) : this.dirIndex++];
		
	}
	
}
