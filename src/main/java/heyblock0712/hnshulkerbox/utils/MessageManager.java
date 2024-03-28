package heyblock0712.hnshulkerbox.utils;

import heyblock0712.hnshulkerbox.HNShulkerBox;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MessageManager {
    private static FileConfiguration messageConfig;

    public void initialize(File dataFolder) {
        File messageFile = new File(dataFolder, "message.yml");
        if (!messageFile.exists()) {
            try {
                HNShulkerBox.getInstance().saveResource("message.yml", false);
                messageFile.createNewFile();
            }catch (IOException e) {
                HNShulkerBox.getInstance().getLogger().info(String.valueOf(e));
            }

            loadMessages(messageFile);
        }
        loadMessages(dataFolder);
    }

    public void loadMessages(File dataFolder) {
        messageConfig = YamlConfiguration.loadConfiguration(new File(dataFolder, "message.yml"));
    }

    public static FileConfiguration getMessageConfig() {
        return messageConfig;
    }

    public static String getString(String path) {
        String string = messageConfig.getString(path, "&c" + path);
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static String gstString(String path, String def) {
        String string = messageConfig.getString(path, def);
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
