package net.mcreator.lol.procedures;

import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.lol.item.ManaItem;
import net.mcreator.lol.LolModVariables;
import net.mcreator.lol.LolMod;

import java.util.concurrent.atomic.AtomicReference;
import java.util.Map;
import java.util.HashMap;

public class OdwojtkaProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
			if (event.phase == TickEvent.Phase.END) {
				Entity entity = event.player;
				World world = entity.world;
				double i = entity.getPosX();
				double j = entity.getPosY();
				double k = entity.getPosZ();
				Map<String, Object> dependencies = new HashMap<>();
				dependencies.put("x", i);
				dependencies.put("y", j);
				dependencies.put("z", k);
				dependencies.put("world", world);
				dependencies.put("entity", entity);
				dependencies.put("event", event);
				executeProcedure(dependencies);
			}
		}
	}
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				LolMod.LOGGER.warn("Failed to load dependency entity for procedure Odwojtka!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				LolMod.LOGGER.warn("Failed to load dependency world for procedure Odwojtka!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		double diamonds = 0;
		LolModVariables.mana = (double) 0;
		{
			AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
			entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _iitemhandlerref.set(capability));
			if (_iitemhandlerref.get() != null) {
				for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
					ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
					if ((ManaItem.block == (itemstackiterator).getItem())) {
						LolModVariables.mana = (double) (LolModVariables.mana + (((itemstackiterator)).getCount()));
					}
				}
			}
		}
		if (((entity.getPersistentData().getDouble("ModTime")) == 0)) {
			entity.getPersistentData().putDouble("ModTime", 50);
		} else {
			entity.getPersistentData().putDouble("ModTime", ((entity.getPersistentData().getDouble("ModTime")) - 1));
		}
		if (((entity.getPersistentData().getDouble("ModTime")) == 0)) {
			if ((LolModVariables.mana > 64)) {
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = new ItemStack(ManaItem.block);
					((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) (LolModVariables.mana - 64),
							((PlayerEntity) entity).container.func_234641_j_());
				}
			}
			if ((LolModVariables.mana <= 63)) {
				if (entity instanceof PlayerEntity) {
					ItemStack _setstack = new ItemStack(ManaItem.block);
					_setstack.setCount((int) 1);
					ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
				}
			}
		}
		if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
			((PlayerEntity) entity).sendStatusMessage(new StringTextComponent((("Masz ") + "" + (((LolModVariables.mana) + "" + ("/64.0 many"))))),
					(true));
		}
	}
}
