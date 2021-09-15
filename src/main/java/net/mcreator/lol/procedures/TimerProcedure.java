package net.mcreator.lol.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.IWorld;

import net.mcreator.lol.LolModVariables;

import java.util.Map;
import java.util.HashMap;

public class TimerProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onWorldTick(TickEvent.WorldTickEvent event) {
			if (event.phase == TickEvent.Phase.END) {
				IWorld world = event.world;
				Map<String, Object> dependencies = new HashMap<>();
				dependencies.put("world", world);
				dependencies.put("event", event);
				executeProcedure(dependencies);
			}
		}
	}
	public static void executeProcedure(Map<String, Object> dependencies) {
		LolModVariables.TimeSec = (double) (LolModVariables.TimeSec + 0.05);
		if ((LolModVariables.TimeSec >= 2)) {
			LolModVariables.TimeSec = (double) 0;
			LolModVariables.TimeMin = (double) (LolModVariables.TimeMin + 1);
		}
		if ((LolModVariables.TimeMin >= 2)) {
			LolModVariables.TimeMin = (double) 0;
		}
	}
}
