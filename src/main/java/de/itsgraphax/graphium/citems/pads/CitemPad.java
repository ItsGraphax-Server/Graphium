package de.itsgraphax.graphium.citems.pads;

import de.itsgraphax.graphium.pads.PadHandler;
import de.itsgraphax.grphxLib.citems.Citem;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

import static de.itsgraphax.graphium.Graphium.graphium;

public abstract class CitemPad extends Citem {
    private static final float SNAP_ANGLE = 45f;

    protected final Function<ItemDisplay, PadHandler> def;

    public CitemPad(NamespacedKey key, Function<ItemDisplay, PadHandler> def) {
        super(key);

        this.def = def;
    }

    @Override
    public void onInteract(@NotNull PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Block block = event.getClickedBlock();
        if (block == null) return;

        BlockFace face = event.getBlockFace();

        Location loc = block.getLocation().add(
                0.5 + face.getModX(),
                         face.getModY(),
                0.5 + face.getModZ()
        );

        Player p = event.getPlayer();
        int rotation = Math.round(
                p.getLocation().getYaw() / SNAP_ANGLE
        ) * (int) SNAP_ANGLE;

        rotation = Math.floorMod(rotation, 360);

        consume(event);
        spawn(loc, rotation);
        EquipmentSlot slot = event.getHand();
        if (slot != null) p.swingHand(slot);
        p.getWorld().playSound(p.getLocation(), Sound.BLOCK_WOOL_PLACE, 1, 1);
    }

    public void spawn(Location loc, int rotation) {
        Location clone = loc.clone();
        clone.setYaw(rotation);
        ItemDisplay display = clone.getWorld().spawn(clone, ItemDisplay.class);

        display.setItemStack(createItem());
        graphium.pdc().setCitemKey(display, key());
    }


    public PadHandler createPadHandler(ItemDisplay entity) {
        return def.apply(entity);
    }
}
