package de.itsgraphax.graphium.pads;

import de.itsgraphax.graphium.citems.pads.CitemPad;
import de.itsgraphax.graphium.managers.AdvancementListener;
import de.itsgraphax.graphium.managers.Utils;
import de.itsgraphax.grphxLib.citems.Citem;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.sound.Sound;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.Set;

import static de.itsgraphax.graphium.Graphium.graphium;

public abstract class PadHandler {
    protected final ItemDisplay entity;

    public PadHandler(ItemDisplay entity) {
        this.entity = entity;
    }

    public static PadHandler resolve(ItemDisplay e) {
        NamespacedKey citemKey = graphium.pdc().getCitemKey(e);
        if (citemKey == null) return null;
        Citem citem = graphium.cim().get(citemKey);
        if (!(citem instanceof CitemPad citemPad)) return null;
        return citemPad.createPadHandler(e);
    }


    public Vector getDimensions() {
        return new Vector(1, 1, 1);
    }

    public BoundingBox getBoundingBox(Location loc, int rotation) {
        Vector rotated = getDimensions().rotateAroundY(Math.toRadians(rotation));

        Vector halfDimensions = rotated.clone().divide(new Vector(2, 2, 2));

        Location pos1 = loc.subtract(halfDimensions);
        Location pos2 = pos1.clone().add(rotated);

        return BoundingBox.of(pos1, pos2);
    }

    public BoundingBox getBoundingBox() {
        return getBoundingBox(entity.getLocation(), (int) entity.getYaw());
    }


    public boolean isTouching(BoundingBox bb) {
        return getBoundingBox().overlaps(bb);
    }


    public void checkDestroy() {
        Location loc = entity.getLocation();

        Block support = loc.clone().subtract(0, 1, 0).getBlock();
        Block suffocation = loc.getBlock();
        Set<ItemDisplay> itemDisplays = new HashSet<>(entity.getWorld()
                .getNearbyEntitiesByType(ItemDisplay.class, loc, 5));
        itemDisplays.remove(entity);
        PadHandler touching = Utils.getTouchingPad(entity, itemDisplays);

        if (((support.getType() != Material.AIR && suffocation.getType() == Material.AIR) ||
                (suffocation.getType() == Material.WATER))
                && touching == null) return; // Skip if checks all pass

        Citem citem = graphium.cim().get(graphium.pdc().getCitemKey(entity));
        if (citem == null) throw new RuntimeException();
        ItemStack item = citem.createItem();
        entity.getWorld().dropItemNaturally(loc, item);

        entity.remove();
    }


    public void onTick(Player p) {

    }

    public void onJump(Player p) {

    }


    protected void startFeatherLaunch(Player p) {
        graphium.pdc().setLastFeatherLaunch(p, System.currentTimeMillis());
    }

    protected void awardAceRaceAdvancement(Player p) {
        AdvancementListener.award(p, "ace_race");
    }

    protected void playSound(Player p) {
        NamespacedKey citemKey = graphium.pdc().getCitemKey(entity);
        assert citemKey != null;
        NamespacedKey soundKey = graphium.ns().sound.pad(citemKey);
        Audience.audience(p).playSound(Sound.sound(soundKey, Sound.Source.MASTER, 1f, 1f));
    }
}
