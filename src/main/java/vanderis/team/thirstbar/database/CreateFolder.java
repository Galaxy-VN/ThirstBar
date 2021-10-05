package vanderis.team.thirstbar.database;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import vanderis.team.thirstbar.ThirstBar;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@SuppressWarnings("unused")
public class CreateFolder {

    public Plugin plugin = ThirstBar.getPlugin(ThirstBar.class);

    public static File folder;

    private final String folderName;
    private String linkFolder = "";

    public CreateFolder(String folderName) {
        this.folderName = folderName;
    }

    public CreateFolder(String folderName, String linkFolder) {
        this.folderName = folderName;
        this.linkFolder = linkFolder;
    }

    public void createFolder() {
        folder = (linkFolder == null) ?
                new File(plugin.getDataFolder()+"/"+folderName):
                new File(plugin.getDataFolder()+"/"+linkFolder+"/"+folderName);
        if (!(folder.exists())) {
            if ((linkFolder == null)) {
                if(!folder.mkdir()) {
                    Bukkit.getServer().getConsoleSender().sendMessage("Create folder "+folderName);
                }
            } else {
                if(!folder.mkdirs()) {
                    Bukkit.getServer().getConsoleSender().sendMessage("Create folder "+folderName);
                }
            }
        }
    }

    public File[] getAllFile() {
        return folder.listFiles();
    }

    public void saveFolder() {
        try {
            for (File f : Objects.requireNonNull(folder.listFiles())) {
                FileConfiguration fc = YamlConfiguration.loadConfiguration(f);
                fc.save(f);
            }
        } catch (IOException e) {
            Bukkit.getServer().getConsoleSender().sendMessage("§4Can't save all file ");
        }
    }

}
