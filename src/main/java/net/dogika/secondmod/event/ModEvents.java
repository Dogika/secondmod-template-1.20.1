package net.dogika.secondmod.event;

import net.dogika.secondmod.SecondMod;
import net.dogika.secondmod.gui.EvokerScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

public class ModEvents {
    public static void registerModEvents() {
        SecondMod.LOGGER.info("Registering Mod Events for: " + SecondMod.MOD_ID);

        EvokerUseCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            player.sendMessage(Text.literal("Evoker Use Event called."), true);

            if (!world.isClient) {
                player.openHandledScreen(
                        new SimpleNamedScreenHandlerFactory((syncId, playerInventory, playerx) ->
                                new EvokerScreenHandler(
                                        syncId,
                                        playerInventory,
                                        ScreenHandlerContext.create(world, entity.getBlockPos())
                                ), entity.getDisplayName())
                );
            }

            return ActionResult.PASS;
        });
    }
}
