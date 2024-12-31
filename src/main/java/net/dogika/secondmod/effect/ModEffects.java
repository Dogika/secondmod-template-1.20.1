package net.dogika.secondmod.effect;

import net.dogika.secondmod.SecondMod;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;

public class ModEffects {
    public static StatusEffect HERO_OF_THE_PILLAGE = registerStatusEffect("hero_of_the_pillage");

    public static StatusEffect registerStatusEffect(String name) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(SecondMod.MOD_ID, name),
            new HeroOfThePillageEffect(StatusEffectCategory.HARMFUL, 0xbb00bb));
    }
}
