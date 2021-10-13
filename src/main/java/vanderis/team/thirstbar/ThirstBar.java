package vanderis.team.thirstbar;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import vanderis.team.thirstbar.commands.CommandMain;
import vanderis.team.thirstbar.commands.TabCompleted;
import vanderis.team.thirstbar.listener.PlayerConsumeEvent;
import vanderis.team.thirstbar.listener.PlayerJoinQuitEvent;
import vanderis.team.thirstbar.manager.StorageString;
import vanderis.team.thirstbar.manager.StorageMethod;
import vanderis.team.thirstbar.manager.Placeholder;
import vanderis.team.thirstbar.manager.thirst.PlayersThirstList;

import java.nio.charset.StandardCharsets;

public final class ThirstBar extends JavaPlugin {

    @Override
    public void onEnable() {
        optionYml();
        registerListener();
        addThirstPlayerOnline();
        registerCommand();
        StorageMethod.setListThirst();
        StorageMethod.listItemConsume = StorageMethod.plugin.getConfig().getStringList("itemConsumeRegenThirst");
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new Placeholder().register();
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

        StorageMethod.fileThirstEffect.createFileYAML();
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
        StorageMethod.fileThirstEffect.saveCommentYaml(thirstYml.getBytes(StandardCharsets.UTF_8));
    }

    private void registerListener() {
        Bukkit.getPluginManager().registerEvents(new PlayerJoinQuitEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerConsumeEvent(), this);
    }

    private void registerCommand() {
        for (String command : StorageString.commandMainList) {
            PluginCommand pluginCommand = getCommand(command);
            if (pluginCommand == null) continue;
            pluginCommand.setExecutor(new CommandMain());
            pluginCommand.setTabCompleter(new TabCompleted());
        }
    }

    private void addThirstPlayerOnline() {
        for (Player player : Bukkit.getServer().getOnlinePlayers())
            PlayersThirstList.addThirstPlayer(player);
    }

    private void removeThirstPlayerOnline() {
        for (Player player : Bukkit.getServer().getOnlinePlayers())
            PlayersThirstList.removeThirstPlayer(player);
    }
}
