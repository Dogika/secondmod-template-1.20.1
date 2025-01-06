package net.dogika.secondmod.enchant;

import net.minecraft.enchantment.ChannelingEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.entity.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import static java.lang.Math.*;

public class CallThunderEnchant extends Enchantment {

	public CallThunderEnchant() {
		super(Rarity.RARE, EnchantmentTarget.TRIDENT, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
	}

    @Override
	protected boolean canAccept(Enchantment other) {
		if (other instanceof ChannelingEnchantment) return false;
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
