package heyblock0712.hnshulkerbox.listeners;

import heyblock0712.hnshulkerbox.data.Backpack;
import heyblock0712.hnshulkerbox.utils.ShulkerBoxUtil;
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
        Player player = (Player) event.getWhoClicked();

        // 過濾
        if (!Backpack.hasPlayer(player)) return;

        ItemStack itemClicked = event.getCurrentItem();
        ItemStack originalItem = Backpack.peekItem(player);
        int mainHandSlot = player.getInventory().getHeldItemSlot();

        // 儲存新位置
        player.sendMessage(String.valueOf(event.getSlot()));
        ItemStack hotbarButton = null;

        if (event.getHotbarButton() != -1) {
            hotbarButton = player.getInventory().getItem(event.getHotbarButton());
        }
        // 是否 移動原先項目

        // 檢查 快捷鍵移動
        if (event.getHotbarButton() == mainHandSlot || hotbarButton != null && ShulkerBoxUtil.isShulkerBox(hotbarButton.getType())) {
            event.setCancelled(true);
            return;
        }

        // 檢查 點選移動
        if (originalItem != null && originalItem.equals(itemClicked) || itemClicked != null && ShulkerBoxUtil.isShulkerBox(itemClicked.getType())) {
            event.setCancelled(true);
        }
    }
}
