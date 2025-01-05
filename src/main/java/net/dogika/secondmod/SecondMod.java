package net.dogika.secondmod;

import net.dogika.secondmod.effect.ModEffects;
import net.dogika.secondmod.enchant.ModEnchants;
import net.dogika.secondmod.event.ModEvents;
import net.dogika.secondmod.gui.ModScreenHandlers;
import net.dogika.secondmod.gui.ModScreens;
import net.dogika.secondmod.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecondMod implements ModInitializer {
	public static final String MOD_ID = "secondmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);



	@Override
	public void onInitialize() {
		ModScreenHandlers.registerScreenHandlers();
		ModItems.registerModItems();
		ModEvents.registerModEvents();
		ModScreens.registerScreens();
		ModEffects.registerStatusEffects();
		ModEnchants.registerEnchants();
	}
}