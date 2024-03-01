package info.preva1l.giftingandgems.commands;

import info.preva1l.giftingandgems.config.Config;
import info.preva1l.giftingandgems.config.Lang;
import info.preva1l.giftingandgems.guis.GiftingGUI;
import info.preva1l.giftingandgems.utils.Sound;
import info.preva1l.giftingandgems.utils.Text;
import info.preva1l.giftingandgems.utils.commands.Command;
import info.preva1l.giftingandgems.utils.commands.CommandArgs;
import info.preva1l.giftingandgems.utils.commands.CommandArguments;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class GiftCommand extends Command {

    @CommandArgs(name = "gift", aliases = {"giftrank", "rankgift", "buyranks"})
    public void execute(CommandArguments command) {
        Player sender = command.getPlayer();
        OfflinePlayer target;
        if (Config.ALLOW_OFFLINE_GIFTING.toBoolean()) {
            target = command.getArgs().length != 0 ? Bukkit.getOfflinePlayer(command.getArgs()[0]) : null;
        } else {
            target = command.getArgs().length != 0 ? Bukkit.getPlayer(command.getArgs()[0]) : null;
        }
        if (target == null) {
            sender.sendMessage(Text.pluginMessage(Lang.ERROR_NO_PLAYER.toFormattedString()));
            Sound.fail(sender);
            return;
        }
        if (!target.isOnline()) {
            sender.sendMessage(Text.pluginMessage(Lang.WARNING_NOT_ONLINE.toFormattedString()));
            Sound.warning(sender);
        }

        new GiftingGUI(sender, target).open(sender);
    }
}
