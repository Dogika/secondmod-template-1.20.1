package net.dogika.secondmod.gui;

import net.dogika.secondmod.SecondMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static ScreenHandlerType<EvokerScreenHandler> EVOKER_SCREEN_HANDLER = new ScreenHandlerType<>(EvokerScreenHandler::new, FeatureSet.empty());

    public static void registerScreenHandlers() {
        SecondMod.LOGGER.info("Registering Mod Screen Handlers for: " + SecondMod.MOD_ID);

        EVOKER_SCREEN_HANDLER = Registry.register(
                Registries.SCREEN_HANDLER,
                new Identifier(SecondMod.MOD_ID, "evoker"),
                EVOKER_SCREEN_HANDLER
        );
    }
}
