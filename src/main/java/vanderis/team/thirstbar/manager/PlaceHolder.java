package vanderis.team.thirstbar.manager;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import vanderis.team.thirstbar.listener.ListenerPlayerConsumer;
import vanderis.team.thirstbar.manager.water.ListThirstPlayer;
import vanderis.team.thirstbar.manager.water.ThirstPlayer;

public class PlaceHolder extends PlaceholderExpansion {
    @Override
    public String getIdentifier() {
        return "ThirstBar";
    }

    @Override
    public String getAuthor() {
        return "WinterNight";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player player, String params) {
        if (player == null) return "";
        ThirstPlayer thirstPlayer = ListThirstPlayer.getWaterPlayer(player);
        if(thirstPlayer == null) return "";
        switch (params) {
            case "WaterMax":
                return String.valueOf(thirstPlayer.getWaterMax());
            case "WaterPoint":
                return String.valueOf(thirstPlayer.getWaterPoint());
            case "WaterDecrease":
                return String.valueOf(thirstPlayer.getWaterDecrease());
            case "WaterRegen":
                if(ListenerPlayerConsumer.listRegenWater.get(player) == null) return "0.0";
                return String.valueOf(ListenerPlayerConsumer.listRegenWater.get(player));
            case "WaterMaxInt":
                return String.valueOf((int) thirstPlayer.getWaterMax());
            case "WaterPointInt":
                return String.valueOf((int) thirstPlayer.getWaterPoint());
            case "WaterDecreaseInt":
                return String.valueOf((int) thirstPlayer.getWaterDecrease());
            case "WaterRegenInt":
                if(ListenerPlayerConsumer.listRegenWater.get(player) == null) return "0";
                return String.valueOf(ListenerPlayerConsumer.listRegenWater.get(player).intValue());
            case "NameFood":
                if(ListenerPlayerConsumer.listNameFood.get(player) == null) return "AIR";
                return ListenerPlayerConsumer.listNameFood.get(player);
            default:
                return null;
        }
    }
}
