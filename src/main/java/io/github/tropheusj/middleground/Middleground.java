package io.github.tropheusj.middleground;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.option.Option;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Middleground implements ModInitializer {
	public static final Random RAND = new Random();

	@Override
	public void onInitialize() {
	}

	public static int rand(int max) {
		return RAND.nextInt(max);
	}

	public static int randWidth() {
		return RAND.nextInt(300);
	}

	public static int randColor() {
		return RAND.nextInt(0xFFFFFF);
	}

	public static <T> void shuffle(T[] t) {
		List<T> list = new ArrayList<>(List.of(t));
		Collections.shuffle(list, RAND);
		T[] newT =  (T[]) list.toArray();
		System.arraycopy(newT, 0, t, 0, newT.length);
	}
}
