package gg.bonka.placeholderItemMeta.items.event;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * This event called after all placeholders replaced. Feel free to change any item's data. Changes only applied to clientside item
 */
public class ItemParseEvent extends Event {
    @Getter
    private static HandlerList handlerList = new HandlerList();

    @Getter
    private Player player;

    @Getter
    @Setter
    private ItemStack item;

    public ItemParseEvent(Player player, ItemStack item){
        super();
        this.player = player;
        this.item = item;
    }



    @Override
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }



}
