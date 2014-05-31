package qmech.lib.util;


import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;

public class RecipeHelper {
    public static void shapedRecipe(ItemStack output, Object... recipePars) {
        GameRegistry.addRecipe(output, recipePars);
    }

    public static void shapelessRecipe(ItemStack output, Object... recipePars) {
        GameRegistry.addShapelessRecipe(output, recipePars);
    }

    public static void smeltingRecipe(ItemStack output, ItemStack input, float xp) {
        GameRegistry.addSmelting(input, output, xp);
    }
}
