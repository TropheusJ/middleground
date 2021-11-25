package io.github.tropheusj.middleground.mixin;

import io.github.tropheusj.middleground.Middleground;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.MouseOptionsScreen;
import net.minecraft.client.gui.screen.option.NarratorOptionsScreen;
import net.minecraft.client.option.Option;

import net.minecraft.text.Text;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = {NarratorOptionsScreen.class, MouseOptionsScreen.class})
public class ModifyButtonListOrder extends Screen {
	protected ModifyButtonListOrder(Text text) {
		super(text);
	}

	@ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/ButtonListWidget;addAll([Lnet/minecraft/client/option/Option;)V"))
	private Option[] modifyList(Option[] options) {
		Middleground.shuffle(options);
		return options;
	}
}
