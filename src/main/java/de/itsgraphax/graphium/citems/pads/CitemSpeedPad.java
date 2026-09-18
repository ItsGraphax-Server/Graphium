package de.itsgraphax.graphium.citems.pads;

import de.itsgraphax.graphium.pads.SpeedPad;

import static de.itsgraphax.graphium.Graphium.graphium;

public class CitemSpeedPad extends CitemPad {
    public CitemSpeedPad() {
        super(graphium.ns().citems.speedPad(), SpeedPad::new);
    }
}
