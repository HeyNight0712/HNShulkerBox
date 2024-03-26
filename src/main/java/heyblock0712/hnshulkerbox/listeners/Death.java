package heyblock0712.hnshulkerbox.listeners;

import heyblock0712.hnshulkerbox.data.InventoryData;
import org.bukkit.Color;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

public class Death implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        if (!InventoryData.hasPlayer(player)) {return;}

        ItemStack mainHandItem = player.getInventory().getItemInMainHand();

        if (mainHandItem.getItemMeta() instanceof BlockStateMeta) {
            BlockStateMeta blockStateMeta = (BlockStateMeta) mainHandItem.getItemMeta();
            if (blockStateMeta.getBlockState() instanceof InventoryHolder) {
                InventoryHolder boxInventory = (InventoryHolder) blockStateMeta.getBlockState();

                Inventory inventory = InventoryData.getInventory(player);
                boxInventory.getInventory().setContents(inventory.getContents());

                blockStateMeta.setBlockState((BlockState) boxInventory);
                mainHandItem.setItemMeta(blockStateMeta);

                player.getInventory().setItemInMainHand(mainHandItem);

                // 移除玩家的数据，因为玩家已经死亡
                InventoryData.removePlayer(player);
            }
        } else {
            player.sendMessage("死亡儲存盒子錯誤!!");
        }
    }
}
