package net.dogika.secondmod.enchant;

import net.dogika.secondmod.SecondMod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchants {

    public static final Enchantment BARRIER = registerEnchant("barrier", new BarrierEnchant());
    public static final Enchantment BLEEDING = registerEnchant("bleeding", new BarrierEnchant());
    public static final Enchantment CALL_THUNDER = registerEnchant("call_thunder", new BarrierEnchant());
    public static final Enchantment DROWNED_CURSE = registerEnchant("drowned_curse", new BarrierEnchant());
    public static final Enchantment DULLNESS = registerEnchant("dullness", new BarrierEnchant());
    public static final Enchantment FIREWALKER = registerEnchant("firewalker", new BarrierEnchant());
    public static final Enchantment FREEZING = registerEnchant("freezing", new BarrierEnchant());
    public static final Enchantment SHARING_CURSE = registerEnchant("sharing_curse", new BarrierEnchant());
    public static final Enchantment SPREADSHOT = registerEnchant("spreadshot", new BarrierEnchant());

    public static Enchantment registerEnchant(String name, Enchantment enchantment) {
        return Registry.register(Registries.ENCHANTMENT, new Identifier(SecondMod.MOD_ID, name), enchantment);
    }
}