
package net.mcreator.lol.gui;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.Minecraft;

import net.mcreator.lol.LolMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class ZnoooowuwuwuwGuiWindow extends ContainerScreen<ZnoooowuwuwuwGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	private final static HashMap guistate = ZnoooowuwuwuwGui.guistate;
	TextFieldWidget textFieldName;
	public ZnoooowuwuwuwGuiWindow(ZnoooowuwuwuwGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 180;
		this.ySize = 180;
	}
	private static final ResourceLocation texture = new ResourceLocation("lol:textures/znoooowuwuwuw.png");
	@Override
	public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderHoveredTooltip(ms, mouseX, mouseY);
		textFieldName.render(ms, mouseX, mouseY, partialTicks);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.color4f(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		Minecraft.getInstance().getTextureManager().bindTexture(texture);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.blit(ms, k, l, 0, 0, this.xSize, this.ySize, this.xSize, this.ySize);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeScreen();
			return true;
		}
		if (textFieldName.isFocused())
			return textFieldName.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void tick() {
		super.tick();
		textFieldName.tick();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack ms, int mouseX, int mouseY) {
		this.font.drawString(ms, "Witaj w nawiedzonych moczarach,", 4, 10, -13421773);
		this.font.drawString(ms, "nie wiem co tu robisz i mnie to", 4, 22, -12829636);
		this.font.drawString(ms, "nie obchodzi ale je\u015Bli masz zamiar", 4, 34, -12829636);
		this.font.drawString(ms, "da\u0107 si\u0119 zabi\u0107 nas w to nie mieszaj", 4, 45, -12829636);
		this.font.drawString(ms, "Spokojnie, przyszed\u0142em tu po co\u015B", 4, 78, -12829636);
		this.font.drawString(ms, "co mi si\u0119 nale\u017Cy, wi\u0119c nie wchod\u017A", 5, 89, -12829636);
		this.font.drawString(ms, "mi w drog\u0119 starcze", 5, 99, -12829636);
		this.font.drawString(ms, "Jest co\u015B co powinienem wiedzie\u0107? ", 4, 133, -12829636);
		this.font.drawString(ms, "Jestem tu pierwszy raz i doceni\u0119", 6, 145, -12829636);
		this.font.drawString(ms, "ka\u017Cd\u0105 pomoc", 6, 156, -12829636);
	}

	@Override
	public void onClose() {
		super.onClose();
		Minecraft.getInstance().keyboardListener.enableRepeatEvents(false);
	}

	@Override
	public void init(Minecraft minecraft, int width, int height) {
		super.init(minecraft, width, height);
		minecraft.keyboardListener.enableRepeatEvents(true);
		this.addButton(new Button(this.guiLeft + 4, this.guiTop + 56, 85, 20, new StringTextComponent("Pewny siebie"), e -> {
			if (true) {
				LolMod.PACKET_HANDLER.sendToServer(new ZnoooowuwuwuwGui.ButtonPressedMessage(0, x, y, z));
				ZnoooowuwuwuwGui.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 4, this.guiTop + 111, 65, 20, new StringTextComponent("Pokornie"), e -> {
			if (true) {
				LolMod.PACKET_HANDLER.sendToServer(new ZnoooowuwuwuwGui.ButtonPressedMessage(1, x, y, z));
				ZnoooowuwuwuwGui.handleButtonAction(entity, 1, x, y, z);
			}
		}));
		textFieldName = new TextFieldWidget(this.font, this.guiLeft + 63, this.guiTop + 156, 120, 20, new StringTextComponent(""));
		guistate.put("text:textFieldName", textFieldName);
		textFieldName.setMaxStringLength(32767);
		this.children.add(this.textFieldName);
	}
}
