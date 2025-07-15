package com.rafaxplugins.repairanvil.misc.config;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class ConfigBase extends YamlConfiguration {

    private final Plugin plugin;
    private final File file;

    public ConfigBase(Plugin plugin, String fileName) {
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), fileName);

        loadFile();
    }

    private void loadFile() {
        try {
            if (!plugin.getDataFolder().exists()) {
                plugin.getDataFolder().mkdirs();
            }

            if (!file.exists()) {
                plugin.saveResource(file.getName(), false);
            }

            this.load(file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void reload() {
        loadFile();
    }

    public void saveConfig() {
        try {
            this.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
