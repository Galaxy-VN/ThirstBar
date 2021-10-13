package vanderis.team.thirstbar.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import vanderis.team.thirstbar.manager.thirst.PlayersThirstList;
import vanderis.team.thirstbar.manager.thirst.PlayersThirst;

public class PlayerJoinQuitEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        PlayersThirstList.addThirstPlayer(player);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        Player player = e.getPlayer();
        PlayersThirstList.removeThirstPlayer(player);
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e){
        Player player = e.getPlayer();
        PlayersThirst thirstPlayer = PlayersThirstList.getThirstPlayer(player);
        thirstPlayer.setThirstValue(thirstPlayer.getThirstMax());
    }

}
