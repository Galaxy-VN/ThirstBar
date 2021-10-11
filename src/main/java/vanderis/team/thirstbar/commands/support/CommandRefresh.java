package vanderis.team.thirstbar.commands.support;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import vanderis.team.thirstbar.manager.ListString;
import vanderis.team.thirstbar.manager.Method;
import vanderis.team.thirstbar.manager.water.ListThirstPlayer;
import vanderis.team.thirstbar.manager.water.ThirstPlayer;

public class CommandRefresh implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args[0].equalsIgnoreCase(ListString.commandRefresh)){
            if (!sender.isOp()
                    && (!(sender instanceof Player)
                    || (!sender.hasPermission(ListString.getPermissionAdmin())
                    && !sender.hasPermission(ListString.getPermissionRefresh())) )) {
                ListString.messengerErrorHaveNotPerm(sender);
                return true;
            }
            if(args.length == 2){
                if(sender instanceof Player){
                    Player player = (Player) sender;
                    ThirstPlayer thirstPlayer = ListThirstPlayer.getWaterPlayer(player);
                    thirstPlayer.setWaterPoint(thirstPlayer.getWaterMax());
                    ListThirstPlayer.changeBossBarPlayer(thirstPlayer);
                    ListThirstPlayer.setEffectThirst(thirstPlayer);
                    ListString.messengerRefresh(player);
                } else {
                    ListString.messengerErrorNeedInputPlayer(sender);
                }
            } else if(args.length >= 3){
                Player player = Bukkit.getServer().getPlayer(args[1]);
                if(Bukkit.getOnlinePlayers().stream().noneMatch(p -> p.getName().equals(args[1]))){
                    ListString.messengerErrorUndefinedPlayer(sender);
                    return true;
                }
                ThirstPlayer thirstPlayer = ListThirstPlayer.getWaterPlayer(player);
                thirstPlayer.setWaterPoint(thirstPlayer.getWaterMax());
                ListThirstPlayer.changeBossBarPlayer(thirstPlayer);
                ListThirstPlayer.setEffectThirst(thirstPlayer);
                ListString.messengerRefresh(player);
            }
            return true;
        }
        if(args[0].equalsIgnoreCase(ListString.commandImmune)){
            if (!sender.isOp()
                    && (!(sender instanceof Player)
                    || (!sender.hasPermission(ListString.getPermissionAdmin())
                    && !sender.hasPermission(ListString.getPermissionImmune())) )) {
                ListString.messengerErrorHaveNotPerm(sender);
                return true;
            }
            if(args.length == 2){
                if(sender instanceof Player){
                    Player player = (Player) sender;
                    ThirstPlayer thirstPlayer = ListThirstPlayer.getWaterPlayer(player);
                    thirstPlayer.setImmune(!thirstPlayer.isImmune());
                    ListThirstPlayer.setEffectThirst(thirstPlayer);
                    ListString.messengerImmune(player);
                } else {
                    ListString.messengerErrorNeedInputPlayer(sender);
                }
            } else if(args.length >= 3){
                Player player = Bukkit.getServer().getPlayer(args[1]);
                if(Bukkit.getOnlinePlayers().stream().noneMatch(p -> p.getName().equals(args[1]))){
                    ListString.messengerErrorUndefinedPlayer(sender);
                    return true;
                }
                ThirstPlayer thirstPlayer = ListThirstPlayer.getWaterPlayer(player);
                thirstPlayer.setImmune(!thirstPlayer.isImmune());
                ListThirstPlayer.setEffectThirst(thirstPlayer);
                ListString.messengerRefresh(player);
            }
            return true;
        }
        if(args[0].equalsIgnoreCase(ListString.commandSet)){
            if (!sender.isOp()
                    && (!(sender instanceof Player)
                    || (!sender.hasPermission(ListString.getPermissionAdmin())
                    && !sender.hasPermission(ListString.getPermissionSet())) )) {
                ListString.messengerErrorHaveNotPerm(sender);
                return true;
            }
            if (!sender.isOp()
                    && (!(sender instanceof Player)
                    || (!sender.hasPermission(ListString.getPermissionAdmin())
                    && !sender.hasPermission(ListString.getPermissionRefresh())) )) {
                ListString.messengerErrorHaveNotPerm(sender);
                return true;
            }
            if(args.length == 2){
                ListString.messengerErrorCommandEmpty(sender);
            } else if(args.length == 3){
                if(!Method.checkConvertDouble(args[1])){
                    ListString.messengerErrorFormatNumber(sender);
                    return true;
                }
                double value = Double.parseDouble(args[1]);
                if(sender instanceof Player){
                    Player player = (Player) sender;
                    ThirstPlayer thirstPlayer = ListThirstPlayer.getWaterPlayer(player);
                    thirstPlayer.setWaterPoint(value);
                    ListThirstPlayer.changeBossBarPlayer(thirstPlayer);
                    ListThirstPlayer.setEffectThirst(thirstPlayer);
                    ListString.messengerSet(player, value);
                } else {
                    ListString.messengerErrorNeedInputPlayer(sender);
                }
            } else if(args.length >= 4){
                if(!Method.checkConvertDouble(args[1])){
                    ListString.messengerErrorFormatNumber(sender);
                    return true;
                }
                double value = Double.parseDouble(args[1]);
                Player player = Bukkit.getServer().getPlayer(args[2]);
                if(Bukkit.getOnlinePlayers().stream().noneMatch(p -> p.getName().equals(args[2]))){
                    ListString.messengerErrorUndefinedPlayer(sender);
                    return true;
                }
                ThirstPlayer thirstPlayer = ListThirstPlayer.getWaterPlayer(player);
                thirstPlayer.setWaterPoint(value);
                ListThirstPlayer.changeBossBarPlayer(thirstPlayer);
                ListThirstPlayer.setEffectThirst(thirstPlayer);
                ListString.messengerSet(player, value);
            }
            return true;
        }
        if(args[0].equalsIgnoreCase(ListString.commandCheck)){
            if (!sender.isOp()
                    && (!(sender instanceof Player)
                    || (!sender.hasPermission(ListString.getPermissionAdmin())
                    && !sender.hasPermission(ListString.getPermissionCheck())) )) {
                ListString.messengerErrorHaveNotPerm(sender);
                return true;
            }
            if(args.length == 2){
                if(sender instanceof Player){
                    Player player = (Player) sender;
                    ThirstPlayer thirstPlayer = ListThirstPlayer.getWaterPlayer(player);
                    ListString.messengerCheck(player, thirstPlayer.getWaterPoint(), thirstPlayer.getWaterMax());
                } else {
                    ListString.messengerErrorNeedInputPlayer(sender);
                }
            } else if(args.length >= 3){
                Player player = Bukkit.getServer().getPlayer(args[1]);
                if(Bukkit.getOnlinePlayers().stream().noneMatch(p -> p.getName().equals(args[1]))){
                    ListString.messengerErrorUndefinedPlayer(sender);
                    return true;
                }
                ThirstPlayer thirstPlayer = ListThirstPlayer.getWaterPlayer(player);
                ListString.messengerCheck(player, thirstPlayer.getWaterPoint(), thirstPlayer.getWaterMax());
            }
            return true;
        }
        if(args[0].equalsIgnoreCase(ListString.commandDisable)){
            if (!sender.isOp()
                    && (!(sender instanceof Player)
                    || (!sender.hasPermission(ListString.getPermissionAdmin())
                    && !sender.hasPermission(ListString.getPermissionDisable())) )) {
                ListString.messengerErrorHaveNotPerm(sender);
                return true;
            }
            if(args.length == 2){
                if(sender instanceof Player){
                    Player player = (Player) sender;
                    ThirstPlayer thirstPlayer = ListThirstPlayer.getWaterPlayer(player);
                    thirstPlayer.setDisable(!thirstPlayer.isDisable());
                    ListThirstPlayer.changeBossBarPlayer(thirstPlayer);
                    ListThirstPlayer.setEffectThirst(thirstPlayer);
                    ListString.messengerDisable(player);
                } else {
                    ListString.messengerErrorNeedInputPlayer(sender);
                }
            } else if(args.length >= 3){
                Player player = Bukkit.getServer().getPlayer(args[1]);
                if(Bukkit.getOnlinePlayers().stream().noneMatch(p -> p.getName().equals(args[1]))){
                    ListString.messengerErrorUndefinedPlayer(sender);
                    return true;
                }
                ThirstPlayer thirstPlayer = ListThirstPlayer.getWaterPlayer(player);
                thirstPlayer.setDisable(!thirstPlayer.isDisable());
                ListThirstPlayer.changeBossBarPlayer(thirstPlayer);
                ListThirstPlayer.setEffectThirst(thirstPlayer);
                ListString.messengerDisable(player);
            }
            return true;
        }
        return false;
    }
}
