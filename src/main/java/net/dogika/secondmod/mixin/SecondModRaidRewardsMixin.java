package net.dogika.secondmod.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.dogika.secondmod.effect.ModEffects;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.village.raid.Raid;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;
import java.util.function.Predicate;

@Mixin(Raid.class)
public abstract class SecondModRaidRewardsMixin {

    @Shadow @Final private ServerWorld world;

    @Shadow protected abstract Predicate<ServerPlayerEntity> isInRaidDistance();

    @WrapOperation(
            method = "tick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/boss/ServerBossBar;setName(Lnet/minecraft/text/Text;)V", ordinal = 5)
    )
    private void additionalConditions(ServerBossBar instance, Text name, Operation<Void> original) {
        List<ServerPlayerEntity> list = this.world.getPlayers(isInRaidDistance());

        for (ServerPlayerEntity playerEntity : list) {
            if (playerEntity.hasStatusEffect(ModEffects.HERO_OF_THE_PILLAGE)) {
                original.call(instance, name.copy().append("?"));
                return;
            }
        }

        original.call(instance, name);
    }
}
