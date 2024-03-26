package heyblock0712.hnshulkerbox.data;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class Backpack{
    private static final Map<Player, ItemStack> playerItems = new HashMap<Player, ItemStack>();

    /**
     * 檢查是否有玩家
     *
     * @param player 玩家
     * @return 是否有數據
     */
    public static boolean hasPlayer(Player player) {
        return playerItems.containsKey(player);
    }
    
    /**
     * 儲存玩家手上物品
     * 
     * @param player 玩家
     * @param item 手上物品
     */
    public static void storeItem(Player player, ItemStack item) {
        playerItems.put(player, item);
    }

    /**
     * 移除 Map
     * @param player 玩家
     */
    public static void removePlayer(Player player) {
        playerItems.remove(player);
    }


    /**
     * 搜索並返回
     *
     * @param player 玩家
     * @return 返回搜索
     */
    public static ItemStack peekItem(Player player) {return playerItems.get(player);}
}
