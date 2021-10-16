package vanderis.team.thirstbar.commands.support;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import vanderis.team.thirstbar.manager.StorageString;
import vanderis.team.thirstbar.manager.StorageMethod;
import vanderis.team.thirstbar.manager.thirst.PlayersThirstList;
import vanderis.team.thirstbar.manager.thirst.PlayersThirst;

public class RefreshCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args[0].equalsIgnoreCase(StorageString.refreshCommand)){
            if(args.length == 1){
                if (checkNoPermission(sender, StorageString.permissionRefresh)) {
                    StorageString.errorHaveNotPermMessage(sender);
                    return true;
                }
                if(sender instanceof Player){
                    Player player = (Player) sender;
                    PlayersThirst thirstPlayer = PlayersThirstList.getThirstPlayer(player);
                    thirstPlayer.setThirstValue(thirstPlayer.getThirstMax());
                    PlayersThirstList.changePlayerBossbar(thirstPlayer);
                    PlayersThirstList.setEffectThirst(thirstPlayer);
                    StorageString.refreshMessage(player);
                } else {
                    StorageString.errorNeedInputPlayerMessage(sender);
                }
            } else {
                if (checkNoPermission(sender, StorageString.permissionRefreshOther)) {
                    StorageString.errorHaveNotPermMessage(sender);
                    return true;
                }
                Player player = Bukkit.getServer().getPlayer(args[1]);
                if(Bukkit.getOnlinePlayers().stream().noneMatch(p -> p.getName().equals(args[1]))){
                    StorageString.errorUndefinedPlayerMessage(sender);
                    return true;
                }
                PlayersThirst thirstPlayer = PlayersThirstList.getThirstPlayer(player);
                thirstPlayer.setThirstValue(thirstPlayer.getThirstMax());
                PlayersThirstList.changePlayerBossbar(thirstPlayer);
                PlayersThirstList.setEffectThirst(thirstPlayer);
                StorageString.refreshMessage(player);
            }
            return true;
        }
        if(args[0].equalsIgnoreCase(StorageString.immuneCommand)){
            if(args.length == 1){
                if (checkNoPermission(sender, StorageString.permissionImmune)) {
                    StorageString.errorHaveNotPermMessage(sender);
                    return true;
                }
                if(sender instanceof Player){
                    Player player = (Player) sender;
                    PlayersThirst thirstPlayer = PlayersThirstList.getThirstPlayer(player);
                    if(!thirstPlayer.isImmune()) {
                        double value = thirstPlayer.getThirstValue();
                        thirstPlayer.setThirstValue(thirstPlayer.getThirstMax());
                        PlayersThirstList.setEffectThirst(thirstPlayer);
                        thirstPlayer.setThirstValue(value);
                        thirstPlayer.setImmune(!thirstPlayer.isImmune());
                    } else {
                        thirstPlayer.setImmune(!thirstPlayer.isImmune());
                        PlayersThirstList.setEffectThirst(thirstPlayer);
                    }
                    PlayersThirstList.changePlayerBossbar(thirstPlayer);
                    StorageString.immuneMessage(player);
                } else {
                    StorageString.errorNeedInputPlayerMessage(sender);
                }
            } else {
                if (checkNoPermission(sender, StorageString.permissionImmuneOther)) {
                    StorageString.errorHaveNotPermMessage(sender);
                    return true;
                }
                Player player = Bukkit.getServer().getPlayer(args[1]);
                if(Bukkit.getOnlinePlayers().stream().noneMatch(p -> p.getName().equals(args[1]))){
                    StorageString.errorUndefinedPlayerMessage(sender);
                    return true;
                }
                PlayersThirst thirstPlayer = PlayersThirstList.getThirstPlayer(player);
                if(!thirstPlayer.isImmune()) {
                    double value = thirstPlayer.getThirstValue();
                    thirstPlayer.setThirstValue(thirstPlayer.getThirstMax());
                    PlayersThirstList.setEffectThirst(thirstPlayer);
                    thirstPlayer.setThirstValue(value);
                    thirstPlayer.setImmune(!thirstPlayer.isImmune());
                } else {
                    thirstPlayer.setImmune(!thirstPlayer.isImmune());
                    PlayersThirstList.setEffectThirst(thirstPlayer);
                }
                PlayersThirstList.changePlayerBossbar(thirstPlayer);
                StorageString.refreshMessage(player);
            }
            return true;
        }
        if(args[0].equalsIgnoreCase(StorageString.setCommand)){
            if(args.length == 1){
                StorageString.errorCommandEmptyMessage(sender);
            } else if(args.length == 2){
                if (checkNoPermission(sender, StorageString.permissionSet)) {
                    StorageString.errorHaveNotPermMessage(sender);
                    return true;
                }
                if(!StorageMethod.checkConvertDouble(args[1])){
                    StorageString.errorFormatNumberMessage(sender);
                    return true;
                }
                double value = Double.parseDouble(args[1]);
                if(sender instanceof Player){
                    Player player = (Player) sender;
                    PlayersThirst thirstPlayer = PlayersThirstList.getThirstPlayer(player);
                    thirstPlayer.setThirstValue(value);
                    PlayersThirstList.changePlayerBossbar(thirstPlayer);
                    PlayersThirstList.setEffectThirst(thirstPlayer);
                    StorageString.setMessage(player, value);
                } else {
                    StorageString.errorNeedInputPlayerMessage(sender);
                }
            } else {
                if (checkNoPermission(sender, StorageString.permissionSetOther)) {
                    StorageString.errorHaveNotPermMessage(sender);
                    return true;
                }
                if(!StorageMethod.checkConvertDouble(args[1])){
                    StorageString.errorFormatNumberMessage(sender);
                    return true;
                }
                double value = Double.parseDouble(args[1]);
                Player player = Bukkit.getServer().getPlayer(args[2]);
                if(Bukkit.getOnlinePlayers().stream().noneMatch(p -> p.getName().equals(args[2]))){
                    StorageString.errorUndefinedPlayerMessage(sender);
                    return true;
                }
                PlayersThirst thirstPlayer = PlayersThirstList.getThirstPlayer(player);
                thirstPlayer.setThirstValue(value);
                PlayersThirstList.changePlayerBossbar(thirstPlayer);
                PlayersThirstList.setEffectThirst(thirstPlayer);
                StorageString.setMessage(player, value);
            }
            return true;
        }
        if(args[0].equalsIgnoreCase(StorageString.addCommand)) {
            if(args.length == 1) {
                StorageString.errorCommandEmptyMessage(sender);
            } else if(args.length == 2) {
                if (checkNoPermission(sender, StorageString.permissionAdd)) {
                    StorageString.errorHaveNotPermMessage(sender);
                    return true;
                }
                if(!StorageMethod.checkConvertDouble(args[1])){
                    StorageString.errorFormatNumberMessage(sender);
                    return true;
                }
                double value = Double.parseDouble(args[1]);
                if(sender instanceof Player){
                    Player player = (Player) sender;
                    PlayersThirst thirstPlayer = PlayersThirstList.getThirstPlayer(player);
                    thirstPlayer.setThirstValue(thirstPlayer.getThirstValue() + value);
                    PlayersThirstList.changePlayerBossbar(thirstPlayer);
                    PlayersThirstList.setEffectThirst(thirstPlayer);
                    StorageString.addMessage(player, value);
                } else {
                    StorageString.errorNeedInputPlayerMessage(sender);
                }
            } else {
                if (checkNoPermission(sender, StorageString.permissionAddOther)) {
                    StorageString.errorHaveNotPermMessage(sender);
                    return true;
                }
                if(!StorageMethod.checkConvertDouble(args[1])){
                    StorageString.errorFormatNumberMessage(sender);
                    return true;
                }
                double value = Double.parseDouble(args[1]);
                Player player = Bukkit.getServer().getPlayer(args[2]);
                if(Bukkit.getOnlinePlayers().stream().noneMatch(p -> p.getName().equals(args[2]))){
                    StorageString.errorUndefinedPlayerMessage(sender);
                    return true;
                }
                PlayersThirst thirstPlayer = PlayersThirstList.getThirstPlayer(player);
                thirstPlayer.setThirstValue(thirstPlayer.getThirstValue() + value);
                PlayersThirstList.changePlayerBossbar(thirstPlayer);
                PlayersThirstList.setEffectThirst(thirstPlayer);
                StorageString.addMessage(player, value);
            }
            return true;
        }
        if(args[0].equalsIgnoreCase(StorageString.checkCommand)){
            if(args.length == 1){
                if (checkNoPermission(sender, StorageString.permissionCheck)) {
                    StorageString.errorHaveNotPermMessage(sender);
                    return true;
                }
                if(sender instanceof Player){
                    Player player = (Player) sender;
                    PlayersThirst thirstPlayer = PlayersThirstList.getThirstPlayer(player);
                    StorageString.checkMessage(sender, player, thirstPlayer.getThirstValue(), thirstPlayer.getThirstMax());
                } else {
                    StorageString.errorNeedInputPlayerMessage(sender);
                }
            } else {
                if (checkNoPermission(sender, StorageString.permissionCheckOther)) {
                    StorageString.errorHaveNotPermMessage(sender);
                    return true;
                }
                Player player = Bukkit.getServer().getPlayer(args[1]);
                if(Bukkit.getOnlinePlayers().stream().noneMatch(p -> p.getName().equals(args[1]))){
                    StorageString.errorUndefinedPlayerMessage(sender);
                    return true;
                }
                PlayersThirst thirstPlayer = PlayersThirstList.getThirstPlayer(player);
                StorageString.checkMessage(sender, player, thirstPlayer.getThirstValue(), thirstPlayer.getThirstMax());
            }
            return true;
        }
        if(args[0].equalsIgnoreCase(StorageString.disableCommand)){
            if(args.length == 1){
                if (checkNoPermission(sender, StorageString.permissionDisable)) {
                    StorageString.errorHaveNotPermMessage(sender);
                    return true;
                }
                if(sender instanceof Player){
                    Player player = (Player) sender;
                    PlayersThirst thirstPlayer = PlayersThirstList.getThirstPlayer(player);
                    thirstPlayer.setDisable(!thirstPlayer.isDisable());
                    PlayersThirstList.changePlayerBossbar(thirstPlayer);
                    PlayersThirstList.setEffectThirst(thirstPlayer);
                    StorageString.disableMessage(player);
                } else {
                    StorageString.errorNeedInputPlayerMessage(sender);
                }
            } else {
                if (checkNoPermission(sender, StorageString.permissionDisableOther)) {
                    StorageString.errorHaveNotPermMessage(sender);
                    return true;
                }
                Player player = Bukkit.getServer().getPlayer(args[1]);
                if(Bukkit.getOnlinePlayers().stream().noneMatch(p -> p.getName().equals(args[1]))){
                    StorageString.errorUndefinedPlayerMessage(sender);
                    return true;
                }
                PlayersThirst thirstPlayer = PlayersThirstList.getThirstPlayer(player);
                thirstPlayer.setDisable(!thirstPlayer.isDisable());
                PlayersThirstList.changePlayerBossbar(thirstPlayer);
                PlayersThirstList.setEffectThirst(thirstPlayer);
                StorageString.disableMessage(player);
            }
            return true;
        }
        return false;
    }

    private boolean checkNoPermission(CommandSender sender, String perm){
        return (sender instanceof Player) && !sender.isOp()
                && !sender.hasPermission(StorageString.permissionAdmin)
                && !sender.hasPermission(perm);
    }
}
