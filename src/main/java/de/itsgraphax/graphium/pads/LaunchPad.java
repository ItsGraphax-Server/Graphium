package de.itsgraphax.graphium.pads;

import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import static de.itsgraphax.graphium.Graphium.graphium;

public class LaunchPad extends PadHandler {
    public LaunchPad(ItemDisplay entity) {
        super(entity);
    }

    @Override
    public Vector getDimensions() {
        return new Vector(3, 0.25, 3);
    }

    protected Vector setVelocity(double x, double z) {
        return new Vector(x * 2, 1, z * 2);
    }

    @Override
    public void onJump(Player p) {
        graphium.getServer().getScheduler().runTaskLater(graphium, () -> {
            double yawRad = Math.toRadians(p.getYaw());

            double x = -Math.sin(yawRad);
            double z = Math.cos(yawRad);

            p.setVelocity(setVelocity(x, z));

            // ff logic
            startFeatherLaunch(p);

            // ux logic
            playSound(p);
            awardAceRaceAdvancement(p);
        }, 2);
    }
}
