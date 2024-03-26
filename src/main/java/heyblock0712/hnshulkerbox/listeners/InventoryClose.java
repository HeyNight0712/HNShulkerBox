package heyblock0712.hnshulkerbox.listeners;

import heyblock0712.hnshulkerbox.data.Backpack;
import heyblock0712.hnshulkerbox.utils.ShulkerBoxUtil;
import org.bukkit.Sound;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

public class InventoryClose implements Listener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();

        player.playSound(
                event.getPlayer().getLocation(),
                Sound.BLOCK_SHULKER_BOX_CLOSE,
                1.0F,
                1.0F);

        // 移除資料
        Backpack.removePlayer(player);
    }
}
