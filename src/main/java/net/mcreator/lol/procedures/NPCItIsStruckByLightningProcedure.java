package net.mcreator.lol.procedures;

import net.minecraftforge.fml.server.ServerLifecycleHooks;

import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.Util;
import net.minecraft.server.MinecraftServer;

import net.mcreator.lol.LolMod;

import java.util.Map;

public class NPCItIsStruckByLightningProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				LolMod.LOGGER.warn("Failed to load dependency world for procedure NPCItIsStruckByLightning!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		if (!world.isRemote()) {
			MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
			if (mcserv != null)
				mcserv.getPlayerList().func_232641_a_(new StringTextComponent("So\u0142tys: ale mnie jeb\u0142o"), ChatType.SYSTEM, Util.DUMMY_UUID);
		}
	}
}
