package gg.quartzdev.qselectionui.selection;

import gg.quartzdev.qselectionui.qSelectionUI;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class SelectionManager {

    qSelectionUI plugin;
    HashMap<Player, Selection> selections;

    public SelectionManager(qSelectionUI plugin){
        this.plugin = plugin;
        this.selections = new HashMap<>();
    }

    public Selection get(Player player){
        Selection selection = selections.get(player);
        if(selection == null){
            selection = new Selection(player, this.plugin);
            selections.put(player,selection);
        }
        return selection;
    }
}