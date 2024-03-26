package heyblock0712.hnshulkerbox.data;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class InventoryData {
    private static final Map<Player, Inventory> playerInventory = new HashMap<Player, Inventory>();

    public InventoryData(Player player, Inventory inventory) {
        playerInventory.put(player, inventory);

    }

    public Inventory getInventory(Player player) {
        return playerInventory.get(player);
    }

    public static boolean hasPlayer(Player player) {
        return playerInventory.containsKey(player);
    }

    public static void removePlayer(Player player) {
        playerInventory.remove(player);
    }
}
