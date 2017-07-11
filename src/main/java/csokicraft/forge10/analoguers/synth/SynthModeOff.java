package csokicraft.forge10.analoguers.synth;

/** "Always off" mode */
public class SynthModeOff implements SynthMode{
	@Override
	public String getName(){
		return "Off";
	}
	
	@Override
	public byte getValue(long deltaT){
		return 0;
	}

	@Override
	public long recurrance(){
		return 1;
	}

}
