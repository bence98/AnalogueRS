package csokicraft.forge10.analoguers;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class ItemSynthTuner extends Item{
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced){
		tooltip.add("§aThis tool can be used on a Signal Synthetiser to configure it");
		tooltip.add("§bRight-click to change its waveform");
		tooltip.add("§bSneak-right click to change its frequency (0 means step only on redstone pulse)");
		super.addInformation(stack, playerIn, tooltip, advanced);
	}
	
	@Override
	public boolean doesSneakBypassUse(ItemStack stack, IBlockAccess world, BlockPos pos, EntityPlayer player) {
		return true;
	}
}
