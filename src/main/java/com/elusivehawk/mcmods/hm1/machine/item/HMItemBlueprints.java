
package hawksmachinery.machine.common.item;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import com.elusivehawk.mcmods.hm1.item.HMItem;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMItemBlueprints extends HMItem
{
	public HMItemBlueprints(int id)
	{
		super(id);
		setHasSubtypes(true);
		setContainerItem(this);
		setMaxStackSize(1);
		setTextureFile("/gui/items.png");
		
	}
	
	@Override
	public int getIconFromDamage(int dmg)
	{
		return 188;
	}
	
	@Override
	public EnumRarity getRarity(ItemStack item)
	{
		return item.getItemDamage() == 8 ? EnumRarity.rare : EnumRarity.uncommon;
		
	}
	
	@Override
	public boolean hasEffect(ItemStack item)
	{
		return item.getItemDamage() == 8;
	}
	
	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack item)
	{
		return false;
	}
	
	@Override
	public String getItemNameIS(ItemStack item)
	{
		return "item.HMBlueprint" + item.getItemDamage();
	}
	
}
