package net.dogika.secondmod.mixin;

import net.dogika.secondmod.effect.ModEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.IllagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class SecondModIllagerTeammateMixin {
    @Inject(
            method = "isTeammate",
            at = @At("HEAD"),
            cancellable = true
    )
    private void secondModTeammateIllager(Entity other, CallbackInfoReturnable<Boolean> cir) {
        if (((Object)this) instanceof IllagerEntity && other instanceof PlayerEntity && ((PlayerEntity)other).hasStatusEffect(ModEffects.HERO_OF_THE_PILLAGE)) {
            cir.setReturnValue(true);
        }
    }
}
