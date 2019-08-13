package com.infinitymine.arcade;

import com.infinitymine.arcade.system.OmegaPlugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class InfinityArcade extends OmegaPlugin {

    private static InfinityArcade instance;

    @Override
    public void enable() {
        // Plugin startup logic
        instance = this;

    }

    @Override
    public void disable() {
        // Plugin shutdown logic

    }

    public static InfinityArcade getInstance() {
        return instance;
    }
}
