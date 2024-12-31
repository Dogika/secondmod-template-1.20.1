package net.dogika.secondmod;

import net.dogika.secondmod.effect.ModEffects;
import net.dogika.secondmod.gui.EvokerScreenHandler;
import net.dogika.secondmod.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.minecraft.screen.ScreenHandlerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecondMod implements ModInitializer {
	public static final String MOD_ID = "secondmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final ScreenHandlerType<EvokerScreenHandler> EVOKER_SCREEN_HANDLER;
	@Override
	public void onInitialize() {
		ModItems.registerModItems();
	}
}