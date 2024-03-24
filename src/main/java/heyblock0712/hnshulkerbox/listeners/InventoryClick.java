package heyblock0712.hnshulkerbox.listeners;

import heyblock0712.hnshulkerbox.data.Backpack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClick implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        // 獲取 移動物品
        ItemStack itemClicked = event.getCurrentItem();


        Player player = (Player) event.getWhoClicked();
        ItemStack originalItem = Backpack.peekItem(player);

        // 獲取 主手槽位
        int mainHandSlot = player.getInventory().getHeldItemSlot();

        if (event.getHotbarButton() == mainHandSlot) {
            event.setCancelled(true);
            return;
        }

        if (originalItem != null && originalItem.equals(itemClicked)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        // 獲取移動物品
        ItemStack itemClicked = event.getOldCursor();

        Player player = (Player) event.getWhoClicked();
        ItemStack originalItem = Backpack.peekItem(player);

        if (originalItem != null && originalItem.equals(itemClicked)) {
            event.setCancelled(true);
        }
    }
}
