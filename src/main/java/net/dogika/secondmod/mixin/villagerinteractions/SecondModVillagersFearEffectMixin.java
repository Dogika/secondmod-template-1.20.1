package net.dogika.secondmod.mixin.villagerinteractions;

import com.google.common.collect.ImmutableMap;
import com.llamalad7.mixinextras.injector.ModifyReceiver;
import net.dogika.secondmod.access.SecondModHasKilledVillagerAccessor;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.sensor.VillagerHostilesSensor;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VillagerHostilesSensor.class)
public class SecondModVillagersFearEffectMixin {
    @Inject(
            method = "isHostile",
            at = @At("HEAD"),
            cancellable = true
    )
    private void fearPlayersWithModdedEffect(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity instanceof PlayerEntity && !((SecondModHasKilledVillagerAccessor)entity).secondmod_template_1_20_1$getHasKilledVillager()) cir.setReturnValue(false);
    }


    @ModifyReceiver(
            method = "<clinit>",
            at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableMap$Builder;build()Lcom/google/common/collect/ImmutableMap;")
    )
    private static ImmutableMap.Builder<EntityType<?>, Float> addPlayerToSquaredDistancesForDangerMap(ImmutableMap.Builder<EntityType<?>, Float> instance) {
        instance.put(EntityType.PLAYER, 24.0F);
        return instance;
    }
}
