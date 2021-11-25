package io.github.tropheusj.middleground.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.AccessibilityOptionsScreen;

import net.minecraft.client.gui.screen.option.NarratorOptionsScreen;
import net.minecraft.text.Text;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static io.github.tropheusj.middleground.Middleground.randWidth;
import static io.github.tropheusj.middleground.Middleground.rand;

@Mixin(value = {AccessibilityOptionsScreen.class, NarratorOptionsScreen.class})
public class ModifyInitFooter extends Screen {
	protected ModifyInitFooter(Text text) {
		super(text);
	}

	@ModifyArgs(method = "initFooter", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/ButtonWidget;<init>(IIIILnet/minecraft/text/Text;Lnet/minecraft/client/gui/widget/ButtonWidget$PressAction;)V"))
	private void modifyButtonWidget(Args args) {
		args.set(0, rand(width));
		args.set(1, rand(height));
		args.set(2, randWidth());
	}
}
