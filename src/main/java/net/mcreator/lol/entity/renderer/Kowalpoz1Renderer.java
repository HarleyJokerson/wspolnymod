package net.mcreator.lol.entity.renderer;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.entity.model.VillagerModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import net.mcreator.lol.entity.Kowalpoz1Entity;

@OnlyIn(Dist.CLIENT)
public class Kowalpoz1Renderer {
	public static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(Kowalpoz1Entity.entity,
					renderManager -> new MobRenderer(renderManager, new VillagerModel(0), 0.5f) {
						@Override
						public ResourceLocation getEntityTexture(Entity entity) {
							return new ResourceLocation("lol:textures/vilagersmith.png");
						}
					});
		}
	}
}
