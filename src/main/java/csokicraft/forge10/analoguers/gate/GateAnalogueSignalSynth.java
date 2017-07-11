package csokicraft.forge10.analoguers.gate;

import csokicraft.forge10.analoguers.AnalogueRS;
import csokicraft.forge10.analoguers.synth.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import pl.asie.charset.gates.PartGate.Connection;

public class GateAnalogueSignalSynth extends PartGateAnalogue{
	int synthID = 0, freq = 0;
	boolean wasOn = false;
	long lastT = System.currentTimeMillis(), step = 0;

	public GateAnalogueSignalSynth(){
		synthID = 0;
	}
	
	@Override
	public Connection getType(EnumFacing dir) {
		if(dir == EnumFacing.NORTH) return Connection.OUTPUT_ANALOG;
		if(dir == EnumFacing.SOUTH) return Connection.INPUT;
		return Connection.NONE;
	}
	
	@Override
	public void update(){
		super.update();
		if(freq>0) notifyPartUpdate();
	}
	
	@Override
	protected byte calculateOutputInside(EnumFacing arg0){
		if(rsToDigi(getValueInside(EnumFacing.SOUTH))){
			if(freq>0)stepByTime();
			else stepByPulse();
			
			long rec = getSynthMode().recurrance();
			while(rec>0 && step>=rec) step -= rec;
		}else wasOn = false;
		return getSynthMode().getValue(step);
	}

	private void stepByTime(){
		long T = System.currentTimeMillis();
		long toStep = (T-lastT)*freq/1000;
		if(toStep>0){
			step += toStep;
			lastT = T;
		}
	}
	
	private void stepByPulse(){
		if(!wasOn){
			step++;
			wasOn=true;
		}
	}
	
	@Override
	public State getLayerState(int arg0){
		return getTorchState(arg0);
	}

	@Override
	public State getTorchState(int arg0){
		if(arg0==0) return getStateFrom(EnumFacing.NORTH);
		return getStateFrom(EnumFacing.SOUTH);
	}
	
	private SynthMode getSynthMode(){
		return SynthRegistry.instance().get(synthID);
	}

	@Override
	public boolean onActivated(EntityPlayer arg0, ItemStack arg1, Vec3d arg2){
		if(arg1!=null&&arg1.getItem().equals(AnalogueRS.synthTuner)){
			if(arg0.worldObj.isRemote) return true;
			
			if(arg0.isSneaking()){
				freq++;
				if(freq>20) freq = 0;
				arg0.addChatComponentMessage(new TextComponentString("Frequency: "+freq+" steps/sec"));
			}else{
				synthID = SynthRegistry.instance().getNextID(synthID);
				arg0.addChatComponentMessage(new TextComponentString("Signal mode: "+getSynthMode().getName()));
			}
			return true;
		}
		return super.onActivated(arg0, arg1, arg2);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag){
		tag.setInteger("synthMode", synthID);
		tag.setInteger("freq", freq);
		tag.setLong("step", step);
		return super.writeToNBT(tag);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag){
		synthID = tag.getInteger("synthMode");
		freq = tag.getInteger("freq");
		step = tag.getLong("step");
		super.readFromNBT(tag);
	}
}
