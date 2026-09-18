package de.itsgraphax.graphium.managers;

import de.itsgraphax.graphium.pads.PadHandler;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;

import java.util.Collection;

import static de.itsgraphax.graphium.Graphium.graphium;

public class Utils {
    public static void debugParticles(Location loc, Color c) {
        loc.getWorld().spawnParticle(Particle.DUST, loc, 1, new Particle.DustOptions(c, 1));
    }

    public static PadHandler getTouchingPad(Entity origin) {
        return getTouchingPad(origin, origin.getLocation().getNearbyEntitiesByType(ItemDisplay.class, 5));
    }

    public static PadHandler getTouchingPad(Entity origin, Collection<ItemDisplay> itemDisplays) {
        for (ItemDisplay display : itemDisplays) {
            PadHandler pad = PadHandler.resolve(display);

            if (pad != null) {
                if (display.getTicksLived() < 10*20) continue;

                if (origin instanceof Player p && p.getGameMode() == GameMode.CREATIVE) {
                    BoundingBox bb = pad.getBoundingBox();
                    Utils.debugParticles(bb.getMin().toLocation(origin.getWorld()), Color.RED);
                    Utils.debugParticles(bb.getMax().toLocation(origin.getWorld()), Color.BLUE);
                }

                if (pad.isTouching(origin.getBoundingBox())) return pad;
            }
        }
        return null;
    }

    public static void log(Object... objects) {
        for (Object o : objects) {
            graphium.getLogger().info(String.format("[LOG] %s", o));
        }
    }
}
