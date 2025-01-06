package net.dogika.secondmod.enchant;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.entity.EquipmentSlot;

public class HotFootedEnchant extends Enchantment {

	public HotFootedEnchant() {
		super(Rarity.RARE, EnchantmentTarget.ARMOR_FEET, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
	}
	@Override
	public int getMaxLevel() {
		return 3;
	}

	@Override
	protected boolean canAccept(Enchantment other) {
		if (other instanceof FrostWalkerEnchantment) return false;
		return super.canAccept(other);
	}

	@Override
	public boolean isAvailableForEnchantedBookOffer() {
		return false;
	}

	@Override
	public boolean isAvailableForRandomSelection() {
		return false;
	}
}
