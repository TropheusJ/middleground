package io.github.tropheusj.middleground.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.ControlsOptionsScreen;
import net.minecraft.client.gui.screen.option.LanguageOptionsScreen;
import net.minecraft.client.gui.screen.option.MouseOptionsScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;

import net.minecraft.client.gui.screen.option.SkinOptionsScreen;
import net.minecraft.client.gui.screen.option.SoundOptionsScreen;
import net.minecraft.client.gui.screen.option.VideoOptionsScreen;
import net.minecraft.client.gui.screen.pack.PackScreen;
import net.minecraft.text.Text;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static io.github.tropheusj.middleground.Middleground.randWidth;
import static io.github.tropheusj.middleground.Middleground.rand;

@Mixin({OptionsScreen.class, SkinOptionsScreen.class, VideoOptionsScreen.class,
		SoundOptionsScreen.class, ControlsOptionsScreen.class, MouseOptionsScreen.class,
		LanguageOptionsScreen.class, PackScreen.class})
public class ModifyButtonWidget extends Screen {
	protected ModifyButtonWidget(Text text) {
		super(text);
	}

	@ModifyArgs(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/ButtonWidget;<init>(IIIILnet/minecraft/text/Text;Lnet/minecraft/client/gui/widget/ButtonWidget$PressAction;)V"))
	private void modifyButtonWidget(Args args) {
		args.set(0, rand(width));
		args.set(1, rand(height));
		args.set(2, randWidth());
	}
}
