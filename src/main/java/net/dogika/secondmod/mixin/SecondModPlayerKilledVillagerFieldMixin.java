package net.dogika.secondmod.mixin;

import net.dogika.secondmod.access.SecondModHasKilledVillagerAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VillagerEntity.class)
public abstract class SecondModPlayerKilledVillagerFieldMixin extends Entity{

    public SecondModPlayerKilledVillagerFieldMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(
            method = "onDeath",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/VillagerEntity;notifyDeath(Lnet/minecraft/entity/Entity;)V")
    )
    private void killedDuringRaidByPlayer(DamageSource damageSource, CallbackInfo ci) {
        Entity entity = damageSource.getAttacker();
        if (entity instanceof PlayerEntity playerEntity
                && this.getWorld() instanceof ServerWorld serverWorld
                && serverWorld.getRaidAt(entity.getBlockPos()) != null
        ) {
            playerEntity.sendMessage(Text.literal("You have killed a fucking villager."));
            (
                    (SecondModHasKilledVillagerAccessor) playerEntity
            ).secondmod_template_1_20_1$setHasKilledVillager(true);;
        }
    }
}
