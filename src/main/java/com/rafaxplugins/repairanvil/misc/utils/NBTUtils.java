package com.rafaxplugins.repairanvil.misc.utils;

import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;

public class NBTUtils {

    public static boolean hasNbt(org.bukkit.inventory.ItemStack item, String key) {
        if (item == null || key == null) {
            return false;
        }

        ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        if (nmsItem == null) {
            return false;
        }

        NBTTagCompound tag = nmsItem.getTag();
        if (tag == null) {
            return false;
        }

        return tag.hasKey(key);
    }

    public static boolean hasNbt(Material material, String key) {
        if (material == null) {
            return false;
        }
        return hasNbt(new org.bukkit.inventory.ItemStack(material), key);
    }
}
