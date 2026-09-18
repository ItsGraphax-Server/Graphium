package de.itsgraphax.graphium.pads;

import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class SpeedPad extends PadHandler {
    public SpeedPad(ItemDisplay entity) {
        super(entity);
    }

    @Override
    public Vector getDimensions() {
        return new Vector(1, 0.25, 1);
    }

    @Override
    public void onTick(Player p) {
        if (!p.hasPotionEffect(PotionEffectType.SPEED)) {
            playSound(p);
            awardAceRaceAdvancement(p);
        }
        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 2));
    }
}
