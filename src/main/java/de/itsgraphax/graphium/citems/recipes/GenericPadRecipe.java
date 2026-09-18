package de.itsgraphax.graphium.citems.recipes;

import de.itsgraphax.grphxLib.citems.Citem;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import static de.itsgraphax.graphium.Graphium.graphium;

public class GenericPadRecipe {
    public static void register(Material concrete, Material special, NamespacedKey citemKey, int amount) {
        Citem pad = graphium.cim().get(citemKey);
        assert pad != null;

        ItemStack result = pad.createItem();
        result.setAmount(amount);

        ShapedRecipe recipe = new ShapedRecipe(citemKey, result);
        recipe.shape(
                " p ",
                "ccc",
                "rsr"
        );
        recipe.setIngredient('p', Material.LIGHT_WEIGHTED_PRESSURE_PLATE);
        recipe.setIngredient('c', concrete);
        recipe.setIngredient('r', Material.REDSTONE);
        recipe.setIngredient('s', special);

        graphium.getServer().addRecipe(recipe);
    }
}
