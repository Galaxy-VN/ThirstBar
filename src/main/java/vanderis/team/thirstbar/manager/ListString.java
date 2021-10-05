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

    public static List<String> listCommandMain = Arrays.asList(commandMain, commandMainCompact);
    public static List<String> listCommandFirst = Arrays.asList(commandReload, commandHelp, commandRefresh);

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
        String text = Method.plugin.getConfig().getString("MessengerRefreshWater");
        if (text == null) text = "";
        text = text.replace("<player>", player.getName()).replace('&', '§');
        player.sendMessage(text);
    }

    public static void messengerErrorUndefinedPlayer(CommandSender sender){
        sender.sendMessage("Player undefined in server or not online");
    }

    public static void messengerErrorNeedInputPlayer(CommandSender sender){
        sender.sendMessage("you need input player");
    }

    public static void messengerErrorHaveNotPerm(CommandSender sender){
        sender.sendMessage("you haven't permission to use this command.");
    }

    public static void messengerErrorListEffect(){
        Bukkit.getConsoleSender().sendRawMessage(ChatColor.RED+"Error in EffectThirsty. You can't use this");
    }

}
