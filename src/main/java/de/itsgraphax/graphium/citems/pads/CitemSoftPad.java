package de.itsgraphax.graphium.citems.pads;

import de.itsgraphax.graphium.pads.SoftPad;

import static de.itsgraphax.graphium.Graphium.graphium;

public class CitemSoftPad extends CitemPad {
    public CitemSoftPad() {
        super(graphium.ns().citems.softPad(), SoftPad::new);
    }
}
