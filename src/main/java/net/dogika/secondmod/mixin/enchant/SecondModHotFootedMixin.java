package net.dogika.secondmod.mixin.enchant;

import net.dogika.secondmod.enchant.ModEnchants;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(LivingEntity.class)
public abstract class SecondModHotFootedMixin {

    @Shadow public abstract ItemStack getEquippedStack(EquipmentSlot slot);

    @Shadow public abstract Random getRandom();

    @ModifyConstant(
            method = "travel",
            constant = @Constant(floatValue = 0.9F)
    )
    private float speedIfEnchant(float constant) {
        int level = EnchantmentHelper.getLevel(ModEnchants.HOT_FOOTED, getEquippedStack(EquipmentSlot.FEET));
        if (level > 0) return constant + 0.09F + (float)level * 0.001F;
        LivingEntity thisLivingEntity = (LivingEntity)(Object)this;
        thisLivingEntity.getWorld().addParticle(ParticleTypes.BUBBLE, thisLivingEntity.getX(), thisLivingEntity.getY(), thisLivingEntity.getZ(), 0, 0, 0);
        return constant;
    }
}
