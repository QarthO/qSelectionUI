package gg.quartzdev.qselectionui.listeners;

import gg.quartzdev.qselectionui.qSelectionUI;
import gg.quartzdev.qselectionui.selection.Selection;
import gg.quartzdev.qselectionui.selection.SelectionManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class SelectionListener implements Listener {

    qSelectionUI plugin;
    Material selectionWand = Material.GOLDEN_HOE;

    public SelectionListener(qSelectionUI plugin){
        this.plugin = plugin;
//        this.sm = sm;
    }

    @EventHandler
    public void onSelection(PlayerInteractEvent event){
        Player player = event.getPlayer();

//        return if not using selection wand
        if(player.getInventory().getItemInMainHand().getType() != selectionWand) return;

//        get the block
        Block clickedBlock = event.getClickedBlock();
        if(clickedBlock == null) return;

//        cancel the event
        event.setCancelled(true);

//        get the location of the clicked block
        Location clickedBlockLoc = clickedBlock.getLocation();

//        get the players selection
        Selection selection = plugin.getSelectionManager().getSelection(player);

//        update the selection's corner
//              Left Click - Primary
        if(event.getAction().isLeftClick()){
            selection.setPrimary(clickedBlockLoc);
        }
//              Right Click - Secondary
        if(event.getAction().isRightClick()){
            selection.setSecondary(clickedBlockLoc);
        }
    }
}
