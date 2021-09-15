package net.mcreator.lol.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.lol.LolModVariables;
import net.mcreator.lol.LolMod;

import java.util.Map;

public class ProceduraprzyciskdalejProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				LolMod.LOGGER.warn("Failed to load dependency entity for procedure Proceduraprzyciskdalej!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((((entity.getCapability(LolModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new LolModVariables.PlayerVariables())).numerstrony) == 1)) {
			{
				double _setval = (double) (((entity.getCapability(LolModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new LolModVariables.PlayerVariables())).numerstrony) + 1);
				entity.getCapability(LolModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.numerstrony = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
