package net.dogika.secondmod.item;

import net.dogika.secondmod.effect.ModEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent ENCHANTED_EMERALD_APPLE = new FoodComponent.Builder()
            .hunger(5)
            .saturationModifier(1.2F)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 400, 2), 1.0F)
            .statusEffect(new StatusEffectInstance(StatusEffects.LUCK, 6000, 0), 1.0F)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 6000, 0), 1.0F)
            .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 3), 1.0F)
            .statusEffect(new StatusEffectInstance(ModEffects.EMERALD_FEVER, 1200, 0, false, false, true), 0.5F)
            .alwaysEdible()
            .build();
    public static final FoodComponent EMERALD_APPLE = new FoodComponent.Builder()
            .hunger(5)
            .saturationModifier(1.2F)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 100, 1), 1.0F)
            .statusEffect(new StatusEffectInstance(StatusEffects.LUCK, 6000, 0), 0.05F)
            .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 0), 1.0F)
            .statusEffect(new StatusEffectInstance(ModEffects.EMERALD_FEVER, 1200, 0, false, false, true), 1.0F)
            .alwaysEdible()
            .build();
    public static final FoodComponent EMERALD_CARROT = new FoodComponent.Builder()
            .hunger(8)
            .saturationModifier(1.2F)
            .statusEffect(new StatusEffectInstance(ModEffects.EMERALD_FEVER, 1200, 0, false, false, true), 1.0F)
            .build();
}
