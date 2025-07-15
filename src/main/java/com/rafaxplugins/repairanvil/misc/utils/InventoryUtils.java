package com.rafaxplugins.repairanvil.misc.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InventoryUtils {

    public static boolean removeItems(Player player, Material material, int amount) {
        if (player == null || material == null || amount <= 0) {
            return false;
        }

        int remaining = amount;
        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.getType() == material) {
                int stackAmount = item.getAmount();

                if (stackAmount <= remaining) {
                    player.getInventory().remove(item);
                    remaining -= stackAmount;
                } else {
                    item.setAmount(stackAmount - remaining);
                    remaining = 0;
                }

                if (remaining == 0) {
                    break;
                }
            }
        }

        player.updateInventory();
        return remaining == 0;
    }
}
