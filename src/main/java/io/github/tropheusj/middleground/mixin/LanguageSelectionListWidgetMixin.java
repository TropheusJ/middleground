package io.github.tropheusj.middleground.mixin;

import io.github.tropheusj.middleground.Middleground;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;

@Mixin(targets = "net.minecraft.client.gui.screen.option.LanguageOptionsScreen$LanguageSelectionListWidget")
public class LanguageSelectionListWidgetMixin {
	@Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Ljava/util/SortedSet;iterator()Ljava/util/Iterator;"))
	private Iterator redirectIteratorInLoop(SortedSet instance) {
		Object[] arr = instance.toArray();
		Middleground.shuffle(arr);
		return List.of(arr).iterator();
	}
}
