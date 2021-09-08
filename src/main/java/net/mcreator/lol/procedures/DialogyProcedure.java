package net.mcreator.lol.procedures;

import net.minecraftforge.fml.server.ServerLifecycleHooks;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.Util;
import net.minecraft.util.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.advancements.Advancement;

import net.mcreator.lol.LolMod;

import java.util.Map;

public class DialogyProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("advancement") == null) {
			if (!dependencies.containsKey("advancement"))
				LolMod.LOGGER.warn("Failed to load dependency advancement for procedure Dialogy!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				LolMod.LOGGER.warn("Failed to load dependency world for procedure Dialogy!");
			return;
		}
		Advancement advancement = (Advancement) dependencies.get("advancement");
		IWorld world = (IWorld) dependencies.get("world");
		if ((world instanceof World && ((World) world).getServer() != null
				? ((World) world).getServer().getAdvancementManager().getAdvancement(new ResourceLocation("lol:wioskazbudowana")).equals(advancement)
				: false)) {
			if (!world.isRemote()) {
				MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
				if (mcserv != null)
					mcserv.getPlayerList().func_232641_a_(new StringTextComponent("Dowiedz si\u0119 co potrzebuje So\u0142tys"), ChatType.SYSTEM,
							Util.DUMMY_UUID);
			}
		}
	}
}
