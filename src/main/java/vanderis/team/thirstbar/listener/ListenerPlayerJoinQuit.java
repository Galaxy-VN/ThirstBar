package vanderis.team.thirstbar.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import vanderis.team.thirstbar.manager.thirst.ListThirstPlayer;
import vanderis.team.thirstbar.manager.thirst.ThirstPlayer;

public class ListenerPlayerJoinQuit implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        ListThirstPlayer.addThirstPlayer(player);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        Player player = e.getPlayer();
        ListThirstPlayer.removeThirstPlayer(player);
    }

    @EventHandler
    public void onPlayerRevival(PlayerRespawnEvent e){
        Player player = e.getPlayer();
        ThirstPlayer thirstPlayer = ListThirstPlayer.getThirstPlayer(player);
        thirstPlayer.setThirstPoint(thirstPlayer.getThirstMax());
    }

}
