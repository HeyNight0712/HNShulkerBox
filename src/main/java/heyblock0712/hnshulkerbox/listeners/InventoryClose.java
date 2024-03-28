package heyblock0712.hnshulkerbox.listeners;

import heyblock0712.hnshulkerbox.data.InventoryData;
import heyblock0712.hnshulkerbox.utils.MessageManager;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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

        // 轉換文字
        ItemMeta itemMeta = mainHandItem.getItemMeta();
        String name = itemMeta.hasDisplayName() ? itemMeta.getDisplayName() : MessageManager.getString("DefaultName");

        String title = MessageManager.getString("Title");
        String closeMessage = MessageManager.getString("Player.Close");
        closeMessage = closeMessage.replace("%name%", name);

        String closeErrorMessage = MessageManager.getString("Player.CloseError");
        closeErrorMessage = closeErrorMessage.replace("%name%", name);

        if (InventoryData.updateShulkerBox(player, mainHandItem)) {
            player.sendMessage(title + closeMessage);
        }else {
            player.sendMessage(title + closeErrorMessage);
        }
    }
}
