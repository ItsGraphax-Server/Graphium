package de.itsgraphax.graphium.commands;

import de.itsgraphax.graphium.commands.suggestions.CitemSuggestions;
import de.itsgraphax.grphxLib.citems.Citem;
import net.strokkur.commands.Command;
import net.strokkur.commands.Executes;
import net.strokkur.commands.paper.Executor;
import net.strokkur.commands.paper.RequiresOP;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static de.itsgraphax.graphium.Graphium.graphium;

@Command("graphium")
@RequiresOP
public class Debug {
    @Executes("reload")
    void reload(CommandSender s) {
        graphium.reloadConfig();

        s.sendMessage(graphium.rt().parse("<green>Successfully reloaded the config!"));
    }

    @Executes("give")
    void give(@Executor Player s, @CitemSuggestions NamespacedKey citemKey) {
        // NamespacedKey citemKey = NamespacedKey.fromString(citemStringKey);

        if (citemKey == null) {
            return;
            // citemKey = new NamespacedKey(graphium, citemStringKey);
        }

        Citem citem = graphium.cim().get(citemKey);

        if (citem == null) {
            s.sendMessage(graphium.rt().parse("<red>This item was not found."));
            return;
        }

        s.give(citem.createItem());
    }
}
