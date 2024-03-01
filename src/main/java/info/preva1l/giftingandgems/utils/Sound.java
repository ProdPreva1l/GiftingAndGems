package info.preva1l.giftingandgems.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.entity.Player;

@UtilityClass
public class Sound {
    /**
     * Plays a "success" sound.
     * @param player Player to play the sound for
     */
    public void success(Player player) {
        playSound(player, getSuccessSound(player), 20, 1.5f);
    }

    /**
     * Plays a "fail" sound.
     * @param player Player to play the sound for
     */
    public void fail(Player player) {
        playSound(player, getFailSound(player), 20, 1.75f);
    }

    /**
     * Plays a "warning" sound.
     * @param player Player to play the sound for
     */
    public void warning(Player player) {
        playSound(player, getWarningSound(player), 20, 1.75f);
    }

    /**
     * Plays a "click" sound.
     * @param player Player to play the sound for
     */
    public void click(Player player) {
        playSound(player, getClickSound(player), 20, 2);
    }

    /**
     * Plays a "level up" sound.
     * @param player Player to play the sound for
     */
    public void levelUp(Player player) {
        playSound(player, getLevelUpSound(player), 20, 2);
    }

    private void playSound(Player player, org.bukkit.Sound sound, float volume, float pitch) {
        player.playSound(player.getLocation(), sound, volume, pitch);
    }

    private org.bukkit.Sound getSuccessSound(Player player) {
        if (Version.isBefore("1.9")) {
            return org.bukkit.Sound.valueOf("NOTE_PLING");
        } else {
            return org.bukkit.Sound.valueOf("BLOCK_NOTE_BLOCK_PLING");
        }
    }

    private org.bukkit.Sound getFailSound(Player player) {
        if (Version.isBefore("1.9")) {
            return org.bukkit.Sound.valueOf("VILLAGER_NO");
        } else {
            return org.bukkit.Sound.valueOf("ENTITY_VILLAGER_NO");
        }
    }

    private org.bukkit.Sound getWarningSound(Player player) {
        if (Version.isBefore("1.9")) {
            return org.bukkit.Sound.valueOf("BURP");
        } else {
            return org.bukkit.Sound.valueOf("ENTITY_PLAYER_BURP");
        }
    }

    private org.bukkit.Sound getClickSound(Player player) {
        if (Version.isBefore("1.9")) {
            return org.bukkit.Sound.valueOf("CLICK");
        } else {
            return org.bukkit.Sound.valueOf("UI_BUTTON_CLICK");
        }
    }

    private org.bukkit.Sound getLevelUpSound(Player player) {
        if (Version.isBefore("1.9")) {
            return org.bukkit.Sound.valueOf("LEVEL_UP");
        } else {
            return org.bukkit.Sound.valueOf("ENTITY_PLAYER_LEVELUP");
        }
    }
}