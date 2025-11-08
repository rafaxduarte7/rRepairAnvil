package com.rafaxplugins.repairanvil.listeners;

import com.rafaxplugins.repairanvil.RepairAnvilPlugin;
import com.rafaxplugins.repairanvil.loaders.ItemsLoader;
import com.rafaxplugins.repairanvil.misc.config.ConfigBase;
import com.rafaxplugins.repairanvil.misc.message.Message;
import com.rafaxplugins.repairanvil.misc.utils.InventoryUtils;
import com.rafaxplugins.repairanvil.misc.utils.NBTUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InteractListener implements Listener {

    private final ItemsLoader itemsLoader = ItemsLoader.INSTANCE;

    @EventHandler
    void on(PlayerInteractEvent event) {
        final Player player = event.getPlayer();

        final Block clickedBlock = event.getClickedBlock();
        if (clickedBlock == null || clickedBlock.getType() != Material.ANVIL) return;

        final Action action = event.getAction();
        if (action != Action.RIGHT_CLICK_BLOCK) return;

        event.setCancelled(true);

        final ItemStack itemStack = player.getItemInHand();
        if (itemStack == null || itemStack.getType() == null || itemStack.getType() == Material.AIR) return;

        final ConfigBase messages = RepairAnvilPlugin.getMessagesConfig();
        final ConfigBase items = RepairAnvilPlugin.getItemsConfig();

        for (String nbt : items.getStringList("settings.no-repair-by-nbt")) {
            if (NBTUtils.hasNbt(itemStack, nbt.trim().toLowerCase())) {
                messages.getStringList("messages.has-nbt-blocked")
                    .forEach(line -> Message.send(player, line));
                return;
            }
        }

        if (itemStack.getDurability() == 0) {
            messages.getStringList("messages.no-durability")
                .forEach(line -> Message.send(player, line));
            return;
        }

        final Integer required = itemsLoader.getValue(itemStack.getType());
        if (required == null) return;

        if (!InventoryUtils.removeItems(player, Material.DIAMOND, required)) {
            messages.getStringList("messages.no-has-diamond").stream()
                .map(line -> line.replace("<required>", String.valueOf(required)))
                .forEach(line -> Message.send(player, line));
            return;
        }

        itemStack.setDurability((short) 0);

        Message.send(player, messages.getStringList("messages.successful-repair"));
    }

}
