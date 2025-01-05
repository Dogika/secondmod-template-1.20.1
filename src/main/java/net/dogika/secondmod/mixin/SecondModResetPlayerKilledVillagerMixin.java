package net.dogika.secondmod.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.dogika.secondmod.access.SecondModHasKilledVillagerAccessor;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.raid.Raid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Raid.class)
public class SecondModResetPlayerKilledVillagerMixin {

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