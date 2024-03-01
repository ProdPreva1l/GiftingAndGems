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
public enum Config {
    DEBUG("debug", false),
    SERVER_NAME("server-name", "server-01"),
    ALLOW_OFFLINE_GIFTING("allow-offline-gifting", false),

    /*
        Database
     */
    MONGO_HOST("mongo.host", "127.0.0.1"),
    MONGO_PORT("mongo.port", 27017),
    MONGO_USERNAME("mongo.username", "username"),
    MONGO_PASSWORD("mongo.password", "password"),
    MONGO_DATABASE("mongo.database", "database"),
    MONGO_AUTHDB("mongo.auth-db", "authDB"),
    ;

    private final String path;
    private final Object defaultValue;

    public String toString() {
        String str = GiftingAndGems.i().getConfigFile().getString(path);
        if (str.equals(path)) {
            return defaultValue.toString();
        }
        return str;
    }

    public String toFormattedString() {
        String str = GiftingAndGems.i().getConfigFile().getString(path);
        if (str.equals(path)) {
            return Text.message(defaultValue.toString());
        }
        return Text.message(str);
    }

    public List<String> toStringList() {
        List<String> str = GiftingAndGems.i().getConfigFile().getStringList(path);
        if (str.isEmpty() || str.get(0).equals(path)) {
            return (List<String>) defaultValue;
        }
        if (str.get(0).equals("null")) {
            return ImmutableList.of();
        }
        return str;
    }

    public List<String> toLore() {
        List<String> str = GiftingAndGems.i().getConfigFile().getStringList(path);
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
        BasicConfig configFile = GiftingAndGems.i().getConfigFile();

        for (Config config : Config.values()) {
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