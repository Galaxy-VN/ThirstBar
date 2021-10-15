package vanderis.team.thirstbar.manager;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import vanderis.team.thirstbar.ThirstBar;
import vanderis.team.thirstbar.listener.PlayerConsumeEvent;
import vanderis.team.thirstbar.manager.thirst.PlayersThirstList;
import vanderis.team.thirstbar.manager.thirst.PlayersThirst;

public class Placeholder extends PlaceholderExpansion {
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
        return ThirstBar.getInstance().getDescription().getVersion();
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
        PlayersThirst thirstPlayer = PlayersThirstList.getThirstPlayer(player);
        if(thirstPlayer == null) return "";
        switch (params) {
            case "MaxValue":
                return String.valueOf(((float)((int) (thirstPlayer.getThirstMax()*100)))/100);
            case "CurrentValue":
                return String.valueOf(((float)((int) (thirstPlayer.getThirstValue()*100)))/100);
            case "DescendingValue":
                return String.valueOf(((float)((int) (thirstPlayer.getThirstDecrease()*100)))/100);
            case "RegenValue":
                if(PlayerConsumeEvent.listRegenThirst.get(player) == null) return "0.0";
                return String.valueOf(((float)((int) (PlayerConsumeEvent.listRegenThirst.get(player)*100)))/100);
            case "IntMaxValue":
                return String.valueOf((int) thirstPlayer.getThirstMax());
            case "IntCurrentValue":
                return String.valueOf((int) thirstPlayer.getThirstValue());
            case "IntDescendingValue":
                return String.valueOf((int) thirstPlayer.getThirstDecrease());
            case "IntRegenValue":
                if(PlayerConsumeEvent.listRegenThirst.get(player) == null) return "0";
                return String.valueOf(PlayerConsumeEvent.listRegenThirst.get(player).intValue());
            default:
                return null;
        }
    }
}
