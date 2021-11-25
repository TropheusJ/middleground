package io.github.tropheusj.middleground.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.option.SkinOptionsScreen;

import net.minecraft.text.Text;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static io.github.tropheusj.middleground.Middleground.randWidth;
import static io.github.tropheusj.middleground.Middleground.rand;

@Mixin({SkinOptionsScreen.class, OptionsScreen.class})
public class ModifyBuild extends Screen {
	protected ModifyBuild(Text text) {
		super(text);
	}

	@ModifyArgs(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/CyclingButtonWidget$Builder;build(IIIILnet/minecraft/text/Text;Lnet/minecraft/client/gui/widget/CyclingButtonWidget$UpdateCallback;)Lnet/minecraft/client/gui/widget/CyclingButtonWidget;"))
	private void modifyBuild(Args args) {
		args.set(0, rand(width));
		args.set(1, rand(height));
		args.set(2, randWidth());
	}
}
