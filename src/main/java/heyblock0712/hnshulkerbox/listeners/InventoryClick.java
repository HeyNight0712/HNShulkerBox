package heyblock0712.hnshulkerbox.listeners;

import heyblock0712.hnshulkerbox.data.Backpack;
import heyblock0712.hnshulkerbox.utils.ShulkerBoxUtil;
import org.bukkit.Bukkit;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class InventoryClick implements Listener {
    private final JavaPlugin plugin;

    public InventoryClick(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        // 獲取 移動物品
        Player player = (Player) event.getWhoClicked();

        // 過濾
        if (!Backpack.hasPlayer(player)) return;

        ItemStack itemClicked = event.getCurrentItem();
        ItemStack originalItem = Backpack.peekItem(player);
        int mainHandSlot = player.getInventory().getHeldItemSlot();

        int rawSlot = event.getRawSlot();
        // 儲存新位置
        player.sendMessage(String.valueOf(event.getRawSlot()));

        delayedStorage(player, event);
        // Bukkit.getScheduler().runTask(plugin, () -> {
        //     // 获取潜影盒的物品栏
        //     ItemStack mainHeadItem = player.getInventory().getItemInMainHand();
        //     ShulkerBox shulkerBox = (ShulkerBox) ((BlockStateMeta) mainHeadItem.getItemMeta()).getBlockState();
        //     Inventory inventory = shulkerBox.getInventory();
//
        //     // 设置新的库存内容
        //     inventory.setContents(event.getInventory().getContents());
//
        //     // 保存新的库存内容到潜影盒
        //     BlockStateMeta itemMeta = (BlockStateMeta) mainHeadItem.getItemMeta();
        //     itemMeta.setBlockState(shulkerBox);
        //     mainHeadItem.setItemMeta(itemMeta);
        // });


        // 檢查

        //// 獲取 快捷鍵 Key
        //ItemStack hotbarButton = null;
        //if (event.getHotbarButton() != -1) {
        //    hotbarButton = player.getInventory().getItem(event.getHotbarButton());
        //}
//
        //// 檢查 快捷鍵移動
        //// 檢查 event.getHotbarButton() == mainHandSlot 是否為主手
        //// 檢查
        //if (event.getHotbarButton() == mainHandSlot ||
        //        hotbarButton != null && ShulkerBoxUtil.isShulkerBox(hotbarButton.getType())) {
        //    event.setCancelled(true);
        //    return;
        //}
//
        //// 檢查 點選移動
        //if (event.getSlotType() == InventoryType.SlotType.QUICKBAR && event.getSlot() == mainHandSlot ||
        //        itemClicked != null && ShulkerBoxUtil.isShulkerBox(itemClicked.getType())) {
        //    event.setCancelled(true);
        //}
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        Player player = (Player) event.getWhoClicked();
        player.sendMessage("觸發移動");

        delayedStorage(player, event);
    }

    private <T extends InventoryEvent> void delayedStorage(Player player, T event) {
        Bukkit.getScheduler().runTask(plugin, () -> {
            // 获取潜影盒的物品栏
            ItemStack mainHeadItem = player.getInventory().getItemInMainHand();
            ShulkerBox shulkerBox = (ShulkerBox) ((BlockStateMeta) mainHeadItem.getItemMeta()).getBlockState();
            Inventory inventory = shulkerBox.getInventory();

            // 设置新的库存内容
            inventory.setContents(event.getInventory().getContents());

            // 保存新的库存内容到潜影盒
            BlockStateMeta itemMeta = (BlockStateMeta) mainHeadItem.getItemMeta();
            itemMeta.setBlockState(shulkerBox);
            mainHeadItem.setItemMeta(itemMeta);
        });
    }
}
