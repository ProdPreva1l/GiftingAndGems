package info.preva1l.giftingandgems.guis;

import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import info.preva1l.giftingandgems.config.Menus;
import info.preva1l.giftingandgems.utils.Sound;
import info.preva1l.giftingandgems.utils.Text;
import info.preva1l.giftingandgems.utils.gui.ConfirmGUI;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiftingGUI extends FastInv {
    Player sender;
    public GiftingGUI(Player sender, OfflinePlayer target) {
        super(54, Menus.GIFT_GUI_TITLE.toFormattedString());

        this.sender = sender;

        setBackground();
        setNavigation();
        setPurchase();

    }

    private void setBackground() {
        setItems(new int[]{1,2,3,4,5,6,7,8,10,17,19,26,28,29,30,31,32,33,34,35,37,44,45,46,47,48,49,50,51,52,53}, new ItemBuilder(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7)).name(" ").build());
    }

    private void setNavigation() {
        setItem(0, new ItemBuilder(Material.ARROW).name(Text.message("&c&lPrevious")).build(), e-> {
            Sound.click(sender);
        });
        setItem(45, new ItemBuilder(Material.ARROW).name(Text.message("&a&lNext")).build(), e-> {
            Sound.click(sender);
        });
    }

    private void setPurchase() {
        setItems(new int[]{38,39,40,41,42,43}, new ItemBuilder(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5)).name(Text.message("&aBuy Gift")).build(), e->{
            Sound.click(sender);
            new ConfirmGUI(c -> {
                if (!c) {
                    Sound.fail(sender);
                    sender.sendMessage(Text.pluginMessage("&cPurchase Cancelled!"));
                    return;
                }
                Sound.levelUp(sender);
                sender.sendMessage(Text.pluginMessage("&aGift Purchased!"));
            }).open(sender);
        });
    }
}
