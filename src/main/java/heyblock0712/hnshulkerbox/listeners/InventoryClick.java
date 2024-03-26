package heyblock0712.hnshulkerbox.listeners;

import heyblock0712.hnshulkerbox.HNShulkerBox;
import heyblock0712.hnshulkerbox.data.Backpack;
import heyblock0712.hnshulkerbox.utils.ShulkerBoxUtil;
import org.bukkit.Bukkit;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;

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

        ItemStack itemClicked = event.getCursor();
        int mainHandSlot = player.getInventory().getHeldItemSlot();

        // 獲取 快捷鍵 Key
        ItemStack hotbarButton;
        if (event.getHotbarButton() != -1) {
            hotbarButton = player.getInventory().getItem(event.getHotbarButton());
        } else {
            hotbarButton = null;
        }

        ShulkerBoxUtil util = new ShulkerBoxUtil(event);
        // 檢查 快捷鍵
        if (util.hotbarButtonHandMove(mainHandSlot)) {return;}

        // 快捷鍵 移動 ShulkerBox
        if (util.hotbarButtonShulkerBoxMove(hotbarButton)) {return;}

        // 主手
        if (util.clickHandMove(mainHandSlot)) {return;}

        // 是否蹲下點擊 盒子
        if (util.isShiftClickShulkerBox(event.getCurrentItem())) {return;}

        // 盒子疊代
        if (util.cursorShulkerBoxMove(itemClicked)) {return;}


        delayedStorage(player, event);
        if (HNShulkerBox.debug) { player.sendMessage("更新 盒子狀態 (Click)");}
    }

    /**
     * 檢查拖動物品 是否為指定功能
     *
     * @param event 物品拖動事件
     */
    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack itemDragged = event.getOldCursor();

        // 防止拖動 盒子疊代
        if (ShulkerBoxUtil.isShulkerBox(itemDragged.getType())) {
            Set<Integer> rawSlots = event.getRawSlots();
            for (Integer slot: rawSlots) {
                if (slot >= 0 && slot < 27) {
                    event.setCancelled(true);

                    if (HNShulkerBox.debug) { event.getWhoClicked().sendMessage("取消拖動放置 盒子位置");}
                    return;
                }
            }
        }

        delayedStorage(player, event);
        if (HNShulkerBox.debug) { player.sendMessage("更新 盒子狀態 (Drag)");}
    }

    /**
     * 延遲更新庫存 通常是 約50ms
     *
     * @param player 玩家
     * @param event 事件
     * @param <T> 傳遞事件
     */
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
