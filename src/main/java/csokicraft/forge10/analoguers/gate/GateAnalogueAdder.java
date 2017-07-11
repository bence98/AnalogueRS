package csokicraft.forge10.analoguers.gate;

import net.minecraft.util.EnumFacing;
import pl.asie.charset.gates.PartGate.Connection;

public class GateAnalogueAdder extends PartGateAnalogue{
	@Override
	public Connection getType(EnumFacing dir){
		if(dir == EnumFacing.NORTH || dir == EnumFacing.EAST) return Connection.OUTPUT_ANALOG;
		return Connection.INPUT_ANALOG;
	}

	@Override
	protected byte calculateOutputInside(EnumFacing dir){
		if(getType(dir) != Connection.OUTPUT_ANALOG) return 0;
		
		int inSum = getValueInside(EnumFacing.SOUTH)+getValueInside(EnumFacing.WEST);
		if(dir == EnumFacing.NORTH) return (byte) (inSum % 16);
		else if(dir == EnumFacing.EAST) return (byte) (inSum / 16);
		return 0;
	}

	@Override
	public State getLayerState(int id){
		if(getValueInside(EnumFacing.NORTH) > 0) return State.ON;
		return State.OFF;
	}

	@Override
	public State getTorchState(int id){
		return getLayerState(0);
	}

}
