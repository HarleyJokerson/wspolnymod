package net.mcreator.lol.procedures;

import net.minecraft.client.gui.widget.TextFieldWidget;

import net.mcreator.lol.LolMod;

import java.util.Map;
import java.util.HashMap;

public class PewnysiebieProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("guistate") == null) {
			if (!dependencies.containsKey("guistate"))
				LolMod.LOGGER.warn("Failed to load dependency guistate for procedure Pewnysiebie!");
			return;
		}
		HashMap guistate = (HashMap) dependencies.get("guistate");
		{
			TextFieldWidget _tf = (TextFieldWidget) guistate.get("text:pewnysiebie");
			if (_tf != null) {
				_tf.setText("Text");
			}
		}
	}
}
