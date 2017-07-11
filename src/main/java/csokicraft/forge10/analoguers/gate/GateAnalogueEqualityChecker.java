package csokicraft.forge10.analoguers.gate;

import net.minecraft.util.EnumFacing;
import pl.asie.charset.gates.PartGate.Connection;

public class GateAnalogueEqualityChecker extends PartGateAnalogue{
	@Override
	public Connection getType(EnumFacing dir){
		if(dir == EnumFacing.NORTH || dir == EnumFacing.EAST) return Connection.OUTPUT_ANALOG;
		return Connection.INPUT_ANALOG;
	}
	
	@Override
	protected byte calculateOutputInside(EnumFacing f) {
		byte in1=getValueInside(EnumFacing.SOUTH),
			 in2=getValueInside(EnumFacing.WEST);
		
		if(f==EnumFacing.NORTH){
			if(in1==in2) return in1;
			else return 0;
		}
		else return (byte) (in1^in2);
	}

	@Override
	public State getLayerState(int arg0){
		return State.OFF;
	}

	@Override
	public State getTorchState(int arg0){
		return State.OFF;
	}

}
