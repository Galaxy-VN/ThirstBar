package vanderis.team.thirstbar.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import vanderis.team.thirstbar.commands.support.RefreshCommand;
import vanderis.team.thirstbar.manager.ListString;
import vanderis.team.thirstbar.manager.Method;
import vanderis.team.thirstbar.manager.thirst.PlayersThirstList;

public class CommandMain implements CommandExecutor {

    private final RefreshCommand optionThirst = new RefreshCommand();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase(ListString.mainCommand) || cmd.getName().equalsIgnoreCase(ListString.mainCompactCommand)) {

            if (args.length == 0) {
                ListString.commandMainMessage(sender);
                return true;
            }
            if (args[0].equalsIgnoreCase(ListString.helpCommand)) {
                ListString.commandHelpMessage(sender);
                return true;
            }
            if (args[0].equalsIgnoreCase(ListString.reloadCommand)) {
                if (!sender.isOp()
                        && (!(sender instanceof Player)
                        || !sender.hasPermission(ListString.getPermissionAdmin()))) {
                    ListString.errorHaveNotPermMessage(sender);
                    return true;
                }
                Method.plugin.reloadConfig();
                Method.listItemConsume = Method.plugin.getConfig().getStringList("itemConsumeRegenThirst");
                Method.fileThirstEffect.reloadFileYAML();
                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                    PlayersThirstList.removeThirstPlayer(player);
                    PlayersThirstList.addThirstPlayer(player);
                }
                Method.setListThirst();
                ListString.commandReloadMessage(sender);
                return true;
            }
            if (args[0].equalsIgnoreCase(ListString.refreshCommand)) {
                optionThirst.onCommand(sender, cmd, label, args);
                return true;
            }
        }
        return false;
    }
}
