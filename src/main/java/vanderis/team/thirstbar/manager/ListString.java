package vanderis.team.thirstbar.manager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class ListString {

    public static String mainCommand = "ThirstBar";
    public static String mainCompactCommand = "TB";
    public static String reloadCommand = "reload";
    public static String helpCommand = "help";
    public static String refreshCommand = "refresh";
    public static String immuneCommand = "immune";
    public static String setCommand = "set";
    public static String checkCommand = "check";
    public static String disableCommand = "disable";

    public static List<String> commandMainList = Arrays.asList(mainCommand, mainCompactCommand);
    public static List<String> commandFirstList = Arrays.asList(reloadCommand, helpCommand, refreshCommand,
            immuneCommand, setCommand, checkCommand, disableCommand);

    public static String getPermissionAdmin(){
        String perm = Method.plugin.getConfig().getString("PermissionAdmin");
        if (perm == null) perm = "thirstbar.admin";
        return perm;
    }

    public static String getPermissionRefresh(){
        String perm = Method.plugin.getConfig().getString("PermissionRefresh");
        if (perm == null) perm = "thirstbar.refresh";
        return perm;
    }

    public static String getPermissionImmune(){
        String perm = Method.plugin.getConfig().getString("PermissionImmune");
        if (perm == null) perm = "thirstbar.immune";
        return perm;
    }

    public static String getPermissionSet(){
        String perm = Method.plugin.getConfig().getString("PermissionSet");
        if (perm == null) perm = "thirstbar.set";
        return perm;
    }

    public static String getPermissionDisable(){
        String perm = Method.plugin.getConfig().getString("PermissionDisable");
        if (perm == null) perm = "thirstbar.disable";
        return perm;
    }

    public static String getPermissionCheck(){
        String perm = Method.plugin.getConfig().getString("PermissionCheck");
        if (perm == null) perm = "thirstbar.check";
        return perm;
    }

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
        String text = Method.plugin.getConfig().getString("RefreshMessenger");
        if (text == null) text = "";
        text = text.replace("<player>", player.getName()).replace('&', '§');
        player.sendMessage(text);
    }

    public static void immuneMessage(Player player) {
        String text = Method.plugin.getConfig().getString("ImmuneMessenger");
        if (text == null) text = "";
        text = text.replace("<player>", player.getName()).replace('&', '§');
        player.sendMessage(text);
    }

    public static void setMessage(Player player, double value) {
        String text = Method.plugin.getConfig().getString("SetThirstPointMessage");
        if (text == null) text = "";
        text = text.replace("<player>", player.getName())
                .replace("<point>", ""+Math.ceil(value*100)/100)
                .replace('&', '§');
        player.sendMessage(text);
    }

    public static void checkMessage(Player player, double point, double pointMax) {
        String text = Method.plugin.getConfig().getString("CheckThirstPointMessage");
        if (text == null) text = "";
        text = text.replace("<player>", player.getName())
                .replace("<point>", ""+Math.ceil(point*100)/100)
                .replace("<pointMax>", ""+Math.ceil(pointMax*100)/100)
                .replace('&', '§');
        player.sendMessage(text);
    }

    public static void disableMessage(Player player) {
        String text = Method.plugin.getConfig().getString("DisableMessage");
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
        sender.sendMessage("you need input player");
    }

    public static void errorHaveNotPermMessage(CommandSender sender){
        String text = Method.plugin.getConfig().getString("DoNotHavePermissionMessage");
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
