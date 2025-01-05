package net.dogika.secondmod.mixin;

import net.dogika.secondmod.access.SecondModHasKilledVillagerAccessor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(PlayerEntity.class)
public abstract class SecondModHasKilledVillagerMixin implements SecondModHasKilledVillagerAccessor {

    @Shadow public abstract void sendMessage(Text message, boolean overlay);

    @Unique
    boolean hasKilledVillager;

    @Override
    public boolean secondmod_template_1_20_1$getHasKilledVillager() {
        this.sendMessage(Text.literal("hasKilledVillager was gotten."), true);
        return hasKilledVillager;
    }
    @Override
    public void secondmod_template_1_20_1$setHasKilledVillager(boolean set) {
        this.sendMessage(Text.literal("hasKilledVillager was setted."), true);
        hasKilledVillager = set;
    }
}

