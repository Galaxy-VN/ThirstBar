package vanderis.team.thirstbar.database;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import vanderis.team.thirstbar.ThirstBar;

import java.io.*;
import java.util.Objects;

@SuppressWarnings("unused")
public class CreateFileYaml{

    public Plugin plugin = ThirstBar.getPlugin(ThirstBar.class);

    public FileConfiguration fc;
    public File f;

    private final String nameFileYAML;
    private String linkFolder;

    public CreateFileYaml(String nameFileYAML){
        this.nameFileYAML = nameFileYAML;
    }

    public CreateFileYaml(String nameFileYAML, String linkFolder){
        this.nameFileYAML = nameFileYAML;
        this.linkFolder = linkFolder;
    }

    public void createFileYAML(){
        if(f == null || !f.exists()) {
            f = (linkFolder == null) ?
                    new File(plugin.getDataFolder() + "/" + nameFileYAML + ".yml") :
                    new File(plugin.getDataFolder() + "/" + linkFolder + "/" + nameFileYAML + ".yml");
            fc = YamlConfiguration.loadConfiguration(f);
        }
    }

    public void saveCommentYaml(byte[] bytes){
        if(f.length() == 0) {
            try {
                OutputStream out = new FileOutputStream(f);
                out.write(bytes);
                out.close();
            } catch (IOException ignored){

            }
        }
        reloadFileYAML();
    }

    public void copyDefaultYAML() {
        InputStream is = (linkFolder == null) ?
                plugin.getResource(nameFileYAML+".yml"):
                plugin.getResource(linkFolder+"/"+nameFileYAML+".yml");
        f = (linkFolder == null) ?
                new File(plugin.getDataFolder()+"/"+nameFileYAML+".yml"):
                new File(plugin.getDataFolder()+"/"+linkFolder+"/"+nameFileYAML+".yml");
        if (!f.exists()) {
            try {
                OutputStream out = new FileOutputStream(f);
                int length;
                byte[] buf = new byte[1024];
                while ((length = Objects.requireNonNull(is).read(buf)) > 0) {
                    out.write(buf, 0, length);
                }
                out.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        fc = YamlConfiguration.loadConfiguration(f);
    }

    public FileConfiguration getFileYAML() {
        return fc;
    }

    public void saveFileYAML() {
        try {
            fc.save(f);
        } catch (IOException e) {
            Bukkit.getServer().getConsoleSender().sendMessage("§4Can't save file "+nameFileYAML);
        }
    }

    public void reloadFileYAML() {
        fc = YamlConfiguration.loadConfiguration(f);
    }

}
