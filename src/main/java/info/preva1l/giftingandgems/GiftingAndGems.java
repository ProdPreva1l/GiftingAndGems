package info.preva1l.giftingandgems;

import fr.mrmicky.fastinv.FastInvManager;
import info.preva1l.giftingandgems.commands.GiftCommand;
import info.preva1l.giftingandgems.config.Config;
import info.preva1l.giftingandgems.config.Lang;
import info.preva1l.giftingandgems.config.Menus;
import info.preva1l.giftingandgems.utils.BasicConfig;
import info.preva1l.giftingandgems.utils.commands.CommandManager;
import info.preva1l.giftingandgems.utils.data.CacheHandler;
import info.preva1l.giftingandgems.utils.data.CollectionHelper;
import info.preva1l.giftingandgems.utils.data.SimpleMongoHelper;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class GiftingAndGems extends JavaPlugin {
    private static GiftingAndGems INSTANCE;

    private SimpleMongoHelper simpleMongoHelper;
    private CacheHandler cacheHandler;
    private CollectionHelper collectionHelper;

    private BasicConfig configFile;
    private BasicConfig menusFile;
    private BasicConfig langFile;
    private CommandManager commandManager;

    @Override
    public void onEnable() {
        INSTANCE = this;
        FastInvManager.register(this);

        loadFiles();
        loadDatabase();
        loadCommands();
    }

    @Override
    public void onDisable() {

    }

    private void loadCommands() {
        commandManager = new CommandManager(this);

        new GiftCommand();

        commandManager.getLoadedCommands().forEach(command -> getLogger().info((command.getAssigned().async() ? "Async " : "") + "Command Loaded: " + command.getAssigned().name()));
    }

    private void loadDatabase() {
        simpleMongoHelper = new SimpleMongoHelper(
                Config.MONGO_HOST.toString(),
                Config.MONGO_PORT.toInteger(),
                Config.MONGO_USERNAME.toString(),
                Config.MONGO_PASSWORD.toString(),
                Config.MONGO_DATABASE.toString(),
                Config.MONGO_AUTHDB.toString()
        );
        cacheHandler = new CacheHandler(simpleMongoHelper);
        collectionHelper = new CollectionHelper(simpleMongoHelper, cacheHandler);
    }

    private void loadFiles() {
        configFile = new BasicConfig(this, "config.yml");
        menusFile = new BasicConfig(this, "menus.yml");
        langFile = new BasicConfig(this, "lang.yml");

        Config.loadDefault();
        Menus.loadDefault();
        Lang.loadDefault();
    }

    public static GiftingAndGems i() {
        return INSTANCE;
    }
}
