package net.dogika.secondmod.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.dogika.secondmod.enchant.ModEnchants;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(TridentEntity.class)
public class SecondModSecondCallThunderMixin {
	@Shadow private ItemStack tridentStack;

	@ModifyExpressionValue(
            method = "onEntityHit",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;isThundering()Z")
    )
	private boolean additionalConditions(boolean original) {
		return original || EnchantmentHelper.getLevel(ModEnchants.CALL_THUNDER, this.tridentStack) > 0;
	}
}