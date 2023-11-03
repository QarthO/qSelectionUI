package gg.quartzdev.qselectionui.Selection;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class Selection {

    Set<Player> viewers;
    Corner primary;
    Corner secondary;
    Set<Edge> frame;
    Set<Edge> extra;
    Color color;

    public Selection(Location pos1, Location pos2, Set<Player> players, Color color){
//        Set the corners
        primary = new Corner(pos1, players, color);
        secondary = new Corner(pos2, players, color);
//        Set the color
        this.color = Color.WHITE;
//        Add the viewers
        this.viewers = new HashSet<>();
        viewers.addAll(players);
//        Create the selection display
    }

    public Selection(Location pos1, Set<Player> players){
        this.viewers = new HashSet<>();
        viewers.addAll(players);
    }



    public void setPrimary(Location pos){
        this.primary.changePos(pos);
    }

    public void setSecondary(Location pos){
        this.secondary.changePos(pos);
    }

    public void highlightBlock(){

    }
}
