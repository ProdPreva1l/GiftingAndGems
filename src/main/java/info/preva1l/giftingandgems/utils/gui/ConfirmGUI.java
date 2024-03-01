package info.preva1l.giftingandgems.utils.gui;

import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import info.preva1l.giftingandgems.config.Menus;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ConfirmGUI extends FastInv {
    @FunctionalInterface
    public interface ConfirmCallback {
        /**
         * Returns when player selects
         * @param result true or false whether they confirmed or canceled
         */
        void onConfirm(boolean result);
    }
    /**
     * A GUI to make a player confirm.
     * @param callback {@link ConfirmCallback}
     */
    public ConfirmGUI(ConfirmCallback callback) {
        super(27, Menus.CONFIRM_GUI_TITLE.toFormattedString());

        setItems(new int[]{0, 1, 2, 9, 10, 11, 18, 19, 20},
                new ItemBuilder(Material.REDSTONE_BLOCK)
                        .name(Menus.CANCEL_BUTTON_NAME.toFormattedString())
                        .lore(Menus.CANCEL_BUTTON_LORE.toLore()).build(),e-> {
                    callback.onConfirm(false);
                    e.getWhoClicked().closeInventory();
                });

        setItems(new int[]{3, 4, 5, 12, 13, 14, 21, 22, 23},
                new ItemBuilder(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7)).name(" ").build());

        setItems(new int[]{6, 7, 8, 15, 16, 17, 24, 25, 26},
                new ItemBuilder(Material.EMERALD_BLOCK)
                        .name(Menus.CONFIRM_BUTTON_NAME.toFormattedString())
                        .lore(Menus.CONFIRM_BUTTON_LORE.toLore()).build(), e-> {
                    callback.onConfirm(true);
                    e.getWhoClicked().closeInventory();
                });
    }
}