package de.itsgraphax.graphium.pads;

import org.bukkit.Location;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.util.Vector;

public class LaunchPad extends PadHandler {
    public LaunchPad(ItemDisplay entity) {
        super(entity);
    }

    @Override
    public Vector getDimensions() {
        return new Vector(3, 0.25, 3);
    }

    protected Vector setVelocity(double x, double z) {
        return new Vector(x * 2, 0.8, z * 2);
    }

    @Override
    public void onJump(Player p) {
        // launch logic
        Location oLoc = p.getLocation();
        oLoc.add(0, 0.1, 0);
        p.teleport(oLoc, PlayerTeleportEvent.TeleportCause.PLUGIN);

        double yawRad = Math.toRadians(p.getYaw());

        double x = -Math.sin(yawRad);
        double z =  Math.cos(yawRad);

        p.setVelocity(setVelocity(x, z));

        // ff logic
        startFeatherLaunch(p);

        // ux logic
        playSound(p);
        awardAceRaceAdvancement(p);
    }
}
