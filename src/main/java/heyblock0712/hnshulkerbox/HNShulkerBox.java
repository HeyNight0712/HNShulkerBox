package heyblock0712.hnshulkerbox;

import heyblock0712.hnshulkerbox.data.InventoryData;
import heyblock0712.hnshulkerbox.listeners.Death;
import heyblock0712.hnshulkerbox.listeners.InventoryClick;
import heyblock0712.hnshulkerbox.listeners.InventoryClose;
import heyblock0712.hnshulkerbox.listeners.OpenShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class HNShulkerBox extends JavaPlugin {
    // Debug 用
    public static boolean debug = true;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new OpenShulkerBox(), this);
        getServer().getPluginManager().registerEvents(new InventoryClick(), this);
        getServer().getPluginManager().registerEvents(new InventoryClose(), this);
        getServer().getPluginManager().registerEvents(new Death(), this);
    }

    @Override
    public void onDisable() {

        // 每個在線玩家
        for (Player player : getServer().getOnlinePlayers()) {
            if (!InventoryData.hasPlayer(player)) {continue;}

            ItemStack mainHandItem = player.getInventory().getItemInMainHand();
            if (InventoryData.updateShulkerBox(player, mainHandItem)) {
                player.sendMessage("伺服器正在關閉 為了保護你的盒子 系統將為你自動關閉盒子");
            }else {
                player.sendMessage("伺服器正在關閉你的盒子發生錯誤!!");
            }
        }
    }
}
