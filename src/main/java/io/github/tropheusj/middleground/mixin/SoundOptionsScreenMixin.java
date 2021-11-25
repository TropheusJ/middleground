package io.github.tropheusj.middleground.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.screen.option.SoundOptionsScreen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;

@Mixin(SoundOptionsScreen.class)
public class SoundOptionsScreenMixin extends GameOptionsScreen {
	@Unique
	private static final Random rand = new Random();

	public SoundOptionsScreenMixin(Screen screen, GameOptions gameOptions, Text text) {
		super(screen, gameOptions, text);
	}

	private int randomX() {
		return rand.nextInt(this.width - 300);
	}

	private int randomY() {
		return rand.nextInt(this.height - 20);
	}

	private int randomWidth() {
		return rand.nextInt(300);
	}

	@ModifyArgs(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/SoundSliderWidget;<init>(Lnet/minecraft/client/MinecraftClient;IILnet/minecraft/sound/SoundCategory;I)V"))
	private void modifySliders(Args args) {
		args.set(1, randomX());
		args.set(2, randomY());
		args.set(4, randomWidth());
	}
}
