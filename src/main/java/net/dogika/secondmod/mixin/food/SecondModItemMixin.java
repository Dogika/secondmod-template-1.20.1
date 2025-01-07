package net.dogika.secondmod.mixin.food;

import net.dogika.secondmod.effect.ModEffects;
import net.dogika.secondmod.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class SecondModItemMixin extends Entity {
    public SecondModItemMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow public abstract boolean hasStatusEffect(StatusEffect effect);

    @Inject(
            method = "eatFood",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;playSound(Lnet/minecraft/entity/player/PlayerEntity;DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FF)V")
    )
    private void explodeIfContradictions(World world, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        if (this.hasStatusEffect(ModEffects.GOLD_FEVER) && isEmerald(stack) || this.hasStatusEffect(ModEffects.EMERALD_FEVER) && isGold(stack)) {
            Vec3d pos = this.getBlockPos().toCenterPos();
            world.createExplosion(null, world.getDamageSources().badRespawnPoint(pos), null, pos, 5.0F, true, World.ExplosionSourceType.BLOCK);
        }
    }

    @Unique
    private boolean isEmerald(ItemStack itemStack) {
        return itemStack.isOf(ModItems.EMERALD_CARROT) || itemStack.isOf(ModItems.EMERALD_APPLE) || itemStack.isOf(ModItems.ENCHANTED_EMERALD_APPLE);
    }

    @Unique
    private boolean isGold(ItemStack itemStack) {
        return itemStack.isOf(Items.GOLDEN_CARROT) || itemStack.isOf(Items.GOLDEN_APPLE) || itemStack.isOf(Items.ENCHANTED_GOLDEN_APPLE);
    }
}