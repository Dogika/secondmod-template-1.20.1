package net.dogika.secondmod.item;

import net.dogika.secondmod.SecondMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {

    public static final Item CHEDDAR = registerItem("cheddar", new Item(new FabricItemSettings()));
    public static final Item PARMESAN = registerItem("parmesan", new Item(new FabricItemSettings()));

    public static final Item ENCHANTED_EMERALD_APPLE = registerItem("enchanted_emerald_apple", new Item(new FabricItemSettings().rarity(Rarity.EPIC).food(ModFoodComponents.ENCHANTED_EMERALD_APPLE)){
        @Override
        public boolean hasGlint(ItemStack stack) {
            return true;
        }
    });
    public static final Item EMERALD_APPLE = registerItem("emerald_apple", new Item(new FabricItemSettings().rarity(Rarity.RARE).food(ModFoodComponents.EMERALD_APPLE)));
    public static final Item EMERALD_CARROT = registerItem("emerald_carrot", new Item(new FabricItemSettings().food(ModFoodComponents.EMERALD_CARROT)));

    private static void addItemsToIngredientTabItemGroup(FabricItemGroupEntries entries) {
        entries.add(CHEDDAR);
        entries.add(PARMESAN);
        entries.add(ENCHANTED_EMERALD_APPLE);
        entries.add(EMERALD_APPLE);
        entries.add(EMERALD_CARROT);
    }

    private static Item registerItem(String name, Item item) {

        return Registry.register(Registries.ITEM, new Identifier(SecondMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        SecondMod.LOGGER.info("Registering Mod Items for: " + SecondMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(ModItems::addItemsToIngredientTabItemGroup);
    }
}