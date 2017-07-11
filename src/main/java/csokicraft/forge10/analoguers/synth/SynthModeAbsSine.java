package csokicraft.forge10.analoguers.synth;

public class SynthModeAbsSine implements SynthMode{

	@Override
	public String getName(){
		return "Absolute sine";
	}

	@Override
	public byte getValue(long step){
		step=step%recurrance();
		return (byte) Math.round(15*Math.sin(Math.PI*step/recurrance()));
	}

	@Override
	public long recurrance(){
		return 314;
	}

}
