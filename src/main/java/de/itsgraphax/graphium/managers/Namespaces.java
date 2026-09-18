package de.itsgraphax.graphium.managers;

import de.itsgraphax.grphxLib.utils.NamespacesBase;
import org.bukkit.NamespacedKey;

import static de.itsgraphax.graphium.Graphium.graphium;

public class Namespaces {
    public final CitemKeys citems = new CitemKeys();
    public final PdcKeys pdc = new PdcKeys();
    public final JukeboxKeys jukebox = new JukeboxKeys();
    public final SoundKeys sound = new SoundKeys();
    public final AttributeKeys attribute = new AttributeKeys();
    public final AdvancementKeys advancement = new AdvancementKeys();

    public static final class CitemKeys extends NamespacesBase {
        private CitemKeys() {
            super(graphium);
        }

        public NamespacedKey launchPad() {
            return key("launch_pad");
        }

        public NamespacedKey megaLaunchPad() {
            return key("mega_launch_pad");
        }

        public NamespacedKey speedPad() {
            return key("speed_pad");
        }

        public NamespacedKey slowField() {
            return key("slow_field");
        }

        public NamespacedKey softPad() {
            return key("soft_pad");
        }

        public NamespacedKey aquaJet() {
            return key("aqua_jet");
        }

        public NamespacedKey jumpPad() {
            return key("jump_pad");
        }

        public NamespacedKey discAceRace() {
            return key("disc_ace_race");
        }

        public NamespacedKey cylinder() {
            return key("cylinder");
        }
    }

    public static final class PdcKeys extends NamespacesBase {
        private PdcKeys() {
            super(graphium);
        }

        public NamespacedKey citemKey() {
            return key("citem_key");
        }

        /**
         * A value of null means that fall damage should not be negated
         * Any other value is a timestamp of when the last launch was and will be reset if more than a second has passed and one is on the ground
         */
        public NamespacedKey lastFeatherLaunch() {
            return key("last_feather_launch");
        }
    }

    public static final class JukeboxKeys extends NamespacesBase {
        private JukeboxKeys() {
            super(graphium);
        }

        public NamespacedKey aceRace() {
            return key("ace_race");
        }
    }

    public static final class SoundKeys extends NamespacesBase {
        private SoundKeys() {
            super(graphium);
        }

        public NamespacedKey pad(NamespacedKey citemKey) {
            return key(String.format("pads.%s", citemKey.value()));
        }
    }

    public static final class AttributeKeys extends NamespacesBase {
        private AttributeKeys() {
            super(graphium);
        }

        public NamespacedKey softPad() {
            return key("soft_pad");
        }

        public NamespacedKey featherLaunch() {
            return key("feather_launch");
        }
    }

    public static final class AdvancementKeys extends NamespacesBase {
        private AdvancementKeys() {
            super(graphium);
        }

        public NamespacedKey advancement(String key) {
            return this.key(key);
        }
    }
}
