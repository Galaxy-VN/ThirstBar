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
    public static List<String> commandFirstList = Arrays.asList(reloadCommand, refreshCommand,
            immuneCommand, setCommand, checkCommand, disableCommand);

    public static void commandMainMessage(CommandSender sender) {
        String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
        String text;
        switch (version) {
            case "v1_16_R1":
            case "v1_16_R2":
            case "v1_16_R3":
            case "v1_17_R1":
            case "v1_17_R2":
                text =
                        "§7§m(━━━<<━━━━━━━━§l[§r #007fff§lT#008afe§lH#0095fe§lI#009ffd§lR#00aafd§lS#00b5fc§lT#17bdf8 #2ec5f3§lB#44ccef§lA#5bd4ea§lR §7§l§m]━━━━━━━━>>━━━)\n" +
                                "&r\n " +
                                "§f#007fff§lC#0088ff§lO#0091fe§lM#009afe§lM#00a3fd§lA#00acfd§lN#00b5fc§lD#0fbaf9§lS #2ec4f3§lL#3dcaf0§lI#4ccfed§lS#5bd4ea§lT\n" +
                                "&r\n " +
                                "&f/tb refresh [player]:&e Refresh yourself or other players.\n" +
                                "&f /tb set <value> [player]:&e Set thirst value on yourself or other players.\n" +
                                "&f /tb immune [player]:&e Immune to the bad effects of thirst.\n" +
                                "&f /tb disable [player]:&e Your or other player's thirst value will always be at 100%.\n" +
                                "&f /tb check [player]: &eCheck your thirst value or another player.\n" +
                                "&r\n " +
                                "&f&7* Remove the [player] argument if you want to apply the command to yourself\n" +
                                "&r\n " +
                                "§f#007fff§lA#0091fe&lU#00a3fd&lT#00b5fc&lH#2ec5f3&lO#5bd4ea&lR :&r&f [ Vanderis - Dev ]\n" +
                                "&f #007fff&lV#0091fe&lE#00a3fd&lR#00b5fc&lS#1ebff6&lI#3dcaf0&lO#5bd4ea&lN :&f {version}" +
                                "&r\n &r" +
                                "&7&m(━━━<<━━━━━━━━&l[&r #007fff&lT#008afe&lH#0095fe&lI#009ffd&lR#00aafd&lS#00b5fc&lT#17bdf8 #2ec5f3&lB#44ccef&lA#5bd4ea&lR &7&l&m]━━━━━━━━>>━━━)";
                sender.sendMessage(StorageMethod.formatToHexColor(text).replace("{version}", Bukkit.getServer().getPluginManager().getPlugin("ThirstBar").getDescription().getVersion()));
                break;
            default:
                text =
                        "&7&m(━━━<<━━━━━━━━&l[&r &b&lTHIRST BAR &7&l&m]━━━━━━━━>>━━━)\n" +
                                "&r\n " +
                                "&f&b&lCOMMANDS LIST\n" +
                                "&r\n " +
                                "&f/tb refresh <player>:&e Refresh yourself or other players.\n" +
                                "&f /tb set <value> [player]:&e Set thirst value on yourself or other players.\n" +
                                "&f /tb immune [player]:&e Immune to the bad effects of thirst.\n" +
                                "&f /tb disable [player]:&e Your or other player's thirst value will always be at 100%.\n" +
                                "&f /tb check [player]: &eCheck your thirst value or another player.\n" +
                                "&r\n " +
                                "&f&7*Remove the [player] argument if you want to apply the command to yourself\n" +
                                "&r\n " +
                                "&f&b&lAUTHOR :&r&f [ Vanderis - Dev ]\n" +
                                "&f &b&lVERSION :&f {version}" +
                                "&r\n &r" +
                                "&7&m(━━━<<━━━━━━━━&l[&r &b&lTHIRST BAR &7&l&m]━━━━━━━━>>━━━)";
                sender.sendMessage(text.replace('&', '§').replace("{version}", Bukkit.getServer().getPluginManager().getPlugin("ThirstBar").getDescription().getVersion()));
        }
    }

    public static void commandReloadMessage(CommandSender sender) {
        sender.sendMessage("§aThe plugin has been reloaded");
    }

    public static void refreshMessage(Player player) {
        String text = StorageMethod.plugin.getConfig().getString("RefreshMessage");
        if (text == null) text = "";
        text = text.replace("<player>", player.getName()).replace('&', '§');
        player.sendMessage(text);
    }

    public static void immuneMessage(Player player) {
        String text = StorageMethod.plugin.getConfig().getString("ImmuneMessage");
        if (text == null) text = "";
        text = text.replace("<player>", player.getName()).replace('&', '§');
        player.sendMessage(text);
    }

    public static void setMessage(Player player, double value) {
        String text = StorageMethod.plugin.getConfig().getString("SetThirstValueMessage");
        if (text == null) text = "";
        text = text.replace("<player>", player.getName())
                .replace("<value>", "" + Math.ceil(value * 100) / 100)
                .replace('&', '§');
        player.sendMessage(text);
    }

    public static void checkMessage(CommandSender sender, Player player, double value, double valueMax) {
        String text = StorageMethod.plugin.getConfig().getString("CheckThirstValueMessage");
        if (text == null) text = "";
        text = text.replace("<player>", player.getName())
                .replace("<value>", "" + Math.ceil(value * 100) / 100)
                .replace("<valueMax>", "" + Math.ceil(valueMax * 100) / 100)
                .replace('&', '§');
        sender.sendMessage(text);
    }

    public static void disableMessage(Player player) {
        String text = StorageMethod.plugin.getConfig().getString("DisableMessage");
        if (text == null) text = "";
        text = text.replace("<player>", player.getName()).replace('&', '§');
        player.sendMessage(text);
    }

    public static void errorUndefinedPlayerMessage(CommandSender sender) {
        sender.sendMessage(ChatColor.RED + "This player does not exist or is not online");
    }

    public static void errorCommandEmptyMessage(CommandSender sender) {
        sender.sendMessage(ChatColor.RED + "The command is wrong or missing, please /tb to see the full command..");
    }

    public static void errorNeedInputPlayerMessage(CommandSender sender) {
        sender.sendMessage(ChatColor.RED + "Only players can execute this command..");
    }

    public static void errorHaveNotPermMessage(CommandSender sender) {
        String text = StorageMethod.plugin.getConfig().getString("DontHavePermissionMessage");
        if (text == null) text = "";
        text = text.replace('&', '§');
        sender.sendMessage(text);
    }

    public static void errorFormatNumberMessage(CommandSender sender) {
        sender.sendMessage(ChatColor.RED + "The value you enter must be an integer.");
    }

    public static void errorListEffectMessage() {
        Bukkit.getConsoleSender().sendRawMessage(ChatColor.RED + "EffectThirsty file cannot be read, please check your configuration again");
    }

}
