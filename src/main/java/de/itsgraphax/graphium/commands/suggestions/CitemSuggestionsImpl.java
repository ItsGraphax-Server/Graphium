package de.itsgraphax.graphium.commands.suggestions;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import de.itsgraphax.grphxLib.citems.Citem;
import io.papermc.paper.command.brigadier.CommandSourceStack;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

import static de.itsgraphax.graphium.Graphium.graphium;

public class CitemSuggestionsImpl {
    private static final Collection<Citem> SUGGESTIONS = graphium.cim().values();

    @CitemSuggestions
    public static CompletableFuture<Suggestions> provide(CommandContext<CommandSourceStack> ctx, SuggestionsBuilder builder) {
        SUGGESTIONS.stream()
                .filter(citem -> citem.key().toString().toLowerCase().startsWith(builder.getRemainingLowerCase()))
                .forEach(citem -> builder.suggest(citem.key().toString()));
        return builder.buildFuture();
    }
}