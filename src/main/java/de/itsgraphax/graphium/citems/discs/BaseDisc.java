package de.itsgraphax.graphium.citems.discs;

import de.itsgraphax.grphxLib.citems.Citem;
import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.JukeboxPlayable;
import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import org.bukkit.JukeboxSong;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class BaseDisc extends Citem {
    public BaseDisc(NamespacedKey citemKey, NamespacedKey jukeboxKey) {
        super(citemKey);

        ItemStack defaultItem = createItem();

        Registry<@NotNull JukeboxSong> registry = RegistryAccess.registryAccess()
                .getRegistry(RegistryKey.JUKEBOX_SONG);
        JukeboxSong song = registry.get(jukeboxKey);
        if (song == null) throw new RuntimeException("Could not get song");

        defaultItem.setData(DataComponentTypes.JUKEBOX_PLAYABLE,
                JukeboxPlayable.jukeboxPlayable(song).build());

        setItem(defaultItem);
    }
}
