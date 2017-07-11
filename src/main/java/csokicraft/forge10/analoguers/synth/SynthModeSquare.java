package csokicraft.forge10.analoguers.synth;

public class SynthModeSquare implements SynthMode{
	@Override
	public String getName(){
		return "Square signal";
	}

	@Override
	public byte getValue(long step){
		return (byte) (15*(step%2));
	}

	@Override
	public long recurrance(){
		return 2;
	}

}
