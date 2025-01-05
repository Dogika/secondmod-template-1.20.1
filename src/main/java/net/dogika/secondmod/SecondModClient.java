package net.dogika.secondmod;

import net.dogika.secondmod.gui.ModScreens;
import net.fabricmc.api.ClientModInitializer;

public class SecondModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModScreens.registerScreens();
    }
}
