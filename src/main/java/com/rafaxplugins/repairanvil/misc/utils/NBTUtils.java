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

        return tag.hasKey(key.toLowerCase());
    }

    public static boolean hasNbt(Material material, String key) {
        if (material == null) {
            return false;
        }
        return hasNbt(new org.bukkit.inventory.ItemStack(material), key);
    }

    public static org.bukkit.inventory.ItemStack nbt(org.bukkit.inventory.ItemStack item, String key, boolean value) {
        if (item == null || key == null) {
            return item;
        }

        ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        if (nmsItem == null) {
            return item;
        }

        NBTTagCompound tag = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
        tag.setBoolean(key.toLowerCase(), value);
        nmsItem.setTag(tag);

        return CraftItemStack.asBukkitCopy(nmsItem);
    }

    public static void debugNbt(org.bukkit.inventory.ItemStack item) {
        if (item == null) {
            System.out.println("Item is null");
            return;
        }

        ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        if (nmsItem == null) {
            System.out.println("NMS item is null");
            return;
        }

        NBTTagCompound tag = nmsItem.getTag();
        if (tag == null) {
            System.out.println("No NBT tag found");
        } else {
            System.out.println("NBT Contents: " + tag);
        }
    }
}