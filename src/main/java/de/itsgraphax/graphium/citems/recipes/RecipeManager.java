package de.itsgraphax.graphium.citems.recipes;

import org.bukkit.Material;

import static de.itsgraphax.graphium.Graphium.graphium;

public class RecipeManager {
    public static void registerRecipes() {
        // Launch Pad
        GenericPadRecipe.register(Material.RED_CONCRETE, Material.SLIME_BALL,
                graphium.ns().citems.launchPad(), 2);
        // Mega Launch Pad
        GenericPadRecipe.register(Material.ORANGE_CONCRETE, Material.SLIME_BLOCK,
                graphium.ns().citems.megaLaunchPad(), 2);
        // Jump Pad
        GenericPadRecipe.register(Material.GREEN_CONCRETE, Material.IRON_INGOT,
                graphium.ns().citems.jumpPad(), 2);
        // Speed Pad
        GenericPadRecipe.register(Material.YELLOW_CONCRETE, Material.SUGAR,
                graphium.ns().citems.speedPad(), 18);
        // Slowness Field
        GenericPadRecipe.register(Material.SOUL_SAND, Material.VINE,
                graphium.ns().citems.slowField(), 18);
        // Soft Pad
        GenericPadRecipe.register(Material.BLUE_CONCRETE, Material.FEATHER,
                graphium.ns().citems.softPad(), 2);
        // Aqua Jet
        AquaJetRecipe.register();

        // Graphax' Horns
        GraphaxHornsRecipe.register();
    }
}
