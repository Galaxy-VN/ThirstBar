package vanderis.team.thirstbar.manager.thirst;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import vanderis.team.thirstbar.manager.Method;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@SuppressWarnings("unused")
public class ListThirstPlayer {

    private static final HashSet<ThirstPlayer> list = new HashSet<>();
    private static final HashSet<Player> listPlayer = new HashSet<>();

    private static final HashMap<String, Double> listThirstOut = new HashMap<>();
    public static HashMap<Player, String> mapKeyOfPlayer = new HashMap<>();

    public static void addThirstPlayer(Player player) {
        list.add(new ThirstPlayer(player));
        listPlayer.add(player);
        if (listThirstOut.get(player.getName()) != null) {
            getThirstPlayer(player).setThirstPoint(listThirstOut.get(player.getName()));
            listThirstOut.remove(player.getName());
        }
        repeatingDecreaseThirst(player);
    }

    public static void addThirstPlayer(Player player, double thirstMax, double thirstDecrease) {
        list.add(new ThirstPlayer(player, thirstMax, thirstDecrease));
        listPlayer.add(player);
        if (listThirstOut.get(player.getName()) != null) {
            getThirstPlayer(player).setThirstPoint(listThirstOut.get(player.getName()));
            listThirstOut.remove(player.getName());
        }
        repeatingDecreaseThirst(player);
    }

    public static void removeThirstPlayer(Player player) {
        ThirstPlayer thirstPlayer = getThirstPlayer(player);
        if (thirstPlayer == null) return;
        listThirstOut.put(player.getName(), thirstPlayer.getThirstPoint());
        list.remove(thirstPlayer);
        Bukkit.getScheduler().cancelTask(thirstPlayer.getIdRepeat());
        ListThirstPlayer.removeEffects(thirstPlayer, mapKeyOfPlayer.get(player));
        if (thirstPlayer.getBossBar() == null) return;
        thirstPlayer.getBossBar().removePlayer(player);
    }

    public static HashSet<ThirstPlayer> getListThirstPlayer() {
        return list;
    }

    public static HashSet<Player> getListPlayer() {
        return listPlayer;
    }

    public static ThirstPlayer getThirstPlayer(Player player) {
        return list.stream().filter(list -> list.getPlayer().equals(player)).findAny().orElse(null);
    }

    public static void repeatingDecreaseThirst(Player player) {
        long delayRepeat = Method.plugin.getConfig().getLong("Thirst.Time");
        ThirstPlayer thirstPlayer = getThirstPlayer(player);
        int num = Bukkit.getScheduler().scheduleSyncRepeatingTask(Method.plugin, () -> {
            calDamageThirsty(thirstPlayer);
            changeBossBarPlayer(thirstPlayer);
            setEffectThirst(thirstPlayer);
        }, 0, delayRepeat);
        thirstPlayer.setIdRepeat(num);
    }

    private static void calDamageThirsty(ThirstPlayer thirstPlayer) {
        Player player = thirstPlayer.getPlayer();
        double dmg = Method.plugin.getConfig().getDouble("DamagePerSecond");
        double thirstDecrease = thirstPlayer.getThirstDecrease();
        if (thirstDecrease == 0 || player.isDead()) return;
        double cal = thirstPlayer.getThirstPoint() - thirstDecrease;
        if (cal >= 0)
            thirstPlayer.setThirstPoint(cal);
        else {
            thirstPlayer.setThirstPoint(0);
            double calHP = Math.max(0, player.getHealth() - dmg);
            player.damage(0.0000000000001);
            if (calHP > 0) {
                player.setHealth(calHP);
            } else {
                player.setHealth(0);
            }
        }
    }

    public static void changeBossBarPlayer(ThirstPlayer thirstPlayer) {
        if (!Method.plugin.getConfig().getBoolean("BossBar.Enable")) return;
        Player player = thirstPlayer.getPlayer();
        String title = Method.plugin.getConfig().getString("BossBar.Title");
        if (title != null) title = title
                .replace("<thirstPoint>", String.valueOf(thirstPlayer.getThirstPoint()))
                .replace("<thirstMax>", String.valueOf(thirstPlayer.getThirstMax()))
                .replace("<thirstDecrease>", String.valueOf(thirstPlayer.getThirstDecrease()))
                .replace('&', '§');
        else title = "";

        HashSet<String> listBarColor = new HashSet<>(Arrays.asList("BLUE", "GREEN", "PINK", "PURPLE", "RED", "WHILE", "YELLOW"));
        String color = Method.plugin.getConfig().getString("BossBar.Color");
        if (color != null) color = color.toUpperCase();
        else color = "BLUE";
        if (!listBarColor.contains(color)) color = "BLUE";

        HashSet<String> listBarStyle = new HashSet<>(Arrays.asList("SOLID", "SEGMENTED_20", "SEGMENTED_12", "SEGMENTED_10", "SEGMENTED_6"));
        String style = Method.plugin.getConfig().getString("BossBar.Style");
        if (style != null) style = style.toUpperCase();
        else style = "SOLID";
        if (!listBarStyle.contains(style)) style = "SOLID";

        if (thirstPlayer.getThirstPoint() > thirstPlayer.getThirstMax())
            thirstPlayer.setThirstPoint(thirstPlayer.getThirstMax());

        setBossBar(thirstPlayer, player, title, color, style);
        replaceFood(thirstPlayer);
    }

