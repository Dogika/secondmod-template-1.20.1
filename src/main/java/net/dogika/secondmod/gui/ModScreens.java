package net.dogika.secondmod.gui;

import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class ModScreens {
    public static void registerScreens() {
        HandledScreens.register(ModScreenHandlers.EVOKER_SCREEN_HANDLER, EvokerScreen::new);
    }
}
