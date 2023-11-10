package gg.quartzdev.qselectionui.selection;

import gg.quartzdev.qselectionui.qSelectionUI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class SelectionManager {

    qSelectionUI plugin;
    HashMap<UUID, Selection> selections;

    public SelectionManager(qSelectionUI plugin){
        this.plugin = plugin;
        this.selections = new HashMap<>();
    }

    public Selection get(Player player){
        Bukkit.getLogger().info("" + selections.size());
        Selection selection = selections.get(player.getUniqueId());
        if(selection == null){
            Bukkit.getLogger().info("creating new" + selections.size());
            selection = new Selection(player, this.plugin);
            selections.put(player.getUniqueId(),selection);
        }
        selection.owner = player;
        return selection;
    }
}