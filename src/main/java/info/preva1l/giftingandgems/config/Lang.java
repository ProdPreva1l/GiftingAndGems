package info.preva1l.giftingandgems.config;

import com.google.common.collect.ImmutableList;
import info.preva1l.giftingandgems.GiftingAndGems;
import info.preva1l.giftingandgems.utils.BasicConfig;
import info.preva1l.giftingandgems.utils.Text;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public enum Lang {
    PREFIX("prefix", "&8[&aGEMS&8]"),
    ERROR_NO_PERMISSION("errors.no-permission", "&8[&c!&8] &cInsufficient Permission!"),
    ERROR_NO_PLAYER("errors.player-not-found", "&8[&c!&8] &cPlayer is not found!"),
    WARNING_NOT_ONLINE("warnings.player-not-online", "&8[&c!&8] &cThis player is not online (or may not exist) but you can still gift, no refunds are offered!"),
    ;

    private final String path;
    private final Object defaultValue;

    public String toString() {
        String str = GiftingAndGems.i().getLangFile().getString(path);
        if (str.equals(path)) {
            return defaultValue.toString();
        }
        return str;
    }

    public String toFormattedString() {
        String str = GiftingAndGems.i().getLangFile().getString(path);
        if (str.equals(path)) {
            return Text.message(defaultValue.toString());
        }
        return Text.message(str);
    }

    public List<String> toStringList() {
        List<String> str = GiftingAndGems.i().getLangFile().getStringList(path);
        if (str.isEmpty() || str.get(0).equals(path)) {
            return (List<String>) defaultValue;
        }
        if (str.get(0).equals("null")) {
            return ImmutableList.of();
        }
        return str;
    }

    public List<String> toLore() {
        List<String> str = GiftingAndGems.i().getLangFile().getStringList(path);
        if (str.isEmpty() || str.get(0).equals(path)) {
            return Text.lore((List<String>) defaultValue);
        }
        if (str.get(0).equals("null")) {
            return ImmutableList.of();
        }
        return Text.lore(str);
    }

    public boolean toBoolean() {
        return Boolean.parseBoolean(toString());
    }

    public int toInteger() {
        return Integer.parseInt(toString());
    }

    public double toDouble() {
        return Double.parseDouble(toString());
    }

    public static void loadDefault() {
        BasicConfig configFile = GiftingAndGems.i().getLangFile();

        for (Lang config : Lang.values()) {
            String path = config.getPath();
            String str = configFile.getString(path);
            if (str.equals(path)) {
                configFile.getConfiguration().set(path, config.getDefaultValue());
            }
        }

        configFile.save();
        configFile.load();
    }
}