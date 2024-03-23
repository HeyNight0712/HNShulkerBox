package heyblock0712.hnshulkerbox.listeners;

import heyblock0712.hnshulkerbox.data.Backpack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClick implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        // 獲取移動物品
        ItemStack itemClicked = event.getCurrentItem();

        Player player = (Player) event.getWhoClicked();
        ItemStack originalItem = Backpack.peekItem(player);

        if (originalItem != null && originalItem.equals(itemClicked)) {
            event.setCancelled(true);
        }
    }
}
