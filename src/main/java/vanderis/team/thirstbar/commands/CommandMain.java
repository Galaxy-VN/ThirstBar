package vanderis.team.thirstbar.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import vanderis.team.thirstbar.commands.support.CommandRefresh;
import vanderis.team.thirstbar.manager.ListString;
import vanderis.team.thirstbar.manager.Method;
import vanderis.team.thirstbar.manager.water.ListThirstPlayer;

public class CommandMain implements CommandExecutor {

    private final CommandRefresh optionWater = new CommandRefresh();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase(ListString.commandMain) || cmd.getName().equalsIgnoreCase(ListString.commandMainCompact)) {

            if (args.length == 0) {
                ListString.messengerCommandMain(sender);
                return true;
            }
            if (args[0].equalsIgnoreCase(ListString.commandHelp)) {
                ListString.messengerCommandHelp(sender);
                return true;
            }
            if (args[0].equalsIgnoreCase(ListString.commandReload)) {
                if (!sender.isOp()
                        && (!(sender instanceof Player)
                        || !sender.hasPermission(ListString.getPermissionAdmin()))) {
                    ListString.messengerErrorHaveNotPerm(sender);
                    return true;
                }
                Method.plugin.reloadConfig();
                Method.listFood = Method.plugin.getConfig().getStringList("FoodRegenWater");
                Method.fileThirstEffect.reloadFileYAML();
                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                    ListThirstPlayer.removeWaterPlayer(player);
                    ListThirstPlayer.addWaterPlayer(player);
                }
                Method.setListThirst();
                ListString.messengerCommandReload(sender);
                return true;
            }
            if (args[0].equalsIgnoreCase(ListString.commandRefresh)) {
                optionWater.onCommand(sender, cmd, label, args);
                return true;
            }
        }
        return false;
    }
}
