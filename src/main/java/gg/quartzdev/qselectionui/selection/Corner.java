package gg.quartzdev.qselectionui.selection;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Set;

public class Corner {

    Location pos;
    Player player;
    Color color = Color.WHITE;


    public Corner(Location pos, Player player, Color color){
        this.pos = pos;
        this.player = player;
        this.color = color;
    }

    public Corner(Player player){
        this.player = player;
    }

    public Corner(World world, double x, double y, double z, Player player){
//        super(player);
        this.pos = new Location(world, x, y, z);
    }

    public void changePos(Location pos){
        this.pos = pos;
    }

    public void changeColor(Color color){
        this.color = color;
    }

//    public void addViewer(Player player, boolean canEdit){
//        this.viewers.add(player);
//    }
//
//    public void removeViewer(Player player){
//        this.viewers.remove(player);
//    }

    public boolean canEdit(Player player){
//        return viewers.containsKey(player) && viewers.get(player);
        return false;
    }

    public Location getPos(){
        return this.pos;
    }
}
