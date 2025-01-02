package net.dogika.secondmod.effect;

import net.dogika.secondmod.SecondMod;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;

public class ModEffects {
    public static StatusEffect HERO_OF_THE_PILLAGE = new HeroOfThePillageEffect(StatusEffectCategory.HARMFUL, 0xbb00bb);

    public static void registerStatusEffects() {
        Registry.register(Registries.STATUS_EFFECT, new Identifier(SecondMod.MOD_ID, "hero_of_the_pillage"),
                HERO_OF_THE_PILLAGE);
    }
}
