package io.github.tropheusj.middleground.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.CyclingButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.world.Difficulty;

@Mixin(OptionsScreen.class)
public abstract class OptionsScreenMixin extends Screen {
	@Unique
	private static final Random rand = new Random();
	@Shadow
	private CyclingButtonWidget<Difficulty> difficultyButton;

	protected OptionsScreenMixin(Text text) {
		super(text);
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

	@ModifyArgs(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/LockButtonWidget;<init>(IILnet/minecraft/client/gui/widget/ButtonWidget$PressAction;)V"))
	private void modifyLockButtonWidgetInit(Args args) {
		args.set(0, randomX());
		args.set(1, randomY());
	}

	@Inject(method = "init", at = @At("RETURN"))
	private void finalizeDifficultyButton(CallbackInfo ci) {
		if (this.client.world != null) {
			difficultyButton.setWidth(randomWidth());
		}
	}
}
