package de.itsgraphax.graphium.citems.pads;

import de.itsgraphax.graphium.pads.JumpPad;

import static de.itsgraphax.graphium.Graphium.graphium;

public class CitemJumpPad extends CitemPad {
    public CitemJumpPad() {
        super(graphium.ns().citems.jumpPad(), JumpPad::new);
    }
}
