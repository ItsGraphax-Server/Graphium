package de.itsgraphax.graphium.citems.recipes;

import de.itsgraphax.grphxLib.citems.Citem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import static de.itsgraphax.graphium.Graphium.graphium;

public class AquaJetRecipe {
    public static void register() {
        Citem pad = graphium.cim().get(graphium.ns().citems.aquaJet());
        assert pad != null;

        ItemStack result = pad.createItem();
        result.setAmount(1);

        ShapedRecipe recipe = new ShapedRecipe(graphium.ns().citems.aquaJet(), result);
        recipe.shape(
                "crc",
                "r r",
                "crc"
        );
        recipe.setIngredient('c', Material.LIGHT_BLUE_CONCRETE);
        recipe.setIngredient('r', Material.REDSTONE);

        graphium.getServer().addRecipe(recipe);
    }
}
