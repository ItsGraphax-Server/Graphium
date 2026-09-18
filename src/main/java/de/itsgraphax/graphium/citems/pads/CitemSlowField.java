package de.itsgraphax.graphium.citems.pads;

import de.itsgraphax.graphium.pads.SlowField;

import static de.itsgraphax.graphium.Graphium.graphium;

public class CitemSlowField extends CitemPad {
    public CitemSlowField() {
        super(graphium.ns().citems.slowField(), SlowField::new);
    }
}
