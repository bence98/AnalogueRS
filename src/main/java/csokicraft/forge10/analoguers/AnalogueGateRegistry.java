package csokicraft.forge10.analoguers;

import java.util.*;
import java.util.Map.Entry;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import pl.asie.charset.gates.PartGate;

/** AnalogueRS's gate registry. Technically, a 3-key BiMap
  * @author CsokiCraft */
@Deprecated
public class AnalogueGateRegistry {
    protected BiMap<String, Class<? extends PartGate>> gateClasses = HashBiMap.create();
    protected BiMap<Integer, String> gateNames = HashBiMap.create();
    
    protected AnalogueGateRegistry(){}

    public void registerGate(String name, Class<? extends PartGate> cls, int id){
    	System.out.println("[AnalogueRS] Registering "+name+" ("+cls.getName()+") with ID "+id);
        gateClasses.put(name, cls);
        gateNames.put(id, name);
	}
    
    public String getName(Class<? extends PartGate> cls){
    	return gateClasses.inverse().get(cls);
    }
    
    public String getName(int id){
    	return gateNames.get(id);
    }
    
    public int getID(String name){
    	return gateNames.inverse().get(name);
    }
    
    public int getID(Class<? extends PartGate> cls){
    	return getID(getName(cls));
    }
    
    public Class<? extends PartGate> getClass(String name){
		return gateClasses.get(name);
	}
    
    public Class<? extends PartGate> getClass(int id){
		return getClass(getName(id));
	}

    /** Performs a for-each on this registry, passing the elements one by one to the listener.
      * @param obj Additional data to give to the first {@link IRegistryListener}
      * @return Additional data from the last {@link IRegistryListener} */
    public Object forEach(IRegistryListener listener, Object obj){
    	for(Entry<Integer, String> e : gateNames.entrySet())
    		obj = listener.onElement(e.getKey(), e.getValue(), gateClasses.get(e.getValue()), obj);
    	return obj;
    }
    
    public static interface IRegistryListener{
    	/** Called when an element needs processing.
    	  * @param obj Additional data received from the previous iteration
    	  * @return Additional data to give to the next iteration */
    	public Object onElement(int id, String name, Class<? extends PartGate> cls, Object obj);
    }
}
