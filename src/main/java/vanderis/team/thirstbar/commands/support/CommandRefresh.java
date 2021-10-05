package vanderis.team.thirstbar.commands.support;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import vanderis.team.thirstbar.manager.ListString;
import vanderis.team.thirstbar.manager.water.ListThirstPlayer;
import vanderis.team.thirstbar.manager.water.ThirstPlayer;

public class CommandRefresh implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args[0].equalsIgnoreCase(ListString.commandRefresh)){
            if(sender instanceof Player) {
                Player player = (Player) sender;
                ThirstPlayer thirstPlayer = ListThirstPlayer.getWaterPlayer(player);
                thirstPlayer.setWaterPoint(thirstPlayer.getWaterMax());
                ListThirstPlayer.changeBossBarPlayer(thirstPlayer);
                ListThirstPlayer.setEffectThirst(thirstPlayer);
                ListString.messengerRefresh(player);
                return true;
            } else {
                if(args[1] == null){
                    ListString.messengerErrorNeedInputPlayer(sender);
                    return true;
                }
                Player player = Bukkit.getServer().getPlayer(args[1]);
                if(player == null){
                    ListString.messengerErrorUndefinedPlayer(sender);
                    return true;
                }
                ThirstPlayer thirstPlayer = ListThirstPlayer.getWaterPlayer(player);
                thirstPlayer.setWaterPoint(thirstPlayer.getWaterMax());
                ListString.messengerRefresh(player);
            }
        }
        return false;
    }
}
