package csokicraft.forge10.analoguers;

import java.util.*;
import java.util.Map.Entry;

import csokicraft.forge10.analoguers.gate.*;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.*;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.registry.GameRegistry;
import pl.asie.charset.gates.*;
import pl.asie.charset.lib.ModCharsetLib;

@Mod(modid = AnalogueRS.MODID, version = AnalogueRS.VERSION, dependencies="required-after:CharsetGates@0.3.0-pre15")
public class AnalogueRS
{
    public static final String MODID = "analoguers";
    public static final String VERSION = "1.1";
    //public static AnalogueGateRegistry registry = new AnalogueGateRegistry();
    public static CreativeTabs tab = new CreativeTabs("AnalogueRS"){
		@Override
		public Item getTabIconItem() {
			return Items.COMPARATOR;
		}
	};
	@Instance
	public static AnalogueRS inst;
	@SidedProxy(clientSide="csokicraft.forge10.analoguers.ClientProxy", serverSide="csokicraft.forge10.analoguers.CommonProxy")
	public static CommonProxy proxy;
    
    public static Item synthTuner = new ItemSynthTuner().setUnlocalizedName("synthTuner").setRegistryName("synthTuner").setCreativeTab(tab);
    
    protected Configuration conf;

    @Instance("CharsetGates")
    private ModCharsetGates mcg;
    
	@EventHandler
	public void preinit(FMLPreInitializationEvent evt){
		conf = new Configuration(evt.getSuggestedConfigurationFile());
		conf.load();

		GameRegistry.register(synthTuner);
		GameRegistry.addShapedRecipe(new ItemStack(synthTuner),
				"rqr", "qsq", "rqr",
				'q', Items.QUARTZ,
				'r', Items.REDSTONE,
				's', Items.STICK);

    	/** default ID of the first gate */
    	int defID = 32;
    	ConfigCategory cat = conf.getCategory("gateIDs");
    	
    	//registering gates
    	Map<String, Class<? extends PartGate>> classes = new LinkedHashMap<>();
    	classes.put("inverter", GateAnalogueInverter.class);
    	classes.put("adder", GateAnalogueAdder.class);
    	classes.put("equality", GateAnalogueEqualityChecker.class);
    	classes.put("synth", GateAnalogueSignalSynth.class);
    	
        for(String name:classes.keySet()){
        	int id = conf.getInt(name, "gateIDs", defID, 10, 64, "Gate ID of "+name);
        	
        	//registry.registerGate(name, classes.get(name), id);
        	mcg.registerGate("analoguers:"+name, classes.get(name), id, "analoguers:gatedef/"+name, "part.analoguers.gate."+name);
        	
        	defID++;
        }
        conf.save();
	}
	
    @EventHandler
    public void init(FMLInitializationEvent evt)
    {
    	mcg.registerGateStack(ItemGate.getStack(new GateAnalogueInverter()), "www", "ccc", "sss");
    	mcg.registerGateStack(ItemGate.getStack(new GateAnalogueAdder()), "wcw", "cwc", "sss");
    	mcg.registerGateStack(ItemGate.getStack(new GateAnalogueEqualityChecker()), "wcw", "cpc", "sss", 'p', Items.COMPARATOR);
    	mcg.registerGateStack(ItemGate.getStack(new GateAnalogueSignalSynth()), "wpw", "pkp", "sss", 'p', Items.COMPARATOR, 'k', Items.CLOCK);
    	
    	proxy.registerItemModels();
    }
    
    /** @deprecated I just found that you can <code>@Instance("modid")</code> */
    @Deprecated
	public static Object getMod(String modid){
		if(Loader.isModLoaded(modid))
			for(Entry<ModContainer, Object> e : Loader.instance().getModObjectList().entrySet()){
				if(e.getKey().getModId().equalsIgnoreCase(modid))
					return e.getValue();
			}
		return null;
	}
}
