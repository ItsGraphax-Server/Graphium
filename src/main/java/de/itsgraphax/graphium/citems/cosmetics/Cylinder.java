package de.itsgraphax.graphium.citems.cosmetics;

import de.itsgraphax.grphxLib.citems.Citem;
import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.Equippable;
import net.kyori.adventure.key.Key;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import static de.itsgraphax.graphium.Graphium.graphium;

public class Cylinder extends Citem {
    public Cylinder() {
        super(graphium.ns().citems.cylinder());

        ItemStack defaultItem = createItem();
        defaultItem.setData(DataComponentTypes.EQUIPPABLE, Equippable
                .equippable(EquipmentSlot.HEAD)
                .damageOnHurt(false)
                .equipSound(Key.key("item.armor.equip_leather"))
                .build());
        setItem(defaultItem);
    }
}
