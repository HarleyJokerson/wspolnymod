package net.mcreator.lol.procedures;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.lol.LolMod;

import java.util.Map;

public class ManatestpotkaEffectStartedappliedProcedure {
	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				LolMod.LOGGER.warn("Failed to load dependency entity for procedure ManatestpotkaEffectStartedapplied!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((true)) {
			if (entity instanceof PlayerEntity) {
				((PlayerEntity) entity).abilities.allowEdit = (true);
				((PlayerEntity) entity).sendPlayerAbilities();
			}
		}
		return (false);
	}
}
