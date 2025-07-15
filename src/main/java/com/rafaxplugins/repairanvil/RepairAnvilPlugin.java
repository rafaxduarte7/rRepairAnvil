package com.rafaxplugins.repairanvil;

import com.rafaxplugins.repairanvil.listeners.InteractListener;
import com.rafaxplugins.repairanvil.loaders.ItemsLoader;
import com.rafaxplugins.repairanvil.misc.config.ConfigBase;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class RepairAnvilPlugin extends JavaPlugin {

    @Getter
    private static RepairAnvilPlugin instance;

    @Getter
    private static ConfigBase itemsConfig;

    @Getter
    private static ConfigBase messagesConfig;

    @Override
    public void onEnable() {
        instance = this;

        itemsConfig = new ConfigBase(this, "items.yml");
        messagesConfig = new ConfigBase(this, "messages.yml");

        ItemsLoader.INSTANCE.load();

        Bukkit.getPluginManager().registerEvents(new InteractListener(), this);
    }

}