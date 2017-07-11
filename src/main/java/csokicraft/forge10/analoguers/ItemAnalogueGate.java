package csokicraft.forge10.analoguers;

import java.util.*;
import java.util.Map.Entry;
import java.util.function.Function;

import csokicraft.forge10.analoguers.AnalogueGateRegistry.IRegistryListener;
import csokicraft.forge10.analoguers.AnalogueRS;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.*;
import pl.asie.charset.gates.ItemGate;
import pl.asie.charset.gates.PartGate;

@Deprecated
public class ItemAnalogueGate extends ItemGate{
	public ItemAnalogueGate() {
		setHasSubtypes(true);
		setCreativeTab(AnalogueRS.tab);
	}
	
	/*@Override
	public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> l){
		AnalogueRS.registry.forEach(new IRegistryListener(){
			@Override
			public Object onElement(int id, String name, Class<? extends PartGate> cls, Object obj){
				List<ItemStack> l = (List<ItemStack>) obj;
				l.add(new ItemStack(AnalogueRS.gateItem, 1, id));
				return l;
			}
		}, l);
	}*/
}
