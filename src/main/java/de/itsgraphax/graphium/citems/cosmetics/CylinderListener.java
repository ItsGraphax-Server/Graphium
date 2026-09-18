package de.itsgraphax.graphium.citems.cosmetics;

import de.itsgraphax.grphxLib.citems.Citem;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import static de.itsgraphax.graphium.Graphium.graphium;

public class CylinderListener implements Listener {
    @EventHandler
    void onZombieSpawn(EntitySpawnEvent e) {
        if (e.getEntity() instanceof Zombie zombie
                && zombie.getType() == EntityType.ZOMBIE
                && Math.random() < 0.0407) {
            Citem cylinder = graphium.cim().get(graphium.ns().citems.cylinder());
            assert cylinder != null;
            zombie.getEquipment().setHelmet(cylinder.createItem());
            zombie.getEquipment().setHelmetDropChance(0.9f);
        }
    }
}
