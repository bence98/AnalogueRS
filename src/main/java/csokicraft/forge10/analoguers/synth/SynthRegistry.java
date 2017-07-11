package csokicraft.forge10.analoguers.synth;

import java.util.*;

public class SynthRegistry{
	protected Map<Integer, Class<? extends SynthMode>> data;
	protected static SynthRegistry inst = new SynthRegistry();
	protected int lastID = -1;
	
	protected SynthRegistry(){
		data = new HashMap<>();
		
		register(0, SynthModeOff.class);
		register(1, SynthModeSquare.class);
		register(2, SynthModeTriangle.class);
		register(3, SynthModeSawtooth.class);
		register(4, SynthModeAbsSine.class);
	}
	
	public static SynthRegistry instance(){
		return inst;
	}
	
	public void register(int i, Class<?extends SynthMode> mode){
		if(data.containsKey(i))
			throw new IllegalArgumentException("SynthMode ID "+i+" was already registered! Had "+data.get(i)+", got "+mode+"!");
		data.put(i, mode);
		lastID = Math.max(i, lastID);
	}
	
	public SynthMode get(int i){
		try {
			return data.get(i).newInstance();
		} catch (InstantiationException | IllegalAccessException e){
			throw new IllegalStateException("Could not instantiate "+data.get(i)+", report this to the developer:"+e.getMessage(), e);
		}
	}
	
	public int getNextID(int prevID){
		boolean exit = false;
		int nextID = 0;
		for(int id:data.keySet()){
			if(exit){
				nextID=id;
				break;
			}
			if(id == prevID) exit=true;
		}
		return nextID;
	}
}
