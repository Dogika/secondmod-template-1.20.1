package net.dogika.secondmod.mixin;

import net.dogika.secondmod.access.SecondModHasKilledVillagerAccessor;
import net.dogika.secondmod.effect.ModEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
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
public abstract class SecondModNegativeRaidEffectsMixin {
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
}
