package net.dogika.secondmod.enchant;

import net.dogika.secondmod.SecondMod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchants {

    public static final Enchantment BARRIER = registerEnchant("barrier", new BarrierEnchant());
    public static final Enchantment BLEEDING = registerEnchant("bleeding", new BleedingEnchant());
    public static final Enchantment CALL_THUNDER = registerEnchant("call_thunder", new CallThunderEnchant());
    //public static final Enchantment DROWNED_CURSE = registerEnchant("drowned_curse", new DrownedCurse());
    public static final Enchantment DULLNESS = registerEnchant("dullness", new DullnessEnchant());
    public static final Enchantment HOT_FOOTED = registerEnchant("hot_footed", new HotFootedEnchant());
    public static final Enchantment FREEZING = registerEnchant("freezing", new FreezingEnchant());
    //public static final Enchantment SHARING_CURSE = registerEnchant("sharing_curse", new SharingCurse());
    public static final Enchantment SPREADSHOT = registerEnchant("spreadshot", new SpreadshotEnchant());

    public static Enchantment registerEnchant(String name, Enchantment enchantment) {
        return Registry.register(Registries.ENCHANTMENT, new Identifier(SecondMod.MOD_ID, name), enchantment);
    }

    public static void registerEnchants() {
        SecondMod.LOGGER.info("Registering Mod Enchants for: " + SecondMod.MOD_ID);
    }

}