package vanderis.team.thirstbar;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import vanderis.team.thirstbar.commands.CommandMain;
import vanderis.team.thirstbar.commands.TabCompleted;
import vanderis.team.thirstbar.listener.ListenerPlayerConsumer;
import vanderis.team.thirstbar.listener.ListenerPlayerJoinQuit;
import vanderis.team.thirstbar.manager.ListString;
import vanderis.team.thirstbar.manager.Method;
import vanderis.team.thirstbar.manager.PlaceHolder;
import vanderis.team.thirstbar.manager.thirst.ListThirstPlayer;

import java.nio.charset.StandardCharsets;

public final class ThirstBar extends JavaPlugin {

    @Override
    public void onEnable() {
        optionYml();
        registerListener();
        addThirstPlayerOnline();
        registerCommand();
        Method.setListThirst();
        Method.listFood = Method.plugin.getConfig().getStringList("FoodRegenThirst");
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PlaceHolder().register();
            optionYml();
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + "\n" +
                "████████╗██╗░░██╗██╗██████╗░░██████╗████████╗        ██████╗░░█████╗░██████╗░\n" +
                "╚══██╔══╝██║░░██║██║██╔══██╗██╔════╝╚══██╔══╝        ██╔══██╗██╔══██╗██╔══██╗\n" +
                "░░░██║░░░███████║██║██████╔╝╚█████╗░░░░██║░░░        ██████╦╝███████║██████╔╝\n" +
                "░░░██║░░░██╔══██║██║██╔══██╗░╚═══██╗░░░██║░░░        ██╔══██╗██╔══██║██╔══██╗\n" +
                "░░░██║░░░██║░░██║██║██║░░██║██████╔╝░░░██║░░░        ██████╦╝██║░░██║██║░░██║\n" +
                "░░░╚═╝░░░╚═╝░░╚═╝╚═╝╚═╝░░╚═╝╚═════╝░░░░╚═╝░░░        ╚═════╝░╚═╝░░╚═╝╚═╝░░╚═╝");
    }

    @Override
    public void onDisable() {
        removeThirstPlayerOnline();
    }

    private void optionYml() {
        saveDefaultConfig();

        Method.fileThirstEffect.createFileYAML();
        String thirstYml = "ThirstStage:\n" +
                "  stage1:\n" +
                "    #You can name this stage whatever you want.\n" +
                "    Value: '26:50'\n" +
                "    # if thirst value is between 26 and 50\n" +
                "    # then the below effects will be available\n" +
                "    Effects:\n" +
                "      - SLOW:1\n" +
                "    # You can find more effects here:\n" +
                "    # https://www.digminecraft.com/lists/effect_list_pc.php\n" +
                "  stage2:\n" +
                "    Value: '1:25'\n" +
                "    BossBarColor: RED\n" +
                "    Effects:\n" +
                "      - SLOW:2\n" +
                "  # you can create more stages if you want\n";
        Method.fileThirstEffect.saveCommentYaml(thirstYml.getBytes(StandardCharsets.UTF_8));
    }

    private void registerListener() {
        Bukkit.getPluginManager().registerEvents(new ListenerPlayerJoinQuit(), this);
        Bukkit.getPluginManager().registerEvents(new ListenerPlayerConsumer(), this);
    }

    private void registerCommand() {
        for (String command : ListString.listCommandMain) {
            PluginCommand pluginCommand = getCommand(command);
            if (pluginCommand == null) continue;
            pluginCommand.setExecutor(new CommandMain());
            pluginCommand.setTabCompleter(new TabCompleted());
        }
    }

    private void addThirstPlayerOnline() {
        for (Player player : Bukkit.getServer().getOnlinePlayers())
            ListThirstPlayer.addThirstPlayer(player);
    }

    private void removeThirstPlayerOnline() {
        for (Player player : Bukkit.getServer().getOnlinePlayers())
            ListThirstPlayer.removeThirstPlayer(player);
    }
}
