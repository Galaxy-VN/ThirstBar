package vanderis.team.thirstbar.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import vanderis.team.thirstbar.commands.support.RefreshCommand;
import vanderis.team.thirstbar.manager.StorageString;
import vanderis.team.thirstbar.manager.StorageMethod;
import vanderis.team.thirstbar.manager.thirst.PlayersThirstList;

public class CommandMain implements CommandExecutor {

    private final RefreshCommand optionThirst = new RefreshCommand();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase(StorageString.mainCommand) || cmd.getName().equalsIgnoreCase(StorageString.mainCompactCommand)) {

            if (args.length == 0) {
                StorageString.commandMainMessage(sender);
                return true;
            }
            if (args[0].equalsIgnoreCase(StorageString.helpCommand)) {
                StorageString.commandHelpMessage(sender);
                return true;
            }
            if (args[0].equalsIgnoreCase(StorageString.reloadCommand)) {
                if ((sender instanceof Player) && !sender.isOp() && !sender.hasPermission(StorageString.permissionAdmin)) {
                    StorageString.errorHaveNotPermMessage(sender);
                    return true;
                }
                StorageMethod.plugin.reloadConfig();
                StorageMethod.listItemConsume = StorageMethod.plugin.getConfig().getStringList("itemConsumeRegenThirst");
                StorageMethod.fileThirstEffect.reloadFileYAML();
                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                    PlayersThirstList.removeThirstPlayer(player);
                    PlayersThirstList.addThirstPlayer(player);
                }
                StorageMethod.setListThirst();
                StorageString.commandReloadMessage(sender);
                return true;
            }
            if (args[0].equalsIgnoreCase(StorageString.refreshCommand)) {
                optionThirst.onCommand(sender, cmd, label, args);
                return true;
            }
        }
        return false;
    }
}
