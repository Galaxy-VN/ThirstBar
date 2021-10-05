package vanderis.team.thirstbar.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import vanderis.team.thirstbar.manager.water.ListThirstPlayer;
import vanderis.team.thirstbar.manager.water.ThirstPlayer;

public class ListenerPlayerJoinQuit implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        ListThirstPlayer.addWaterPlayer(player);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        Player player = e.getPlayer();
        ListThirstPlayer.removeWaterPlayer(player);
    }

    @EventHandler
    public void onPlayerRevival(PlayerRespawnEvent e){
        Player player = e.getPlayer();
        ThirstPlayer thirstPlayer = ListThirstPlayer.getWaterPlayer(player);
        thirstPlayer.setWaterPoint(thirstPlayer.getWaterMax());
    }

}
