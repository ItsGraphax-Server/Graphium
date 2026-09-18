package de.itsgraphax.graphium.managers;

import de.itsgraphax.graphium.citems.cosmetics.Cylinder;
import de.itsgraphax.graphium.citems.pads.CitemPad;
import de.itsgraphax.grphxLib.citems.Citem;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import static de.itsgraphax.graphium.Graphium.graphium;

public class AdvancementListener implements Listener {
    public static Advancement getAdvancement(String stringKey) {
        NamespacedKey key = graphium.ns().advancement.advancement(stringKey);
        Advancement advancement = graphium.getServer().getAdvancement(key);
        if (advancement == null)
            throw new RuntimeException(String.format("Could not get advancement: %s", key.asString()));
        return advancement;
    }

    public static void award(Player p, String stringKey) {
        Advancement advancement = getAdvancement(stringKey);
        AdvancementProgress progress = p.getAdvancementProgress(advancement);
        if (progress.isDone()) return;
        for (String c : progress.getRemainingCriteria()) {
            progress.awardCriteria(c);
        }
    }

    public void tick() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            fashionButBetter(p);
        }
    }

    void fashionButBetter(Player p) {
        Citem helmetCitem = graphium.cim().fromItem(p.getInventory().getHelmet());
        if (helmetCitem instanceof Cylinder) {
            award(p, "fashion_but_better");
        }
    }

    @EventHandler
    void missionFailedSuccessfully(EntityDeathEvent e) {
        if (!(e.getEntity() instanceof Zombie zombie)
                || !(e.getDamageSource().getCausingEntity() instanceof Player p)) return;

        ItemStack helmet = zombie.getEquipment().getHelmet();
        Citem helmetCitem = graphium.cim().fromItem(helmet);

        Utils.log(helmetCitem);

        if (helmetCitem == null ||
                !helmetCitem.key().equals(graphium.ns().citems.cylinder())) return;

        for (ItemStack drop : e.getDrops()) {
            Citem dropCitem = graphium.cim().fromItem(drop);

            if (dropCitem == null ||
                !helmetCitem.key().equals(graphium.ns().citems.cylinder())) continue;


            award(p, "mission_failed_successfully");
            return;
        }

    }

    @EventHandler
    void footRace(CraftItemEvent e) {
        Citem resultCitem = graphium.cim().fromItem(e.getRecipe().getResult());
        if (resultCitem instanceof CitemPad && e.getView().getPlayer() instanceof Player p) {
            if (!p.getAdvancementProgress(getAdvancement("foot_race")).isDone()) {
                Citem disc = graphium.cim().get(graphium.ns().citems.discAceRace());
                assert disc != null;
                p.give(disc.createItem());
            }

            award(p, "foot_race");
        }
    }
}
