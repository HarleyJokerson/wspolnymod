package net.mcreator.lol.procedures;

import net.minecraftforge.fml.server.ServerLifecycleHooks;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.Util;
import net.minecraft.server.MinecraftServer;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScoreCriteria;
import net.minecraft.scoreboard.Score;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.Entity;

import net.mcreator.lol.LolMod;

import java.util.Map;

public class DialogyProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				LolMod.LOGGER.warn("Failed to load dependency entity for procedure Dialogy!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				LolMod.LOGGER.warn("Failed to load dependency sourceentity for procedure Dialogy!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				LolMod.LOGGER.warn("Failed to load dependency x for procedure Dialogy!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				LolMod.LOGGER.warn("Failed to load dependency y for procedure Dialogy!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				LolMod.LOGGER.warn("Failed to load dependency z for procedure Dialogy!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				LolMod.LOGGER.warn("Failed to load dependency world for procedure Dialogy!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (!world.isRemote()) {
			MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
			if (mcserv != null)
				mcserv.getPlayerList().func_232641_a_(new StringTextComponent(
						"Poka\u017Ce Ci swoje towary, je\u015Bli dostarczysz mi sw\u00F3j szmaciany czepek i sztabk\u0119 \u017Celaza stworz\u0119 Ci he\u0142m "),
						ChatType.SYSTEM, Util.DUMMY_UUID);
		}
		if ((((sourceentity instanceof PlayerEntity)
				? ((PlayerEntity) sourceentity).inventory.hasItemStack(new ItemStack(Items.LEATHER_HELMET))
				: false)
				&& ((sourceentity instanceof PlayerEntity)
						? ((PlayerEntity) sourceentity).inventory.hasItemStack(new ItemStack(Items.IRON_INGOT))
						: false))) {
			if (world instanceof World && !world.isRemote()) {
				ItemEntity entityToSpawn = new ItemEntity((World) world, x, (y + 2), z, new ItemStack(Items.IRON_HELMET));
				entityToSpawn.setPickupDelay((int) 10);
				world.addEntity(entityToSpawn);
			}
			if (sourceentity instanceof PlayerEntity) {
				ItemStack _stktoremove = new ItemStack(Items.LEATHER_HELMET);
				((PlayerEntity) sourceentity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
						((PlayerEntity) sourceentity).container.func_234641_j_());
			}
			if (sourceentity instanceof PlayerEntity) {
				ItemStack _stktoremove = new ItemStack(Items.IRON_INGOT);
				((PlayerEntity) sourceentity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 2,
						((PlayerEntity) sourceentity).container.func_234641_j_());
			}
			{
				Entity _ent = entity;
				if (_ent instanceof PlayerEntity) {
					Scoreboard _sc = ((PlayerEntity) _ent).getWorldScoreboard();
					ScoreObjective _so = _sc.getObjective("custom_score");
					if (_so == null) {
						_so = _sc.addObjective("custom_score", ScoreCriteria.DUMMY, new StringTextComponent("custom_score"),
								ScoreCriteria.RenderType.INTEGER);
					}
					Score _scr = _sc.getOrCreateScore(((PlayerEntity) _ent).getScoreboardName(), _so);
					_scr.setScorePoints((int) 1);
				}
			}
			if (!world.isRemote()) {
				MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
				if (mcserv != null)
					mcserv.getPlayerList().func_232641_a_(new StringTextComponent("Niech ci s\u0142u\u017Cy"), ChatType.SYSTEM, Util.DUMMY_UUID);
			}
			if (sourceentity instanceof PlayerEntity && !sourceentity.world.isRemote()) {
				((PlayerEntity) sourceentity).sendStatusMessage(new StringTextComponent("zazasage"), (false));
			}
		}
	}
}
