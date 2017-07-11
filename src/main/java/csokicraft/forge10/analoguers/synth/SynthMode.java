package csokicraft.forge10.analoguers.synth;

/** Various modes for the Signal Synthetiser */
public interface SynthMode{
	public String getName();
	/** Gets the value given the current step count */
	public byte getValue(long step);
	/** Gives the number of steps after the signal pattern repeats.
	  * Special cases: 0=never repeats, 1=always constant */
	public long recurrance();
}
