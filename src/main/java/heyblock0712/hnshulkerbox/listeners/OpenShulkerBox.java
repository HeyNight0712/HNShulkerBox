package heyblock0712.hnshulkerbox.listeners;

import heyblock0712.hnshulkerbox.data.InventoryData;
import heyblock0712.hnshulkerbox.utils.MessageManager;
import heyblock0712.hnshulkerbox.utils.ShulkerBoxUtil;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class OpenShulkerBox implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        // 檢查 主手
        if (event.getHand() != EquipmentSlot.HAND) return;

        // 檢查 isShulkerBox
        ItemStack item = event.getItem();
        if (item == null || !ShulkerBoxUtil.isShulkerBox(item.getType())) return;

        // 檢查 蹲下 + 右鍵
        if (!(event.getPlayer().isSneaking())) return;
        if ((event.getAction() != Action.RIGHT_CLICK_AIR) && (event.getAction() != Action.RIGHT_CLICK_BLOCK)) return;

        // 獲取 ShulkerBox
        if (!(item.getItemMeta() instanceof BlockStateMeta)) return;
        BlockStateMeta blockStateMeta = (BlockStateMeta) item.getItemMeta();

        if (!(blockStateMeta.getBlockState() instanceof ShulkerBox)) return;
        Player player = event.getPlayer();

        ShulkerBox shulkerBox = (ShulkerBox) blockStateMeta.getBlockState();
        ItemStack[] originalContents = shulkerBox.getInventory().getContents();

        // 模擬庫存
        ItemMeta itemMeta = item.getItemMeta();
        String name = itemMeta.hasDisplayName() ? itemMeta.getDisplayName() : MessageManager.getString("DefaultName");

        Inventory inventory = Bukkit.createInventory(null, 27, name);
        inventory.setContents(originalContents);

        // 紀錄物品
        InventoryData.put(player, inventory);

        // 音效回饋
        player.playSound(
                player.getLocation(),
                Sound.BLOCK_SHULKER_BOX_OPEN,
                1.0F,
                1.0F);

        // 訊息轉換
        String title = MessageManager.getString("Title");
        String openMessage = MessageManager.getString("Player.Open");
        openMessage = openMessage.replace("%name%", name);

        player.sendMessage(title + openMessage);
        event.getPlayer().openInventory(InventoryData.getInventory(player));

        // 取消事件
        event.setCancelled(true);
    }
}
