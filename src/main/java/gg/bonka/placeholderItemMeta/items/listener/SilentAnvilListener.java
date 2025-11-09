package gg.bonka.placeholderItemMeta.items.listener;

import gg.bonka.placeholderItemMeta.PlaceholderItemMeta;
import gg.bonka.placeholderItemMeta.configuration.PIMConfig;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;

public class SilentAnvilListener implements Listener {

    @EventHandler
    public void prepareAnvilEvent(PrepareAnvilEvent event){
        var inventory = event.getInventory();

        var result = inventory.getResult();

        if(result == null || result.getType().isAir())
            return;

        String name = PlainTextComponentSerializer.plainText().serialize(result.displayName());
        int percentCount = name.length() - name.replace("%", "").length();
        if(percentCount < 2)
            return;

        inventory.setResult(null);
        Bukkit.getScheduler().scheduleSyncDelayedTask(PlaceholderItemMeta.getInstance(), () -> {
            inventory.setResult(null);
        });
    }
}
