package net.dogika.secondmod.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

/**
 * Callback for interacting with an Evoker
 * Called before any ui is opened.
 */
public interface EvokerUseCallback {

    Event<net.fabricmc.fabric.api.event.player.UseEntityCallback> EVENT = EventFactory.createArrayBacked(net.fabricmc.fabric.api.event.player.UseEntityCallback.class, (listeners) -> (player, world, hand, entity, hitResult) -> {
        for(net.fabricmc.fabric.api.event.player.UseEntityCallback event : listeners) {
            ActionResult result = event.interact(player, world, hand, entity, hitResult);
            if (result != ActionResult.PASS) {
                return result;
            }
        }

        return ActionResult.PASS;
    });

    ActionResult interact(PlayerEntity var1, World var2, Hand var3, Entity var4, @Nullable EntityHitResult var5);
}
