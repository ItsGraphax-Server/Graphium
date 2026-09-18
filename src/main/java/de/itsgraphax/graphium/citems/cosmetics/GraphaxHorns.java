package de.itsgraphax.graphium.citems.cosmetics;

import de.itsgraphax.grphxLib.citems.Citem;
import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.Equippable;
import io.papermc.paper.datacomponent.item.ItemAttributeModifiers;
import net.kyori.adventure.key.Key;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;

import static de.itsgraphax.graphium.Graphium.graphium;

public class GraphaxHorns extends Citem {
    public GraphaxHorns() {
        super(graphium.ns().citems.cylinder());

        ItemStack defaultItem = createItem();
        defaultItem.setData(DataComponentTypes.EQUIPPABLE, Equippable
                .equippable(EquipmentSlot.HEAD)
                .damageOnHurt(false)
                .equipSound(Key.key("entity.evocation_illager.fangs"))
                .build());
        defaultItem.setData(DataComponentTypes.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers
                .itemAttributes()
                        .addModifier(Attribute.WAYPOINT_TRANSMIT_RANGE,
                                new AttributeModifier(graphium.ns().attribute.hideHelmetWaypoint(),
                                        -1,
                                        AttributeModifier.Operation.MULTIPLY_SCALAR_1),
                                EquipmentSlotGroup.HEAD)
                .build());
        setItem(defaultItem);
    }
}
