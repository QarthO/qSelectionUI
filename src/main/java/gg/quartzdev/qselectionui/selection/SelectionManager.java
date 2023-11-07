package gg.quartzdev.qselectionui.selection;

import gg.quartzdev.qselectionui.qSelectionUI;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Set;

public class SelectionManager {

    qSelectionUI plugin;
    HashMap<Player, Selection> selections;

    public SelectionManager(qSelectionUI plugin){
        this.plugin = plugin;
    }

    public Selection getSelection(Player player){
        Selection selection = selections.get(player);
        if(selection == null){
            selection = new Selection(player);
            selections.put(player,selection);
        }
        return selection;
    }
}