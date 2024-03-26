package heyblock0712.hnshulkerbox.listeners;

import heyblock0712.hnshulkerbox.data.Backpack;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryClose implements Listener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();

        if (!Backpack.hasPlayer(player)) return;

        player.playSound(
                event.getPlayer().getLocation(),
                Sound.BLOCK_SHULKER_BOX_CLOSE,
                1.0F,
                1.0F);

        // 移除資料
        Backpack.removePlayer(player);
    }
}
