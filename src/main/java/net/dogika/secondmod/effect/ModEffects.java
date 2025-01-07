package net.dogika.secondmod.effect;

import net.dogika.secondmod.SecondMod;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;

public class ModEffects {
    public static StatusEffect HERO_OF_THE_PILLAGE = new StatusEffect(StatusEffectCategory.HARMFUL, 0xbb00bb) {@Override public boolean canApplyUpdateEffect(int duration, int amplifier) {return false;}};
    public static StatusEffect GOLD_FEVER = new StatusEffect(StatusEffectCategory.HARMFUL, 0xffd700) {@Override public boolean canApplyUpdateEffect(int duration, int amplifier) {return false;}};
    public static StatusEffect EMERALD_FEVER = new StatusEffect(StatusEffectCategory.HARMFUL, 0x50c878) {@Override public boolean canApplyUpdateEffect(int duration, int amplifier) {return false;}};

    public static void registerStatusEffects() {
        Registry.register(Registries.STATUS_EFFECT, new Identifier(SecondMod.MOD_ID, "hero_of_the_pillage"), HERO_OF_THE_PILLAGE);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(SecondMod.MOD_ID, "gold_fever"), GOLD_FEVER);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(SecondMod.MOD_ID, "emerald_fever"), EMERALD_FEVER);
    }
}