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
        return "Vanderis";
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
            case "MaxValue":
                return String.valueOf(((float)((int) (thirstPlayer.getWaterMax()*100)))/100);
            case "CurrentValue":
                return String.valueOf(((float)((int) (thirstPlayer.getWaterPoint()*100)))/100);
            case "DescendingValue":
                return String.valueOf(((float)((int) (thirstPlayer.getWaterDecrease()*100)))/100);
            case "RegenValue":
                if(ListenerPlayerConsumer.listRegenWater.get(player) == null) return "0.0";
                return String.valueOf(((float)((int) (ListenerPlayerConsumer.listRegenWater.get(player)*100)))/100);
            case "IntMaxValue":
                return String.valueOf((int) thirstPlayer.getWaterMax());
            case "IntCurrentValue":
                return String.valueOf((int) thirstPlayer.getWaterPoint());
            case "IntDescendingValue":
                return String.valueOf((int) thirstPlayer.getWaterDecrease());
            case "IntRegenValue":
                if(ListenerPlayerConsumer.listRegenWater.get(player) == null) return "0";
                return String.valueOf(ListenerPlayerConsumer.listRegenWater.get(player).intValue());
            default:
                return null;
        }
    }
}
