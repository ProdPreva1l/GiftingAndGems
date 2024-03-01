package info.preva1l.giftingandgems.utils;

import info.preva1l.giftingandgems.GiftingAndGems;
import info.preva1l.giftingandgems.config.Config;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;

@UtilityClass
public class Console {
    private final GiftingAndGems plugin = GiftingAndGems.i();
    public void info(String message) {
        plugin.getLogger().info(message);
    }
    public void warn(String message) {
        plugin.getLogger().warning(message);
    }
    public void severe(String message) {
        plugin.getLogger().severe(message);
    }
    public void debug(String message) {
        if (!Config.DEBUG.toBoolean()) return;
        plugin.getLogger().severe("[DEBUG] " + message);
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (player.hasPermission("chunkclaim.debug")) {
                player.sendMessage(Text.message("&c[DEBUG] " + message));
            }
        });
    }
}
