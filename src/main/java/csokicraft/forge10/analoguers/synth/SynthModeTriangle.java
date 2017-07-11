package csokicraft.forge10.analoguers.synth;

public class SynthModeTriangle implements SynthMode{

	@Override
	public String getName(){
		return "Triangle";
	}

	@Override
	public byte getValue(long step){
		step=step%recurrance();
		if(step<=15) return (byte) step;
		return (byte) (recurrance()-step-1);
	}

	@Override
	public long recurrance(){
		return 31;
	}

}
