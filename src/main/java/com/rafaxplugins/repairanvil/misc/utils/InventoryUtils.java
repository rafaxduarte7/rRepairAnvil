package com.rafaxplugins.repairanvil.misc.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InventoryUtils {

    public static boolean removeItems(Player player, Material material, int amount) {
        if (player == null || material == null || amount <= 0) {
            return false;
        }

        int total = 0;
        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.getType() == material) {
                total += item.getAmount();
                if (total >= amount) {
                    break;
                }
            }
        }

        if (total < amount) {
            return false;
        }

        int remaining = amount;
        for (int i = 0; i < player.getInventory().getSize(); i++) {
            ItemStack item = player.getInventory().getItem(i);
            if (item != null && item.getType() == material) {
                int stackAmount = item.getAmount();

                if (stackAmount <= remaining) {
                    player.getInventory().setItem(i, null);
                    remaining -= stackAmount;
                } else {
                    item.setAmount(stackAmount - remaining);
                    player.getInventory().setItem(i, item);
                    remaining = 0;
                }

                if (remaining == 0) {
                    break;
                }
            }
        }

        player.updateInventory();
        return true;
    }

}