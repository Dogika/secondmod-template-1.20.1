package net.dogika.secondmod.entity;

import net.dogika.secondmod.effect.ModEffects;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.EntityType;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;


public class EvokerEnchantmentConversion {

    public static void registerEvokerInteractions() {
        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (
                    !player.isSpectator()
                    && entity.getType().equals(EntityType.EVOKER)
                    && player.hasStatusEffect(ModEffects.HERO_OF_THE_PILLAGE)
            ) {
                if (!world.isClient) {
                    NamedScreenHandlerFactory screenHandlerFactory = //createScreenHandlerFactoryImplementation();
                }
            }
            return ActionResult.SUCCESS;
        });
    }
}
