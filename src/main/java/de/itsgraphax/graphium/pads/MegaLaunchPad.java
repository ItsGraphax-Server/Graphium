package de.itsgraphax.graphium.pads;

import de.itsgraphax.graphium.managers.AdvancementListener;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class MegaLaunchPad extends LaunchPad {
    public MegaLaunchPad(ItemDisplay entity) {
        super(entity);
    }

    @Override
    protected Vector setVelocity(double x, double z) {
        return new Vector(x * 3.5, 1.5, z * 3.5);
    }

    @Override
    public void onJump(Player p) {
        super.onJump(p);

        AdvancementListener.award(p, "an_upgrade");
    }
}
