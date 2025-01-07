package net.dogika.secondmod.mixin.food;

import com.llamalad7.mixinextras.injector.ModifyReceiver;
import net.dogika.secondmod.effect.ModEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.FoodComponents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FoodComponents.class)
public class SecondModItemsMixin {
    @ModifyReceiver(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/FoodComponent$Builder;build()Lnet/minecraft/item/FoodComponent;", ordinal = 19))
    private static FoodComponent.Builder addGoldFeverToEnchantedGoldenApple(FoodComponent.Builder instance) {
        return instance.statusEffect(new StatusEffectInstance(ModEffects.GOLD_FEVER, 1200, 0, false, false, true), 1.0F);
    }

    @ModifyReceiver(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/FoodComponent$Builder;build()Lnet/minecraft/item/FoodComponent;", ordinal = 20))
    private static FoodComponent.Builder addGoldFeverToGoldenApple(FoodComponent.Builder instance) {
        return instance.statusEffect(new StatusEffectInstance(ModEffects.GOLD_FEVER, 1200, 0, false, false, true), 1.0F);
    }

    @ModifyReceiver(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/FoodComponent$Builder;build()Lnet/minecraft/item/FoodComponent;", ordinal = 21))
    private static FoodComponent.Builder addGoldFeverToGoldenCarrot(FoodComponent.Builder instance) {
        return instance.statusEffect(new StatusEffectInstance(ModEffects.GOLD_FEVER, 1200, 0, false, false, true), 1.0F);
    }
}
