package gg.bonka.placeholderItemMeta.configuration;

import gg.bonka.placeholderItemMeta.PlaceholderItemMeta;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.List;

@Setter
public class PIMConfig extends CustomConfig {

    @Getter
    private static PIMConfig instance;

    private List<String> whitelistedPersistentDataContainers;
    private Boolean blockAnvilPlaceholders;
    private Boolean blockAnvilPlaceholdersSilently;
    private Boolean disablePlaceholdersInName;
    private Boolean disablePlaceholdersInLore;

    private String placeholdersAreNotAllowedMessage;

    public PIMConfig() {
        super(PlaceholderItemMeta.getInstance().getDataFolder(), "config.yml");

        instance = this;
    }

    public static void reload() {
        new PIMConfig();
    }

    @Override
    public void save() {
        put("whitelisted-persistent-data-containers", getWhitelistedPersistentDataContainers());
        put("block-anvil-placeholders", getBlockAnvilPlaceholders());
        put("block-anvil-placeholders-silently", getBlockAnvilPlaceholdersSilently());
        put("disable-placeholders-in-name", getDisablePlaceholdersInName());
        put("disable-placeholders-in-lore", getDisablePlaceholdersInLore());
        put("placeholders-are-not-allowed-message", getPlaceholdersAreNotAllowedMessage());

        try {
            super.save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getWhitelistedPersistentDataContainers() {
        return getStringList(whitelistedPersistentDataContainers, "whitelisted-persistent-data-containers");
    }

    public boolean getBlockAnvilPlaceholders() {
        return getBoolean(blockAnvilPlaceholders, "block-anvil-placeholders");
    }

    public boolean getBlockAnvilPlaceholdersSilently() {
        return getBoolean(blockAnvilPlaceholdersSilently, "block-anvil-placeholders-silently");
    }


    public boolean getDisablePlaceholdersInLore() {
        return getBoolean(disablePlaceholdersInLore, "disable-placeholders-in-lore");
    }

    public boolean getDisablePlaceholdersInName() {
        return getBoolean(disablePlaceholdersInName, "disable-placeholders-in-name");
    }

    public String getPlaceholdersAreNotAllowedMessage() {
        var str = getString(placeholdersAreNotAllowedMessage, "placeholders-are-not-allowed-message");

        if (str == null)
            str = "<red>Placeholders are not allowed!";
        return str;
    }

    private boolean getBoolean(Boolean bool, String key) {
        if (bool == null) {
            bool = Boolean.parseBoolean(getStringKey(key));
        }

        return Boolean.TRUE.equals(bool);
    }

    public String getString(String str, String key) {
        if (str == null) {
            str = getStringKey(key);
        }

        return str;
    }

    private List<String> getStringList(List<String> array, String path) {
        if (array == null) {
            array = getStringList(path);
        }

        return array;
    }
}
