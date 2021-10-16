package vanderis.team.thirstbar.manager;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import vanderis.team.thirstbar.ThirstBar;
import vanderis.team.thirstbar.database.CreateFileYaml;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StorageMethod {

    public static Plugin plugin = ThirstBar.getPlugin(ThirstBar.class);
    public static CreateFileYaml fileThirstEffect = new CreateFileYaml("ThirstEffect");
    public static List<String> listItemConsume = plugin.getConfig().getStringList("ItemConsumeRegen");

    public static List<Double> listValue = new ArrayList<>();
    public static HashMap<String, String> mapKeyOfValue = new HashMap<>();
    public static HashMap<String, List<PotionEffect>> mapEffectOfKey = new HashMap<>();
    public static HashMap<String, BarColor> mapBarColorOfKey = new HashMap<>();
    private final static Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");

    public static String formatToHexColor(String text){
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()){
            String color = text.substring(matcher.start(), matcher.end());
            text = text.replace(color, ChatColor.of(color)+"");
            matcher = pattern.matcher(text);
        }
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    @SuppressWarnings("all")
    public static boolean checkConvertDouble(String text) {
        if (text == null) return false;
        try {
            Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("all")
    public static boolean checkConvertInteger(String text) {
        if (text == null) return false;
        try {
            Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static void setListThirst() {
        ConfigurationSection section = fileThirstEffect.getFileYAML().getConfigurationSection("ThirstStage");
        if (section == null) {
            listValue = null;
            return;
        }
        for (String nameKey : section.getKeys(false)) {
            String valueString = StorageMethod.fileThirstEffect.getFileYAML().getString("ThirstStage." + nameKey + ".Value");
            if (valueString == null || valueString.split(":").length != 2) {
                StorageString.errorListEffectMessage();
                listValue = null;
                return;
            }
            String valueStart = valueString.split(":")[0];
            String valueEnd = valueString.split(":")[1];
            if (!StorageMethod.checkConvertDouble(valueStart) || !StorageMethod.checkConvertDouble(valueEnd)) {
                StorageString.errorListEffectMessage();
                listValue = null;
                return;
            }
            double numStart = Double.parseDouble(valueStart);
            double numEnd = Double.parseDouble(valueEnd);
            if (numEnd > StorageMethod.plugin.getConfig().getDouble("Thirst.Max") || numStart < 0) {
                StorageString.errorListEffectMessage();
                listValue = null;
                return;
            }
            valueString = numStart+":"+numEnd;
            listValue.add(numStart);
            listValue.add(numEnd);
            mapKeyOfValue.put(valueString, nameKey);
            //-------------------------EFFECTS--------------------------------
            List<String> listNameEffects = StorageMethod.fileThirstEffect.getFileYAML().getStringList("ThirstStage." + nameKey + ".Effects");
            List<PotionEffect> listPotionEffect = new ArrayList<>();
            for (String nameEffect : listNameEffects) {
                if (nameEffect.split(":").length != 2) {
                    StorageString.errorListEffectMessage();
                    return;
                }
                String name = nameEffect.split(":")[0];
                PotionEffectType effectType = PotionEffectType.getByName(name.toUpperCase());
                if (effectType == null) {
                    StorageString.errorListEffectMessage();
                    return;
                }
                String powerString = nameEffect.split(":")[1];
                if (!StorageMethod.checkConvertInteger(powerString)) {
                    StorageString.errorListEffectMessage();
                    return;
                }
                int power = Integer.parseInt(powerString) - 1;
                PotionEffect potionEffect = new PotionEffect(effectType, 999999999, power);
                listPotionEffect.add(potionEffect);
            }
            mapEffectOfKey.put(nameKey, listPotionEffect);
            //-------------------------BAR-COLOR--------------------------------
            HashSet<String> listBarColor = new HashSet<>(Arrays.asList("BLUE", "GREEN", "PINK", "PURPLE", "RED", "WHILE", "YELLOW"));
            String color = StorageMethod.fileThirstEffect.getFileYAML().getString("ThirstStage." + nameKey + ".BossBarColor");
            if (color == null) {
                color = StorageMethod.plugin.getConfig().getString("BossBar.Color");
            } else {
                color = color.toUpperCase();
                if (!listBarColor.contains(color)) color = "BLUE";
            }
            mapBarColorOfKey.put(nameKey, BarColor.valueOf(color));
        }
        List<Double> listSorted = listValue;
        Collections.sort(listSorted);
        if(!listSorted.equals(listValue)){
            StorageString.errorListEffectMessage();
            listValue = null;
        }
    }

}
