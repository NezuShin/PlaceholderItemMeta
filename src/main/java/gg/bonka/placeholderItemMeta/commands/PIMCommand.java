package gg.bonka.placeholderItemMeta.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import gg.bonka.placeholderItemMeta.PlaceholderItemMeta;
import gg.bonka.placeholderItemMeta.configuration.PIMConfig;
import gg.bonka.placeholderItemMeta.logging.ConsoleLogger;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

@CommandAlias("pim")
@CommandPermission("pim.command")
public class PIMCommand extends BaseCommand {

    @Default
    @CommandCompletion("@nothing")
    @Description("Shows available commands.")
    public void onPIMCommand(Player player) {
        player.sendMessage(Component.text("Missing argument! Commands:").color(NamedTextColor.RED));
        player.sendMessage(Component.text("/pim reload").color(NamedTextColor.RED));
        player.sendMessage(Component.text("/pim update-inventory").color(NamedTextColor.RED));
    }

    @Subcommand("reload")
    @CommandCompletion("@nothing")
    @Description("Reloads the placeholder item meta plugin data.")
    @CommandPermission("pim.command.reload")
    public void onReloadCommand(Player player) {
        ConsoleLogger.info(String.format("%s started reloading PlaceholderItemMeta.", player.getName()));

        HandlerList.unregisterAll(PlaceholderItemMeta.getInstance().getAnvilListener());
        HandlerList.unregisterAll(PlaceholderItemMeta.getInstance().getSilentAnvilListener());
        PIMConfig.reload();

        PlaceholderItemMeta.getInstance().load();

        player.sendMessage(Component.text("Reloaded placeholder item meta.").color(NamedTextColor.GREEN));
        player.sendMessage(Component.text("Items need to be interacted with before being updated on your client.").color(NamedTextColor.GRAY));
        player.sendMessage(Component.text("You could also re-login this will instantly update all placeholders.").color(NamedTextColor.GRAY));

        ConsoleLogger.success("PlaceholderItemMeta finished reloading.");
    }

    @Subcommand("update-inventory")
    @CommandCompletion("@nothing")
    @Description("Update your inventory to refresh placeholders")
    @CommandPermission("pim.command.update-inventory")
    public void onUpdateInventoryCommand(Player player) {
        player.updateInventory();
        player.sendMessage(Component.text("Your inventory has been updated!"));
    }
}
