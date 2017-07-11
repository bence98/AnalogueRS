package csokicraft.forge10.analoguers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerItemModels(){
		ItemModelMesher imm = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
		ModelResourceLocation resloc = new ModelResourceLocation(AnalogueRS.MODID+":synthTuner", "inventory");
		imm.register(AnalogueRS.synthTuner, 0, resloc);
		ModelBakery.registerItemVariants(AnalogueRS.synthTuner, resloc);
	}
}
