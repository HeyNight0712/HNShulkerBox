package heyblock0712.hnshulkerbox;

import heyblock0712.hnshulkerbox.listeners.InventoryClick;
import heyblock0712.hnshulkerbox.listeners.OpenShulkerBox;
import org.bukkit.plugin.java.JavaPlugin;

public final class HNShulkerBox extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new InventoryClick(), this);
        getServer().getPluginManager().registerEvents(new OpenShulkerBox(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
