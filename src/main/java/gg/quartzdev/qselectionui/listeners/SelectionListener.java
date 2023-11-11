package gg.quartzdev.qselectionui.listeners;

import gg.quartzdev.qselectionui.qSelectionUI;
import gg.quartzdev.qselectionui.selection.Selection;
import gg.quartzdev.qselectionui.util.TitleUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class SelectionListener implements Listener {

    qSelectionUI plugin;
    Material selectionWand = Material.GOLDEN_HOE;

    public SelectionListener(qSelectionUI plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onSelection(PlayerInteractEvent event) {
//        Ignore off-hand interaction event
        if (event.getHand() != EquipmentSlot.HAND) return;

        Player player = event.getPlayer();
//        return if not using selection wand
        if (player.getInventory().getItemInMainHand().getType() != selectionWand) return;

//        get the block
        Block clickedBlock = event.getClickedBlock();
        if (clickedBlock == null) return;

//        cancel the event
        event.setCancelled(true);

//        get the location of the clicked block
        Location clickedBlockLoc = clickedBlock.getLocation();

//        get the players selection
        Selection selection = plugin.getSelectionManager().get(player);

        selection.clear();

        if (player.isSneaking()){
            selection.reset();
            return;
        }
//        update the selection's corner
//              Left Click - Primary
        if(event.getAction().isLeftClick()){
            selection.setPrimary(clickedBlockLoc);
            MiniMessage mm = MiniMessage.miniMessage();
            String msg = "<aqua>Primary: (<light_purple>" + clickedBlockLoc.getBlockX() + "<aqua>,<light_purple>" + clickedBlockLoc.getBlockY() + "<aqua>,<light_purple>" + clickedBlockLoc.getBlockZ() + "<aqua>)";
            TitleUtil.show(player, msg, msg);
        }
//              Right Click - Secondary
        if(event.getAction().isRightClick()){
            selection.setSecondary(clickedBlockLoc);
            String msg = "<aqua>Secondary: (<light_purple>" + clickedBlockLoc.getBlockX() + "<aqua>,<light_purple>" + clickedBlockLoc.getBlockY() + "<aqua>,<light_purple>" + clickedBlockLoc.getBlockZ() + "<aqua>)";
            TitleUtil.show(player, msg, msg);
        }
    }
}
