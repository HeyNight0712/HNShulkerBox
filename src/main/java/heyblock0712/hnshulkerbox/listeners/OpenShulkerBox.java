package heyblock0712.hnshulkerbox.listeners;

import heyblock0712.hnshulkerbox.data.Backpack;
import heyblock0712.hnshulkerbox.utils.ShulkerBoxUtil;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.ShulkerBox;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

import java.util.Random;

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
        ShulkerBox shulkerBox = (ShulkerBox) blockStateMeta.getBlockState();

        // 打開背包
        event.getPlayer().playSound(
                event.getPlayer().getLocation(),
                Sound.BLOCK_SHULKER_BOX_OPEN,
                1.0F,
                new Random().nextFloat());
        event.getPlayer().openInventory(shulkerBox.getInventory());

        // 紀錄物品
        Backpack.storeItem(event.getPlayer(), item);

        // 取消事件
        event.setCancelled(true);
    }
}
