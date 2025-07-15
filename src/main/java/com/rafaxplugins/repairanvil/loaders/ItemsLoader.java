package com.rafaxplugins.repairanvil.loaders;

import com.google.common.collect.Maps;
import com.rafaxplugins.repairanvil.RepairAnvilPlugin;
import com.rafaxplugins.repairanvil.misc.config.ConfigBase;
import org.bukkit.Bukkit;
import org.bukkit.Material;

import java.util.Map;

public class ItemsLoader {

    public final static ItemsLoader INSTANCE = new ItemsLoader();

    private final Map<Material, Integer> ITEMS = Maps.newHashMap();

    public void load() {
        ITEMS.clear();

        final ConfigBase config = RepairAnvilPlugin.getItemsConfig();

        if (config.getConfigurationSection("items") == null) {
            Bukkit.getLogger().warning("Seção 'items' inválida!");
            return;
        }

        for (final String key : config.getConfigurationSection("items").getKeys(false)) {
            final String path = "items." + key;

            final Material material = Material.getMaterial(key);
            if (material == null) {
                Bukkit.getLogger().warning("Nome de material inválido: " + key);
                continue;
            }

            final int amount = config.getInt(path, -1);
            if (amount < 0) {
                Bukkit.getLogger().warning("Quantidade inválida para o item " + key);
                continue;
            }

            Bukkit.getLogger().info("Registrando item: " + material.name() + " = " + amount);
            ITEMS.put(material, amount);
        }

        Bukkit.getLogger().info("[rRepairAnvil] Itens carregados: " + ITEMS.size());
    }

    public Integer getValue(Material material) {
        if (material == null) return null;
        return ITEMS.get(material);
    }

}
