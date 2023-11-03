package gg.quartzdev.qselectionapi.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

public class SelectionListener implements Listener {

    Plugin plugin;
    Material selectionWand = Material.GOLDEN_HOE;

    Location pos1;
    Location pos2;

    public SelectionListener(Plugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onSelection(PlayerInteractEvent event){
        Player player = event.getPlayer();

//        return if not using selection wand
        if(player.getInventory().getItemInMainHand().getType() != selectionWand) return;

        Block clickedBlock = event.getClickedBlock();
        if(clickedBlock == null) return;

        event.setCancelled(true);

        Location clickedBlockLoc = clickedBlock.getLocation();
        World world = clickedBlockLoc.getWorld();

//        Left Click

        if(event.getAction().isLeftClick()){
            pos1 = clickedBlockLoc;
        }

        if(event.getAction().isRightClick()){
            pos2 = clickedBlockLoc;
        }

        if(pos1 == null || pos2 == null) return;
        if(pos1.getWorld() != pos2.getWorld()) return;

        setDisplay(pos1, pos2);
    }
}
