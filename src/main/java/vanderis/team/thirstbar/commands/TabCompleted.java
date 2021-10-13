package vanderis.team.thirstbar.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import vanderis.team.thirstbar.manager.StorageString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TabCompleted implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        if (cmd.getName().equalsIgnoreCase(StorageString.mainCommand) || cmd.getName().equalsIgnoreCase(StorageString.mainCompactCommand)) {
            if(args.length == 1){
                return advancedTabCompleted(args, 0, StorageString.commandFirstList);
            }
            if(args[0].equalsIgnoreCase(StorageString.refreshCommand)){
                if(args.length == 2) {
                    return advancedTabCompleted(args, 1, getAllOnlinePlayer());
                }
            }
        }
        return null;
    }

    private List<String> advancedTabCompleted(String[] args, int num, List<String> list) {
        List<String> completions = new ArrayList<>();
        StringUtil.copyPartialMatches(args[num], list, completions);
        Collections.sort(completions);
        return completions;
    }

    private List<String> getAllOnlinePlayer(){
        List<String> list = new ArrayList<>();
        for(Player player : Bukkit.getServer().getOnlinePlayers()){
            list.add(player.getName());
        }
        return list;
    }

}
