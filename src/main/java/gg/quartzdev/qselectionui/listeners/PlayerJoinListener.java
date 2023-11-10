package gg.quartzdev.qselectionui.listeners;

import gg.quartzdev.qselectionui.qSelectionUI;
import gg.quartzdev.qselectionui.selection.SelectionManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.concurrent.TimeUnit;

public class PlayerJoinListener implements Listener {

    qSelectionUI plugin;

    public PlayerJoinListener(qSelectionUI plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Bukkit.getServer().getLogger().info("Player joined");
        Player player = event.getPlayer();
        SelectionManager sm = this.plugin.getSelectionManager();
        plugin.getServer().getScheduler().runTaskLater(plugin, runnable -> {
            plugin.getLogger().info("showing display async");
            sm.get(player).showDisplay();
        }, 1);
    }
}
