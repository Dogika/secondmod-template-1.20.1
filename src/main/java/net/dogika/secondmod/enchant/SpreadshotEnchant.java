package net.dogika.secondmod.enchant;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.Vec3d;

import static java.lang.Math.*;

public class SpreadshotEnchant extends Enchantment {

	public SpreadshotEnchant() {
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
		double yaw = -user.prevPitch * 0.0349066;
		double pitch = -user.prevHeadYaw * 0.0174533;
		Vec3d playerVelocity = user.getVelocity();
		double magnitude = sqrt(playerVelocity.x * playerVelocity.x + playerVelocity.y * playerVelocity.y + playerVelocity.z * playerVelocity.z);
		double x = cos(yaw) * sin(pitch) * (level * 0.10 + 1) * magnitude;
		double y = -sin(yaw) * (level * 0.10 + 1) * magnitude;
		double z = cos(yaw) * cos(pitch)  * (level * 0.10 + 1) * magnitude;

		if(target instanceof LivingEntity) {
			int blackFlashSlam = 1;
			if (y < 0 && !target.isTouchingWater()) {
				user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 15 * 4 * level, level / 2));
				user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 15 * 4 * level, level / 2));
				user.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 10 * 4 * level, level * 2));
				blackFlashSlam = 50;
			}
			user.addVelocity(x, y * blackFlashSlam, z);
			user.velocityModified = true;
			target.addVelocity(x, y * blackFlashSlam, z);
		}
		super.onTargetDamaged(user, target, level);
	}
}
