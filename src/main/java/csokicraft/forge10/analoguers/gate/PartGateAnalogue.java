package csokicraft.forge10.analoguers.gate;

import csokicraft.forge10.analoguers.AnalogueRS;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3d;
import pl.asie.charset.gates.PartGate;
import pl.asie.charset.gates.PartGate.Connection;

public abstract class PartGateAnalogue extends PartGate{
	
	//Ensure that Reflection can instantiate gates
	public PartGateAnalogue(){}
	
	@Override
	public Connection getType(EnumFacing dir) {
		if(dir == EnumFacing.NORTH) return Connection.OUTPUT_ANALOG;
		if(dir == EnumFacing.SOUTH) return Connection.INPUT_ANALOG;
		return Connection.NONE;
	}
	
	@Override
	public boolean canInvertSide(EnumFacing side){
		return false;
	}
	
	@Override
	public boolean canBlockSide(EnumFacing side){
		return false;
	}
	
	protected State getStateFrom(byte b){
		return getStateFrom(rsToDigi(b));
	}

	protected State getStateFrom(boolean b){
		return b?State.ON:State.OFF;
	}
	
	protected State getStateFrom(EnumFacing f){
		return getStateFrom(getValueInside(f));
	}
}
