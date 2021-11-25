package io.github.tropheusj.middleground.mixin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import net.minecraft.client.gui.screen.option.GameOptionsScreen;

import net.minecraft.client.option.Option;
import net.minecraft.text.Text;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.VideoOptionsScreen;
import net.minecraft.client.option.GameOptions;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(VideoOptionsScreen.class)
public class VideoOptionsScreenMixin extends GameOptionsScreen {
	@Shadow
	@Final
	private static Option[] OPTIONS;
	@Unique
	private static final Random rand = new Random();

	public VideoOptionsScreenMixin(Screen screen, GameOptions gameOptions, Text text) {
		super(screen, gameOptions, text);
	}

	@ModifyArgs(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/ButtonListWidget;addAll([Lnet/minecraft/client/option/Option;)V"))
	private void modifyButtonListOrder(Args args) {
		List<Option> list = new ArrayList<>(List.of(OPTIONS));
		Collections.shuffle(list, rand);
		args.set(0, list.toArray(new Option[list.size()]));
	}
}
