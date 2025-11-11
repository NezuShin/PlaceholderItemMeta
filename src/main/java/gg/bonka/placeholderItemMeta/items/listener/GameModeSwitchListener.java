package gg.bonka.placeholderItemMeta.items.listener;

import gg.bonka.placeholderItemMeta.PlaceholderItemMeta;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

//This listener being registered only if disable-placeholders-for-creative is true
public class GameModeSwitchListener implements Listener {


    //When switching to GameMode.CREATIVE, clientside will corrupt serverside items.
    @EventHandler
    public void onGameModeSwitch(PlayerGameModeChangeEvent e) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(PlaceholderItemMeta.getInstance(), () -> {
            e.getPlayer().updateInventory();
        });

    }
}
