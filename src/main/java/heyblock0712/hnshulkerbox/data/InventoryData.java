package heyblock0712.hnshulkerbox.data;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

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
}
