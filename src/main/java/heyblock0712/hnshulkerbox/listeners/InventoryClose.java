package heyblock0712.hnshulkerbox.listeners;

import heyblock0712.hnshulkerbox.data.InventoryData;
import org.bukkit.Sound;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

import java.awt.*;

public class InventoryClose implements Listener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();

        if (!InventoryData.hasPlayer(player)) return;

        player.playSound(
                event.getPlayer().getLocation(),
                Sound.BLOCK_SHULKER_BOX_CLOSE,
                1.0F,
                1.0F);

        // 覆蓋手上盒子
        ItemStack mainHandItem = player.getInventory().getItemInMainHand();
        if (InventoryData.updateShulkerBox(player, mainHandItem)) {
            player.sendMessage("["+ player.getName()+ "] " + "關閉 界伏盒");
        }else {
            player.sendMessage("關閉儲存盒子錯誤!!!");
        }
    }
}
