package gg.quartzdev.qselectionui.Selection;

import io.papermc.paper.math.BlockPosition;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Corner extends Selection {

    Location pos;

    public Corner(Location pos, Set<Player> viewers, Color color){
        super(pos, pos, viewers, color);
    }

    public void changePos(Location pos){
        this.pos = pos;
    }

    public void changeColor(Color color){
        this.color = color;
    }

    public void addViewer(Player player, boolean canEdit){
        this.viewers.add(player);
    }

    public void removeViewer(Player player){
        this.viewers.remove(player);
    }

    public boolean canEdit(Player player){
//        return viewers.containsKey(player) && viewers.get(player);
        return false;
    }

    public Location getPos(){
        return pos;
    }
}