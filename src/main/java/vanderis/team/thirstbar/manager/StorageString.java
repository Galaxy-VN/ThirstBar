package vanderis.team.thirstbar.manager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class StorageString {

    public static String mainCommand = "ThirstBar";
    public static String mainCompactCommand = "TB";
    public static String reloadCommand = "reload";
    public static String helpCommand = "help";
    public static String refreshCommand = "refresh";
    public static String immuneCommand = "immune";
    public static String setCommand = "set";
    public static String checkCommand = "check";
    public static String disableCommand = "disable";

    public static String permissionAdmin = "thirstbar.admin";
    public static String permissionRefresh = "thirstbar.refresh";
    public static String permissionRefreshOther = "thirstbar.refresh.other";
    public static String permissionImmune = "thirstbar.immune";
    public static String permissionImmuneOther = "thirstbar.immune.other";
    public static String permissionSet = "thirstbar.set";
    public static String permissionSetOther = "thirstbar.set.other";
    public static String permissionDisable = "thirstbar.disable";
    public static String permissionDisableOther = "thirstbar.disable.other";
    public static String permissionCheck = "thirstbar.check";
    public static String permissionCheckOther = "thirstbar.check.other";

    public static List<String> commandMainList = Arrays.asList(mainCommand, mainCompactCommand);
    public static List<String> commandFirstList = Arrays.asList(reloadCommand, helpCommand, refreshCommand,
            immuneCommand, setCommand, checkCommand, disableCommand);

    public static void commandMainMessage(CommandSender sender){
        sender.sendMessage("this is command main");
    }

    public static void commandHelpMessage(CommandSender sender){
        sender.sendMessage("this is command help");
    }

    public static void commandReloadMessage(CommandSender sender){
        sender.sendMessage("successful reload");
    }

    public static void refreshMessage(Player player) {
        String text = StorageMethod.plugin.getConfig().getString("RefreshMessenger");
        if (text == null) text = "";
        text = text.replace("<player>", player.getName()).replace('&', '§');
        player.sendMessage(text);
    }

    public static void immuneMessage(Player player) {
        String text = StorageMethod.plugin.getConfig().getString("ImmuneMessenger");
        if (text == null) text = "";
        text = text.replace("<player>", player.getName()).replace('&', '§');
        player.sendMessage(text);
    }

    public static void setMessage(Player player, double value) {
        String text = StorageMethod.plugin.getConfig().getString("SetThirstValueMessage");
        if (text == null) text = "";
        text = text.replace("<player>", player.getName())
                .replace("<value>", ""+Math.ceil(value*100)/100)
                .replace('&', '§');
        player.sendMessage(text);
    }

    public static void checkMessage(Player player, double value, double valueMax) {
        String text = StorageMethod.plugin.getConfig().getString("CheckThirstValueMessage");
        if (text == null) text = "";
        text = text.replace("<player>", player.getName())
                .replace("<value>", ""+Math.ceil(value*100)/100)
                .replace("<valueMax>", ""+Math.ceil(valueMax*100)/100)
                .replace('&', '§');
        player.sendMessage(text);
    }

    public static void disableMessage(Player player) {
        String text = StorageMethod.plugin.getConfig().getString("DisableMessage");
        if (text == null) text = "";
        text = text.replace("<player>", player.getName()).replace('&', '§');
        player.sendMessage(text);
    }

    public static void errorUndefinedPlayerMessage(CommandSender sender){
        sender.sendMessage("Player undefined in server or not online");
    }

    public static void errorCommandEmptyMessage(CommandSender sender){
        sender.sendMessage("This command can't empty.");
    }

    public static void errorNeedInputPlayerMessage(CommandSender sender){
        sender.sendMessage("You need input player.");
    }

    public static void errorHaveNotPermMessage(CommandSender sender){
        String text = StorageMethod.plugin.getConfig().getString("DontHavePermissionMessage");
        if (text == null) text = "";
        text = text.replace('&', '§');
        sender.sendMessage(text);
    }

    public static void errorFormatNumberMessage(CommandSender sender){
        sender.sendMessage("Can't format string to number.");
    }

    public static void errorListEffectMessage(){
        Bukkit.getConsoleSender().sendRawMessage(ChatColor.RED+"Error in EffectThirsty. You can't use this");
    }

}
