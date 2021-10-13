package vanderis.team.thirstbar.commands.support;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import vanderis.team.thirstbar.manager.ListString;
import vanderis.team.thirstbar.manager.Method;
import vanderis.team.thirstbar.manager.thirst.PlayersThirstList;
import vanderis.team.thirstbar.manager.thirst.PlayersThirst;

public class RefreshCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args[0].equalsIgnoreCase(ListString.refreshCommand)){
            if (!sender.isOp()
                    && (!(sender instanceof Player)
                    || (!sender.hasPermission(ListString.getPermissionAdmin())
                    && !sender.hasPermission(ListString.getPermissionRefresh())) )) {
                ListString.errorHaveNotPermMessage(sender);
                return true;
            }
            if(args.length == 2){
                if(sender instanceof Player){
                    Player player = (Player) sender;
                    PlayersThirst thirstPlayer = PlayersThirstList.getThirstPlayer(player);
                    thirstPlayer.setThirstValue(thirstPlayer.getThirstMax());
                    PlayersThirstList.changePlayerBossbar(thirstPlayer);
                    PlayersThirstList.setEffectThirst(thirstPlayer);
                    ListString.refreshMessage(player);
                } else {
                    ListString.errorNeedInputPlayerMessage(sender);
                }
            } else if(args.length >= 3){
                Player player = Bukkit.getServer().getPlayer(args[1]);
                if(Bukkit.getOnlinePlayers().stream().noneMatch(p -> p.getName().equals(args[1]))){
                    ListString.errorUndefinedPlayerMessage(sender);
                    return true;
                }
                PlayersThirst thirstPlayer = PlayersThirstList.getThirstPlayer(player);
                thirstPlayer.setThirstValue(thirstPlayer.getThirstMax());
                PlayersThirstList.changePlayerBossbar(thirstPlayer);
                PlayersThirstList.setEffectThirst(thirstPlayer);
                ListString.refreshMessage(player);
            }
            return true;
        }
        if(args[0].equalsIgnoreCase(ListString.immuneCommand)){
            if (!sender.isOp()
                    && (!(sender instanceof Player)
                    || (!sender.hasPermission(ListString.getPermissionAdmin())
                    && !sender.hasPermission(ListString.getPermissionImmune())) )) {
                ListString.errorHaveNotPermMessage(sender);
                return true;
            }
            if(args.length == 2){
                if(sender instanceof Player){
                    Player player = (Player) sender;
                    PlayersThirst thirstPlayer = PlayersThirstList.getThirstPlayer(player);
                    thirstPlayer.setImmune(!thirstPlayer.isImmune());
                    PlayersThirstList.setEffectThirst(thirstPlayer);
                    ListString.immuneMessage(player);
                } else {
                    ListString.errorNeedInputPlayerMessage(sender);
                }
            } else if(args.length >= 3){
                Player player = Bukkit.getServer().getPlayer(args[1]);
                if(Bukkit.getOnlinePlayers().stream().noneMatch(p -> p.getName().equals(args[1]))){
                    ListString.errorUndefinedPlayerMessage(sender);
                    return true;
                }
                PlayersThirst thirstPlayer = PlayersThirstList.getThirstPlayer(player);
                thirstPlayer.setImmune(!thirstPlayer.isImmune());
                PlayersThirstList.setEffectThirst(thirstPlayer);
                ListString.refreshMessage(player);
            }
            return true;
        }
        if(args[0].equalsIgnoreCase(ListString.setCommand)){
            if (!sender.isOp()
                    && (!(sender instanceof Player)
                    || (!sender.hasPermission(ListString.getPermissionAdmin())
                    && !sender.hasPermission(ListString.getPermissionSet())) )) {
                ListString.errorHaveNotPermMessage(sender);
                return true;
            }
            if (!sender.isOp()
                    && (!(sender instanceof Player)
                    || (!sender.hasPermission(ListString.getPermissionAdmin())
                    && !sender.hasPermission(ListString.getPermissionRefresh())) )) {
                ListString.errorHaveNotPermMessage(sender);
                return true;
            }
            if(args.length == 2){
                ListString.errorCommandEmptyMessage(sender);
            } else if(args.length == 3){
                if(!Method.checkConvertDouble(args[1])){
                    ListString.errorFormatNumberMessage(sender);
                    return true;
                }
                double value = Double.parseDouble(args[1]);
                if(sender instanceof Player){
                    Player player = (Player) sender;
                    PlayersThirst thirstPlayer = PlayersThirstList.getThirstPlayer(player);
                    thirstPlayer.setThirstValue(value);
                    PlayersThirstList.changePlayerBossbar(thirstPlayer);
                    PlayersThirstList.setEffectThirst(thirstPlayer);
                    ListString.setMessage(player, value);
                } else {
                    ListString.errorNeedInputPlayerMessage(sender);
                }
            } else if(args.length >= 4){
                if(!Method.checkConvertDouble(args[1])){
                    ListString.errorFormatNumberMessage(sender);
                    return true;
                }
                double value = Double.parseDouble(args[1]);
                Player player = Bukkit.getServer().getPlayer(args[2]);
                if(Bukkit.getOnlinePlayers().stream().noneMatch(p -> p.getName().equals(args[2]))){
                    ListString.errorUndefinedPlayerMessage(sender);
                    return true;
                }
                PlayersThirst thirstPlayer = PlayersThirstList.getThirstPlayer(player);
                thirstPlayer.setThirstValue(value);
                PlayersThirstList.changePlayerBossbar(thirstPlayer);
                PlayersThirstList.setEffectThirst(thirstPlayer);
                ListString.setMessage(player, value);
            }
            return true;
        }
        if(args[0].equalsIgnoreCase(ListString.checkCommand)){
            if (!sender.isOp()
                    && (!(sender instanceof Player)
                    || (!sender.hasPermission(ListString.getPermissionAdmin())
                    && !sender.hasPermission(ListString.getPermissionCheck())) )) {
                ListString.errorHaveNotPermMessage(sender);
                return true;
            }
            if(args.length == 2){
                if(sender instanceof Player){
                    Player player = (Player) sender;
                    PlayersThirst thirstPlayer = PlayersThirstList.getThirstPlayer(player);
                    ListString.checkMessage(player, thirstPlayer.getThirstValue(), thirstPlayer.getThirstMax());
                } else {
                    ListString.errorNeedInputPlayerMessage(sender);
                }
            } else if(args.length >= 3){
                Player player = Bukkit.getServer().getPlayer(args[1]);
                if(Bukkit.getOnlinePlayers().stream().noneMatch(p -> p.getName().equals(args[1]))){
                    ListString.errorUndefinedPlayerMessage(sender);
                    return true;
                }
                PlayersThirst thirstPlayer = PlayersThirstList.getThirstPlayer(player);
                ListString.checkMessage(player, thirstPlayer.getThirstValue(), thirstPlayer.getThirstMax());
            }
            return true;
        }
        if(args[0].equalsIgnoreCase(ListString.disableCommand)){
            if (!sender.isOp()
                    && (!(sender instanceof Player)
                    || (!sender.hasPermission(ListString.getPermissionAdmin())
                    && !sender.hasPermission(ListString.getPermissionDisable())) )) {
                ListString.errorHaveNotPermMessage(sender);
                return true;
            }
            if(args.length == 2){
                if(sender instanceof Player){
                    Player player = (Player) sender;
                    PlayersThirst thirstPlayer = PlayersThirstList.getThirstPlayer(player);
                    thirstPlayer.setDisable(!thirstPlayer.isDisable());
                    PlayersThirstList.changePlayerBossbar(thirstPlayer);
                    PlayersThirstList.setEffectThirst(thirstPlayer);
                    ListString.disableMessage(player);
                } else {
                    ListString.errorNeedInputPlayerMessage(sender);
                }
            } else if(args.length >= 3){
                Player player = Bukkit.getServer().getPlayer(args[1]);
                if(Bukkit.getOnlinePlayers().stream().noneMatch(p -> p.getName().equals(args[1]))){
                    ListString.errorUndefinedPlayerMessage(sender);
                    return true;
                }
                PlayersThirst thirstPlayer = PlayersThirstList.getThirstPlayer(player);
                thirstPlayer.setDisable(!thirstPlayer.isDisable());
                PlayersThirstList.changePlayerBossbar(thirstPlayer);
                PlayersThirstList.setEffectThirst(thirstPlayer);
                ListString.disableMessage(player);
            }
            return true;
        }
        return false;
    }
}
