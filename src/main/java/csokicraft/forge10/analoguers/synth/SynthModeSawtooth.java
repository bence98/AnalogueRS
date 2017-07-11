package csokicraft.forge10.analoguers.synth;

public class SynthModeSawtooth implements SynthMode{

	@Override
	public String getName(){
		return "Sawtooth";
	}

	@Override
	public byte getValue(long step){
		return (byte) (step%16);
	}

	@Override
	public long recurrance(){
		return 16;
	}

}
