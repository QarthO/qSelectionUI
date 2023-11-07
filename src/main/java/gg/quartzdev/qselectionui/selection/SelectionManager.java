package gg.quartzdev.qselectionui.selection;

import gg.quartzdev.qselectionui.qSelectionUI;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Set;

public class SelectionManager {

    qSelectionUI plugin;
    Set<Selection> selections;

    public SelectionManager(qSelectionUI plugin){
        this.plugin = plugin;
    }

    public Selection createSelection(Location pos1, Location pos2, Player player){

        Selection selection = new Selection(pos1, pos2, player, Color.WHITE);
        selection.highlightBlock();
        return selection;

    }


}
