package heyblock0712.hnshulkerbox.utils;

import heyblock0712.hnshulkerbox.HNShulkerBox;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class ShulkerBoxUtil {
    private final InventoryClickEvent event;

    private final Player player;

    public ShulkerBoxUtil(InventoryClickEvent event) {
        this.event = event;
        this.player = (Player) event.getWhoClicked();
    }

    /**
     * 防止熱鍵移動 主手物品
     *
     * @param mainHandSlot 主手位置
     * @return boolean
     */
    public boolean hotbarButtonHandMove(int mainHandSlot) {
        if (event.getHotbarButton() == mainHandSlot) {
            event.setCancelled(true);

            if (HNShulkerBox.debug) { player.sendMessage("取消快捷鍵 主手位置"); }
            return true;
        }
        return false;
    }

    public boolean hotbarButtonShulkerBoxMove(ItemStack hotbarButton) {
        if (hotbarButton != null && ShulkerBoxUtil.isShulkerBox(hotbarButton.getType())) {
            event.setCancelled(true);

            if (HNShulkerBox.debug) { player.sendMessage("取消快捷鍵 移動盒子");}
            return true;
        }
        return false;
    }

    public boolean clickHandMove(int mainHandSlot) {
        if (event.getSlotType() == InventoryType.SlotType.QUICKBAR && event.getSlot() == mainHandSlot) {
            event.setCancelled(true);

            if (HNShulkerBox.debug) { player.sendMessage("取消點選 主手位置");}
            return true;
        }
        return false;
    }

    // 是否 蹲下點擊 ShulkerBox
    public boolean isShiftClickShulkerBox(ItemStack click) {
        if (click != null && ShulkerBoxUtil.isShulkerBox(click.getType()) && event.isShiftClick()) {
            event.setCancelled(true);

            if (HNShulkerBox.debug) { player.sendMessage("取消蹲下點擊 盒子");}
            return true;
        }
        return false;
    }

    public boolean cursorShulkerBoxMove(ItemStack itemClicked) {
        if (itemClicked != null && ShulkerBoxUtil.isShulkerBox(itemClicked.getType()) && event.getRawSlot() >= 0 && event.getRawSlot() < 27) {
            event.setCancelled(true);

            if (HNShulkerBox.debug) { player.sendMessage("取消放置 盒子位置");}
            return true;
        }
        return false;
    }


    public static boolean isShulkerBox(Material material){
        return switch (material) {
            case SHULKER_BOX, BLACK_SHULKER_BOX, BLUE_SHULKER_BOX, BROWN_SHULKER_BOX, CYAN_SHULKER_BOX, GRAY_SHULKER_BOX, GREEN_SHULKER_BOX, LIGHT_BLUE_SHULKER_BOX, LIGHT_GRAY_SHULKER_BOX, LIME_SHULKER_BOX, MAGENTA_SHULKER_BOX, ORANGE_SHULKER_BOX, PINK_SHULKER_BOX, PURPLE_SHULKER_BOX, RED_SHULKER_BOX, WHITE_SHULKER_BOX, YELLOW_SHULKER_BOX ->
                    true;
            default -> false;
        };
    }
}
