package heyblock0712.hnshulkerbox.data;

import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

import java.util.HashMap;
import java.util.Map;

public class InventoryData {
    private static final Map<Player, Inventory> playerInventory = new HashMap<>();

    public static void put(Player player, Inventory inventory) {playerInventory.put(player, inventory);}

    public static Inventory getInventory(Player player) {
        return playerInventory.get(player);
    }

    public static boolean hasPlayer(Player player) {
        return playerInventory.containsKey(player);
    }

    public static void removePlayer(Player player) {
        playerInventory.remove(player);
    }

    public static boolean updateShulkerBox(Player player, ItemStack mainHandItem) {
        if (mainHandItem.getItemMeta() instanceof BlockStateMeta) {
            BlockStateMeta blockStateMeta = (BlockStateMeta) mainHandItem.getItemMeta();
            if (blockStateMeta.getBlockState() instanceof InventoryHolder) {
                InventoryHolder boxInventory = (InventoryHolder) blockStateMeta.getBlockState();

                Inventory inventory = InventoryData.getInventory(player);
                boxInventory.getInventory().setContents(inventory.getContents());

                blockStateMeta.setBlockState((BlockState) boxInventory);
                mainHandItem.setItemMeta(blockStateMeta);

                player.getInventory().setItemInMainHand(mainHandItem);
                InventoryData.removePlayer(player);
                return true;
            }
        }
        return false;
    }
}
