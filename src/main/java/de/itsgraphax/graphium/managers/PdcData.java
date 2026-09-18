package de.itsgraphax.graphium.managers;

import de.itsgraphax.grphxLib.utils.PdcDataBase;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static de.itsgraphax.graphium.Graphium.graphium;

public class PdcData extends PdcDataBase {
    public @Nullable NamespacedKey getCitemKey(@NotNull Entity e) {
        String key = pdc(e).get(graphium.ns().pdc.citemKey(),
                PersistentDataType.STRING);
        return key == null ? null : NamespacedKey.fromString(key);
    }

    public void setCitemKey(@NotNull Entity e, @NotNull NamespacedKey key) {
        pdc(e).set(graphium.ns().pdc.citemKey(),
                PersistentDataType.STRING, key.toString());
    }

    public @Nullable Long getLastFeatherLaunch(@NotNull Entity e) {
        return  pdc(e).get(graphium.ns().pdc.lastFeatherLaunch(),
                PersistentDataType.LONG);
    }

    public void setLastFeatherLaunch(@NotNull Entity e, @Nullable Long timestamp) {
        if (timestamp == null) pdc(e).remove(graphium.ns().pdc.lastFeatherLaunch());
        else pdc(e).set(graphium.ns().pdc.lastFeatherLaunch(),
                PersistentDataType.LONG, timestamp);
    }
}
