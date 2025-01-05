package net.dogika.secondmod.enchant;

import net.dogika.secondmod.mixin.SecondModLivingEntityAccessor;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

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
		addFrozenTicks(target);
		super.onTargetDamaged(user, target, level);
	}

	private void addFrozenTicks(@NotNull Entity target) {
		int frozenTicksAdded = 5 * (int) (
				(SecondModLivingEntityAccessor) target
		).getLastDamageTaken();

		target.setFrozenTicks(target.getFrozenTicks() + frozenTicksAdded);
	}
}