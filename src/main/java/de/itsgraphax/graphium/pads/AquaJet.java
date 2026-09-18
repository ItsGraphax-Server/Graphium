package de.itsgraphax.graphium.pads;

import de.itsgraphax.graphium.managers.AdvancementListener;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class AquaJet extends PadHandler {
    public AquaJet(ItemDisplay entity) {
        super(entity);
    }

    @Override
    public Vector getDimensions() {
        return new Vector(1, 1, 0.25);
    }

    protected Vector setVelocity(Vector direction) {
        return direction.multiply(new Vector(1.5, 0.5, 1.5));
    }

    @Override
    public void onTick(Player p) {
        Vector direction = p.getLocation().getDirection();

        p.setVelocity(setVelocity(direction));

        playSound(p);
        awardAceRaceAdvancement(p);
        AdvancementListener.award(p, "aquatised");
    }
}
