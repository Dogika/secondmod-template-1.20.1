package net.dogika.secondmod.mixin.raidinteractions;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.dogika.secondmod.access.SecondModHasKilledVillagerAccessor;
import net.dogika.secondmod.effect.ModEffects;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.raid.Raid;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;

@Mixin(Raid.class)
public abstract class SecondModRaidMixin {

    @Shadow @Final private Set<UUID> heroesOfTheVillage;

    @Shadow @Final private ServerWorld world;

    @Shadow protected abstract Predicate<ServerPlayerEntity> isInRaidDistance();

    @Inject(
            method = "tick",
            at = @At(value = "FIELD", target = "Lnet/minecraft/village/raid/Raid$Status;LOSS:Lnet/minecraft/village/raid/Raid$Status;")
    )
    private void giveNegativeRaidEffects(CallbackInfo ci) {

        List<ServerPlayerEntity> list = this.world.getPlayers(this.isInRaidDistance());

        for (ServerPlayerEntity playerEntity : list) {

            if (!playerEntity.isSpectator() && ((SecondModHasKilledVillagerAccessor) playerEntity).secondmod_template_1_20_1$getHasKilledVillager()) {

                playerEntity.sendMessage(Text.literal("You're getting the modded effect."));

                playerEntity.addStatusEffect(new StatusEffectInstance(ModEffects.HERO_OF_THE_PILLAGE, 48000, 1, false, false, true));
            }
        }
    }

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

    @Inject(
            method = "playRaidHorn",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayNetworkHandler;sendPacket(Lnet/minecraft/network/packet/Packet;)V  ")
    )
    private void resetPlayerKilledVillagerMixin(BlockPos pos, CallbackInfo ci, @Local(ordinal = 0)ServerPlayerEntity serverPlayerEntity) {
        (
                (SecondModHasKilledVillagerAccessor) serverPlayerEntity
        ).secondmod_template_1_20_1$setHasKilledVillager(false);
    }
}
