package de.itsgraphax.graphium.citems.pads;

import de.itsgraphax.graphium.pads.MegaLaunchPad;

import static de.itsgraphax.graphium.Graphium.graphium;

public class CitemMegaLaunchPad extends CitemPad {
    public CitemMegaLaunchPad() {
        super(graphium.ns().citems.megaLaunchPad(), MegaLaunchPad::new);
    }
}