    private static void setBossBar(ThirstPlayer thirstPlayer, Player player, String title, String color, String style) {
        BossBar bossBar = Bukkit.createBossBar(title, BarColor.valueOf(color), BarStyle.valueOf(style));
        if (player.getGameMode().equals(GameMode.CREATIVE) || thirstPlayer.isDisable()) {
            thirstPlayer.setThirstPoint(thirstPlayer.getThirstMax());
            bossBar.setProgress(1);
        } else {
            bossBar.setProgress(thirstPlayer.getThirstPoint() / thirstPlayer.getThirstMax());
        }

        if (thirstPlayer.getBossBar() == null) {
            bossBar.addPlayer(player);
            thirstPlayer.setBossBar(bossBar);
        } else {
            thirstPlayer.getBossBar().setProgress(bossBar.getProgress());
            thirstPlayer.getBossBar().setColor(bossBar.getColor());
            thirstPlayer.getBossBar().setStyle(bossBar.getStyle());
            thirstPlayer.getBossBar().setTitle(bossBar.getTitle());
        }
    }

    private static void replaceFood(ThirstPlayer thirstPlayer) {
        if (!Method.plugin.getConfig().getBoolean("ReplaceFood")) return;
        Player player = thirstPlayer.getPlayer();
        if (player.getGameMode().equals(GameMode.CREATIVE))
            player.setFoodLevel(player.getFoodLevel());
        else
            player.setFoodLevel((int) ((thirstPlayer.getThirstPoint() * 20) / thirstPlayer.getThirstMax()));
    }


    public static void setEffectThirst(ThirstPlayer thirstPlayer) {
        List<Double> listValue = Method.listValue;
        if(listValue == null) return;
        Player player = thirstPlayer.getPlayer();
        for(int i = 0; i < listValue.size(); i+=2){
            double valueStart = listValue.get(i);
            double valueEnd = listValue.get(i+1);
            if(thirstPlayer.getThirstPoint() < valueStart || thirstPlayer.getThirstPoint() > valueEnd) continue;
            String key = Method.mapKeyOfValue.get(valueStart+":"+valueEnd);
            if(mapKeyOfPlayer.get(player) != null)
                removeEffects(thirstPlayer, mapKeyOfPlayer.get(player));
            if(mapKeyOfPlayer.get(player) == null || !mapKeyOfPlayer.get(player).equals(key))
                addEffects(thirstPlayer, key);
            return;
        }
        if(mapKeyOfPlayer.get(player) != null)
            removeEffects(thirstPlayer, mapKeyOfPlayer.get(player));
    }

    private static void addEffects(ThirstPlayer thirstPlayer, String key){
        List<PotionEffect> potionEffects = Method.mapEffectOfKey.get(key);
        if(potionEffects == null || thirstPlayer.isImmune()) return;
        Player player = thirstPlayer.getPlayer();
        player.addPotionEffects(potionEffects);
        thirstPlayer.getBossBar().setColor(Method.mapBarColorOfKey.get(key));
        mapKeyOfPlayer.put(player, key);
    }
    private static void removeEffects(ThirstPlayer thirstPlayer, String key){
        List<PotionEffect> potionEffects = Method.mapEffectOfKey.get(key);
        if(potionEffects == null || thirstPlayer.isImmune()) return;
        Player player = thirstPlayer.getPlayer();
        for(PotionEffect potionEffect : potionEffects){
            PotionEffectType type = potionEffect.getType();
            player.removePotionEffect(type);
        }
        String color = Method.plugin.getConfig().getString("BossBar.Color");
        thirstPlayer.getBossBar().setColor(Method.mapBarColorOfKey.get(key));
        mapKeyOfPlayer.remove(player);
    }
}
