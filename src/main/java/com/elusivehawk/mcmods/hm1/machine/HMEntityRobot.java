
package com.elusivehawk.mcmods.hm1.machine;

import java.util.HashMap;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;
import com.elusivehawk.mcmods.hm1.machine.api.IHMLogoInterpreter;
import com.elusivehawk.mcmods.hm1.machine.api.IHMLogoWord;
import com.elusivehawk.mcmods.hm1.machine.api.IHMRobot;
import com.elusivehawk.mcmods.hm1.machine.logo.HMLogoInterpreter;
import com.elusivehawk.mcmods.hm1.machine.logo.HMLogoWordDictionary;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMEntityRobot extends EntityLiving implements IHMRobot
{
	private HMLogoWordDictionary dictionary = new HMLogoWordDictionary();
	private HashMap<String, Integer> integers = new HashMap<String, Integer>();
	private IHMLogoInterpreter interpreter = new HMLogoInterpreter(this);
	
	public HMEntityRobot(World world)
	{
		super(world);
		
	}
	
	@Override
	public int getMaxHealth()
	{
		return 50;
	}
	
	@Override
    protected boolean canDespawn()
    {
    	return false;
    }
	
	@Override
	public IHMLogoWord getHandlerForWord(String word)
	{
		return this.dictionary.getHandlerForWord(word);
	}
	
	@Override
	public IHMLogoInterpreter getInterpreter()
	{
		return this.interpreter;
	}
	
	@Override
	public void setInterpreter(IHMLogoInterpreter interpreter)
	{
		this.interpreter = interpreter;
		
	}
	
	@Override
	public int getInteger(String intName)
	{
		return this.integers.get(intName);
	}
	
	@Override
	public void setInteger(String intName, int integer)
	{
		this.integers.put(intName, integer);
		
	}
	
}
