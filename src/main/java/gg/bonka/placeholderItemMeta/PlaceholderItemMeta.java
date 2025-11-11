package gg.bonka.placeholderItemMeta;

import co.aikar.commands.PaperCommandManager;
import gg.bonka.placeholderItemMeta.commands.PIMCommand;
import gg.bonka.placeholderItemMeta.configuration.PIMConfig;
import gg.bonka.placeholderItemMeta.items.listener.AnvilListener;
import gg.bonka.placeholderItemMeta.items.listener.GameModeSwitchListener;
import gg.bonka.placeholderItemMeta.items.listener.ItemPacketListener;
import gg.bonka.placeholderItemMeta.items.listener.SilentAnvilListener;
import gg.bonka.placeholderItemMeta.logging.ConsoleLogger;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
@Setter
public final class PlaceholderItemMeta extends JavaPlugin {

    private final static String version = "1.0.1";

    @Getter
    private static PlaceholderItemMeta instance;

    private AnvilListener anvilListener;
    private SilentAnvilListener silentAnvilListener;
    private GameModeSwitchListener gameModeSwitchListener;

    @Override
    public void onEnable() {
        if (instance != null)
            throw new IllegalStateException("PlaceholderItemMeta instance already exists!");

        instance = this;
        new PIMConfig().save();

        new ItemPacketListener();

        //Need a debugging item? Uncommenting this will give you one every time you join :)
        //This is meant for development debugging only, don't leave this uncommented in the main branch!
        //Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);

        anvilListener = new AnvilListener();
        silentAnvilListener = new SilentAnvilListener();
        gameModeSwitchListener = new GameModeSwitchListener();

        load();

        PaperCommandManager commandManager = new PaperCommandManager(this);
        commandManager.registerCommand(new PIMCommand());

        ConsoleLogger.info(String.format("PlaceholderItemMeta [%s] has been enabled!", version));
    }

    public void load(){
        var registerSilentListener = PIMConfig.getInstance().getBlockAnvilPlaceholdersSilently();

        //Player can click fast and get renamed item. To prevent it, need to register both normal and silent listeners
        if (PIMConfig.getInstance().getBlockAnvilPlaceholders() || registerSilentListener)
            Bukkit.getPluginManager().registerEvents(anvilListener, this);
        if (registerSilentListener)
            Bukkit.getPluginManager().registerEvents(silentAnvilListener, this);
        if(PIMConfig.getInstance().getDisablePlaceholdersForCreative())
            Bukkit.getPluginManager().registerEvents(gameModeSwitchListener, this);

    }

    @Override
    public void onDisable() {
        ConsoleLogger.info(String.format("Disabling PlaceholderItemMeta [%s]", version));
    }
}
