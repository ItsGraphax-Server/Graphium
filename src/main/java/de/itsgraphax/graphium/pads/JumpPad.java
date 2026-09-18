package de.itsgraphax.graphium.pads;

import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class JumpPad extends PadHandler {
    public JumpPad(ItemDisplay entity) {
        super(entity);
    }

    @Override
    public Vector getDimensions() {
        return new Vector(3, 0.25, 3);
    }

    @Override
    public void onTick(Player p) {
        p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 5, 7));
    }

    @Override
    public void onJump(Player p) {
        startFeatherLaunch(p);

        awardAceRaceAdvancement(p);
        playSound(p);
    }
}
