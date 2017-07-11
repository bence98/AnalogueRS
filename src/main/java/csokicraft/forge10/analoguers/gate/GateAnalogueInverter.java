package csokicraft.forge10.analoguers.gate;

import net.minecraft.util.EnumFacing;
import pl.asie.charset.gates.PartGate.State;

public class GateAnalogueInverter extends PartGateAnalogue{

	@Override
	public State getLayerState(int id) {
		State s = getTorchState(id/2);
		if(id%2 == 0){
			s = s.invert();
		}
		return s;
	}

	@Override
	public State getTorchState(int id){
		byte inStrength = getValueInside(EnumFacing.SOUTH);
		if(inStrength >= 15) return State.OFF;
		if(id >= (inStrength/4)) return State.ON;
		return State.OFF;
	}

	@Override
	protected byte calculateOutputInside(EnumFacing dir) {
		if(getType(dir) != Connection.OUTPUT_ANALOG) return 0;
		
		byte inStrength = getValueInside(EnumFacing.SOUTH);
		return (byte) (15 - inStrength);
	}
}
