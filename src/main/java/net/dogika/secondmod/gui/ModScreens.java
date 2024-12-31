package net.dogika.secondmod.gui;

public class ModScreens {
    public static void registerScreens() {
        HandledScreens.register(ExampleMod.BOX_SCREEN_HANDLER, BoxScreen::new);

    }
}
