package net.dogika.secondmod.enchant;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.Vec3d;

import static java.lang.Math.*;

EntityAttributeModifier

public class FreezingEnchant extends Enchantment {

	public FreezingEnchant() {
		super(Rarity.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
	}
	@Override
	public int getMinPower(int level) {
		return 1;
	}
	@Override
	public int getMaxLevel() {
		return 5;
	}
	@Override
	public void onTargetDamaged(LivingEntity user, Entity target, int level) {
		target.setFrozenTicks(target.getFrozenTicks() + user.);
		super.onTargetDamaged(user, target, level);
	}
}
