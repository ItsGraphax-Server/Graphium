package de.itsgraphax.graphium.pads;

import de.itsgraphax.graphium.managers.AdvancementListener;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import static de.itsgraphax.graphium.Graphium.graphium;

public class SoftPad extends PadHandler {
    public static final AttributeModifier softPadMod = new AttributeModifier(
            graphium.ns().attribute.softPad(),
            -1, AttributeModifier.Operation.MULTIPLY_SCALAR_1
    );

    public SoftPad(ItemDisplay entity) {
        super(entity);
    }

    @Override
    public Vector getDimensions() {
        return new Vector(1, 3, 1);
    }

    @Override
    public void onTick(Player p) {
        if (p.getFallDistance() > 2) {
            AttributeInstance attr = p.getAttribute(Attribute.FALL_DAMAGE_MULTIPLIER);
            assert attr != null;
            attr.addModifier(softPadMod);

            awardAceRaceAdvancement(p);
            AdvancementListener.award(p, "this_isnt_ar");
        }
    }
}
