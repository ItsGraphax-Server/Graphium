package de.itsgraphax.graphium.managers;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import de.itsgraphax.graphium.pads.PadHandler;
import de.itsgraphax.graphium.pads.SoftPad;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static de.itsgraphax.graphium.Graphium.graphium;

public class PadManager implements Listener {
    private static final AttributeModifier featherLaunchMod = new AttributeModifier(graphium.ns().attribute.featherLaunch(),
            -1, AttributeModifier.Operation.MULTIPLY_SCALAR_1);

    @EventHandler
    void onJump(PlayerJumpEvent e) {
        PadHandler pad = Utils.getTouchingPad(e.getPlayer());
        if (pad == null) return;

        pad.onJump(e.getPlayer());
    }

    public void tickPlayers() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            AttributeInstance fallDamageAttr = p.getAttribute(Attribute.FALL_DAMAGE_MULTIPLIER); assert fallDamageAttr != null;

            // Soft pad modifier
            fallDamageAttr.removeModifier(SoftPad.softPadMod);

            // Feather Launch
            Long lastFeatherLaunch = graphium.pdc().getLastFeatherLaunch(p);
            fallDamageAttr.removeModifier(featherLaunchMod);
            if (lastFeatherLaunch != null) {
                if (System.currentTimeMillis() - lastFeatherLaunch > 2000
                        && p.isOnGround()) {
                    graphium.pdc().setLastFeatherLaunch(p, null);
                } else {
                    fallDamageAttr.addModifier(featherLaunchMod);
                }
            }

            // Pad Handling
            PadHandler pad = Utils.getTouchingPad(p);
            if (pad == null) continue;

            pad.onTick(p);
        }
    }

    public void tickPads() {
        for (World w : Bukkit.getWorlds()) {
            for (Entity e : w.getEntities()) {
                if (e instanceof ItemDisplay id) {
                    PadHandler padHandler = PadHandler.resolve(id);
                    if (padHandler != null) {
                        padHandler.checkDestroy();
                    }
                }
            }
        }
    }
}
