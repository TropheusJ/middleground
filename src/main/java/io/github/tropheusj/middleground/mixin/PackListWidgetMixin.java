package io.github.tropheusj.middleground.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.pack.PackListWidget;

import net.minecraft.client.gui.widget.AlwaysSelectedEntryListWidget;

import net.minecraft.text.Text;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.HashMap;
import java.util.Map;

import static io.github.tropheusj.middleground.Middleground.randColor;
import static io.github.tropheusj.middleground.Middleground.randWidth;
import static io.github.tropheusj.middleground.Middleground.rand;

@Mixin(PackListWidget.class)
public class PackListWidgetMixin extends AlwaysSelectedEntryListWidget<PackListWidget.ResourcePackEntry> {
	@Unique
	private int titleX;
	@Unique
	private int titleY;
	@Unique
	private int titleColor;
	@Unique
	private int lastCheckedId;
	@Unique
	private Map<Integer, Integer> indexToX = new HashMap<>();
	@Unique
	private Map<Integer, Integer> indexToY = new HashMap<>();

	public PackListWidgetMixin(MinecraftClient minecraftClient, int i, int j, int k, int l, int m) {
		super(minecraftClient, i, j, k, l, m);
	}

	@Inject(method = "<init>", at = @At("RETURN"))
	private void setHeaderArgs(MinecraftClient minecraftClient, int i, int j, Text text, CallbackInfo ci) {
		titleX = rand(width);
		titleY = rand(height);
		titleColor = randColor();
	}

	@ModifyArgs(method = "renderHeader", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/text/Text;FFI)I"))
	private void modifyHeader(Args args) {
		args.set(2, (float) titleX);
		args.set(3, (float) titleY);
		args.set(4, titleColor);
	}

	@Override
	protected int getRowTop(int index) {
		lastCheckedId = index;
		return indexToX.computeIfAbsent(index, i -> rand(height));
	}

	@Override
	public int getRowLeft() {
		return indexToY.computeIfAbsent(lastCheckedId, i -> rand(height));
	}

	@Override
	public int getRowWidth() {
		return randWidth();
	}
}
