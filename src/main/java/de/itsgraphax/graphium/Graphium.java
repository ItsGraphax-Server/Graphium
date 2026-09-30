package de.itsgraphax.graphium;

import de.itsgraphax.graphium.citems.cosmetics.Cylinder;
import de.itsgraphax.graphium.citems.cosmetics.CylinderListener;
import de.itsgraphax.graphium.citems.cosmetics.GraphaxHorns;
import de.itsgraphax.graphium.citems.discs.DiscAceRace;
import de.itsgraphax.graphium.citems.pads.*;
import de.itsgraphax.graphium.citems.recipes.RecipeManager;
import de.itsgraphax.graphium.commands.DebugBrigadier;
import de.itsgraphax.graphium.managers.AdvancementListener;
import de.itsgraphax.graphium.managers.Namespaces;
import de.itsgraphax.graphium.managers.PdcData;
import de.itsgraphax.graphium.managers.PadManager;
import de.itsgraphax.grphxLib.citems.CitemListener;
import de.itsgraphax.grphxLib.citems.CitemManager;
import de.itsgraphax.grphxLib.shorthands.OnEnable;
import de.itsgraphax.grphxLib.utils.ResourcepackSender;
import de.itsgraphax.grphxLib.utils.RichText;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

// FAIL COUNTER: 6

public final class Graphium extends JavaPlugin {
    public static Graphium graphium;

    private final Namespaces ns;
    private final PdcData pdc;
    private final CitemManager cim = new CitemManager();

    private final RichText.RichConfigText rt = new RichText.RichConfigText(this);

    public Graphium() {
        super();

        graphium = this;
        ns = new Namespaces();
        pdc = new PdcData();

        cim.register(new CitemLaunchPad());
        cim.register(new CitemMegaLaunchPad());
        cim.register(new CitemJumpPad());
        cim.register(new CitemSpeedPad());
        cim.register(new CitemSlowField());
        cim.register(new CitemSoftPad());
        cim.register(new CitemAquaJet());
        cim.register(new DiscAceRace());
        cim.register(new Cylinder());
        cim.register(new GraphaxHorns());
    }

    @Override
    public void onEnable() {
        OnEnable.registerCommands(this,
                DebugBrigadier::register);

        PadManager padManager = new PadManager();
        AdvancementListener advancementListener = new AdvancementListener();

        OnEnable.registerEvents(
                this,
                padManager,
                new CitemListener(cim),
                new CylinderListener(),
                advancementListener,
                new ResourcepackSender("graphium", "1.0.0"));

        getServer().getScheduler().runTaskTimer(this, padManager::tickPlayers, 1, 1);
        getServer().getScheduler().runTaskTimer(this, padManager::tickPads, 1, 20);
        getServer().getScheduler().runTaskTimer(this, advancementListener::tick, 1, 1);

        RecipeManager.registerRecipes();
    }

    public CitemManager cim() {
        return cim;
    }
    public Namespaces ns() {
        return ns;
    }
    public PdcData pdc() {
        return pdc;
    }

    public RichText.RichConfigText rt() {
        return rt;
    }
}
