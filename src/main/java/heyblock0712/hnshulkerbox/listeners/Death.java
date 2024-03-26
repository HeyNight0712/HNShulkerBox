package heyblock0712.hnshulkerbox.listeners;

import heyblock0712.hnshulkerbox.data.InventoryData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class Death implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        if (!InventoryData.hasPlayer(player)) {return;}

        ItemStack mainHandItem = player.getInventory().getItemInMainHand();

        // 注意這是反轉
        if (!InventoryData.updateShulkerBox(player, mainHandItem)) {
            player.sendMessage("死亡儲存盒子錯誤!!");
        }
    }
}
