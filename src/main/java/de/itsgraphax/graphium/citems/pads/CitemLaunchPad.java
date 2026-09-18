package de.itsgraphax.graphium.citems.pads;

import de.itsgraphax.graphium.pads.LaunchPad;

import static de.itsgraphax.graphium.Graphium.graphium;

public class CitemLaunchPad extends CitemPad {
    public CitemLaunchPad() {
        super(graphium.ns().citems.launchPad(), LaunchPad::new);
    }
}
