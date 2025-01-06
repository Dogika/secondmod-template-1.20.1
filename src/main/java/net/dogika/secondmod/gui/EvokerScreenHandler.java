package net.dogika.secondmod.gui;

import net.dogika.secondmod.enchant.ModEnchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.Property;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.random.Random;

import java.util.Map;

public class EvokerScreenHandler extends ScreenHandler {

    private final ScreenHandlerContext context;
    private static final Map<Enchantment,Enchantment> alternativeEnchantMap = Map.of(
            Enchantments.THORNS,      ModEnchants.BARRIER,
            Enchantments.CHANNELING,  ModEnchants.CALL_THUNDER,
            Enchantments.FROST_WALKER,ModEnchants.HOT_FOOTED,
            Enchantments.MULTISHOT,   ModEnchants.SPREADSHOT,
            Enchantments.FIRE_ASPECT, ModEnchants.FREEZING
    );
    private final Random random = Random.create();
    private final Property seed = Property.create();

    private final Inventory result = new CraftingResultInventory();
    public final SimpleInventory input = new SimpleInventory(2) {
        @Override
        public void markDirty() {
            super.markDirty();
            EvokerScreenHandler.this.onContentChanged(this);
        }
    };

    public EvokerScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }

    public EvokerScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(ModScreenHandlers.EVOKER_SCREEN_HANDLER, syncId);
        this.context = context;

        this.addSlot(new Slot(this.input, 0, 49, 19) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(Items.ENCHANTED_BOOK);
            }
        });
        this.addSlot(new Slot(this.result, 1, 129, 34) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }

            @Override
            public void onTakeItem(PlayerEntity player, ItemStack stack) {
                EvokerScreenHandler.this.input.setStack(0, ItemStack.EMPTY);
            }
        });

        //inventory
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        //hotbar
        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public void onContentChanged(Inventory inventory) {
        super.onContentChanged(inventory);
        if (inventory == this.input) {
            this.updateResult();
        }
    }

    private void updateResult() {
        ItemStack inputStack = this.input.getStack(0);
        ItemStack outputStack = new ItemStack(Items.ENCHANTED_BOOK);

        if (inputStack.isEmpty()) {
            this.result.setStack(0, ItemStack.EMPTY);
        } else {

            Map<Enchantment, Integer> enchantments = EnchantmentHelper.get(inputStack);

            enchantments.forEach(
                    (key, value) ->
                            EnchantedBookItem.addEnchantment(outputStack, new EnchantmentLevelEntry(this.convertEnchantment(key), value))
            );
            this.result.setStack(0, outputStack);
        }
        this.sendContentUpdates();
    }

    private Enchantment convertEnchantment(Enchantment key) {
        if (key.equals(Enchantments.SHARPNESS)) {
            float chance = random.nextFloat();
            if (chance < 0.1) return Enchantments.SHARPNESS;
            return ModEnchants.DULLNESS;
        }
        return alternativeEnchantMap.get(key);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) { 
        return null; // ignore..
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        this.context.run((world, pos) -> this.dropInventory(player, this.input));
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
