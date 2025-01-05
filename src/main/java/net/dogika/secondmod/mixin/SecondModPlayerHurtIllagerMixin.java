package net.dogika.secondmod.mixin;

import net.dogika.secondmod.effect.ModEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.raid.RaiderEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RaiderEntity.class)
public abstract class SecondModPlayerHurtIllagerMixin {

    @Inject(
            method = "damage",
            at = @At(value = "HEAD")
    )
    private void hurtByPlayer(DamageSource damageSource, float amount, CallbackInfoReturnable<Boolean> cir) {
        Entity entity = damageSource.getAttacker();
        if (entity instanceof LivingEntity livingEntity) {
            livingEntity.removeStatusEffect(ModEffects.HERO_OF_THE_PILLAGE);
        }
    }
}