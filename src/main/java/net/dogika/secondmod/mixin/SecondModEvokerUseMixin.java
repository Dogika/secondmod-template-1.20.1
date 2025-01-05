package net.dogika.secondmod.mixin;

import net.dogika.secondmod.effect.ModEffects;
import net.dogika.secondmod.event.EvokerUseCallback;
import net.minecraft.entity.mob.EvokerEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEntity.class)
public class SecondModEvokerUseMixin {

    @Inject(
            method = "interactMob",
            at = @At("HEAD")
    )
    private void secondModUseEvoker(PlayerEntity player, Hand hand, final CallbackInfoReturnable<Boolean> info) {
        if (((Object)this) instanceof EvokerEntity && player.hasStatusEffect(ModEffects.HERO_OF_THE_PILLAGE)) {
            ActionResult result = EvokerUseCallback.EVENT.invoker().interact(player, player.getWorld(), hand, (EvokerEntity)(Object)this, null);

            if (result == ActionResult.FAIL) {
                info.cancel();
            }
        }
    }
}
