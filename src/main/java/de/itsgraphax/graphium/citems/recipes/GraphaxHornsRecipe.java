package de.itsgraphax.graphium.citems.recipes;

import de.itsgraphax.grphxLib.citems.Citem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import static de.itsgraphax.graphium.Graphium.graphium;

public class GraphaxHornsRecipe {
    public static void register() {
        Citem horns = graphium.cim().get(graphium.ns().citems.graphaxHorns());
        assert horns != null;

        ItemStack result = horns.createItem();

        ShapedRecipe recipe = new ShapedRecipe(graphium.ns().citems.graphaxHorns(), result);
        recipe.shape("bho");
        recipe.setIngredient('b', Material.BLUE_DYE);
        recipe.setIngredient('o', Material.ORANGE_DYE);
        recipe.setIngredient('h', Material.GOAT_HORN);

        graphium.getServer().addRecipe(recipe);
    }
}
