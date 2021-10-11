package vanderis.team.thirstbar.manager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class ListString {

    public static String commandMain = "ThirstBar";
    public static String commandMainCompact = "TB";
    public static String commandReload = "reload";
    public static String commandHelp = "help";
    public static String commandRefresh = "refresh";
    public static String commandImmune = "immune";
    public static String commandSet = "set";
    public static String commandCheck = "check";
    public static String commandDisable = "disable";

    public static List<String> listCommandMain = Arrays.asList(commandMain, commandMainCompact);
    public static List<String> listCommandFirst = Arrays.asList(commandReload, commandHelp, commandRefresh,
            commandImmune, commandSet, commandCheck, commandDisable);

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

    public static void messengerCommandMain(CommandSender sender){
        sender.sendMessage("this is command main");
    }

    public static void messengerCommandHelp(CommandSender sender){
        sender.sendMessage("this is command help");
    }

    public static void messengerCommandReload(CommandSender sender){
        sender.sendMessage("successful reload");
    }

    public static void messengerRefresh(Player player) {
        String text = Method.plugin.getConfig().getString("RefreshMessenger");
        if (text == null) text = "";
        text = text.replace("<player>", player.getName()).replace('&', '§');
        player.sendMessage(text);
    }

    public static void messengerImmune(Player player) {
        String text = Method.plugin.getConfig().getString("ImmuneMessenger");
        if (text == null) text = "";
        text = text.replace("<player>", player.getName()).replace('&', '§');
        player.sendMessage(text);
    }

    public static void messengerSet(Player player, double value) {
        String text = Method.plugin.getConfig().getString("SetThirstPointMessage");
        if (text == null) text = "";
        text = text.replace("<player>", player.getName())
                .replace("<point>", ""+Math.ceil(value*100)/100)
                .replace('&', '§');
        player.sendMessage(text);
    }

    public static void messengerCheck(Player player, double point, double pointMax) {
        String text = Method.plugin.getConfig().getString("CheckThirstPointMessage");
        if (text == null) text = "";
        text = text.replace("<player>", player.getName())
                .replace("<point>", ""+Math.ceil(point*100)/100)
                .replace("<pointMax>", ""+Math.ceil(pointMax*100)/100)
                .replace('&', '§');
        player.sendMessage(text);
    }

    public static void messengerDisable(Player player) {
        String text = Method.plugin.getConfig().getString("DisableMessage");
        if (text == null) text = "";
        text = text.replace("<player>", player.getName()).replace('&', '§');
        player.sendMessage(text);
    }

    public static void messengerErrorUndefinedPlayer(CommandSender sender){
        sender.sendMessage("Player undefined in server or not online");
    }

    public static void messengerErrorCommandEmpty(CommandSender sender){
        sender.sendMessage("This command can't empty.");
    }

    public static void messengerErrorNeedInputPlayer(CommandSender sender){
        sender.sendMessage("you need input player");
    }

    public static void messengerErrorHaveNotPerm(CommandSender sender){
        String text = Method.plugin.getConfig().getString("DoNotHavePermissionMessage");
        if (text == null) text = "";
        text = text.replace('&', '§');
        sender.sendMessage(text);
    }

    public static void messengerErrorFormatNumber(CommandSender sender){
        sender.sendMessage("Can't format string to number.");
    }

    public static void messengerErrorListEffect(){
        Bukkit.getConsoleSender().sendRawMessage(ChatColor.RED+"Error in EffectThirsty. You can't use this");
    }

}
