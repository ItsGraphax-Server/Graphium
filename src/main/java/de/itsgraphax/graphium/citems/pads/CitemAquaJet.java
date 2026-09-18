package de.itsgraphax.graphium.citems.pads;

import de.itsgraphax.graphium.pads.AquaJet;

import static de.itsgraphax.graphium.Graphium.graphium;

public class CitemAquaJet extends CitemPad {
    public CitemAquaJet() {
        super(graphium.ns().citems.aquaJet(), AquaJet::new);
    }
}
