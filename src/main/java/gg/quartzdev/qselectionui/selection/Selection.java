package gg.quartzdev.qselectionui.selection;

import gg.quartzdev.qselectionui.qSelectionUI;
import gg.quartzdev.qselectionui.util.DisplayUtil;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Selection {
    qSelectionUI plugin;
    Player owner;
    Set<Player> viewers;
    Corner primary;
    Corner secondary;
    World world;
    Set<Edge> frame;
    Set<Edge> extra;
    Color color;

    public Selection(Player player, qSelectionUI plugin){
        this.plugin = plugin;
        this.owner = player;
        this.world = player.getWorld();
        this.primary = new Corner(player);
        this.secondary = new Corner(player);
        this.viewers = new HashSet<>();
    }

    public void setPrimary(Location pos){
        World posWorld = pos.getWorld();
        this.primary.changePos(pos);
        if(this.world == posWorld){
            this.visualize();
        } else {
            this.world = posWorld;
        }
    }

    public void setSecondary(Location pos){
        World posWorld = pos.getWorld();
        this.secondary.changePos(pos);
        if(this.world == posWorld){
            this.visualize();
        } else {
            this.world = posWorld;
        }
    }

    public void visualize(){

        if(this.primary.getPos() == null || this.secondary.getPos() == null)
            return;

        this.frame = this.calculateEdges();
        this.showDisplay();


    }

    @SuppressWarnings("UnstableApiUsage")
    public void showDisplay(){
        if(this.frame == null) {
            return;
        }
        for(Edge edge: this.frame){
            this.owner.showEntity(this.plugin, edge.getDisplay());
        }
    }
    public Set<Edge> calculateEdges(){

        Set<Edge> edges = new HashSet<>();
        List<Corner> corners = this.calculateSelectionCorners();

        int[][] edgeMatrix = new int[][]{
                {1,2}, {1,3}, {2,4}, {3,4},
                {5,6}, {5,7}, {6,8}, {7,8},
                {1,5}, {2,6}, {4,8}, {3,7}
        };

        for(int[] edgeCoords : edgeMatrix){
            Edge edge = new Edge(corners.get(edgeCoords[0]-1).getPos(), corners.get(edgeCoords[1]-1).getPos());
            ItemDisplay display = edge.getDisplay();
            DisplayUtil.resizeDisplay(display, corners.get(edgeCoords[0]-1).getPos(), corners.get(edgeCoords[1]-1).getPos(), .02, .01);
            edges.add(edge);
        }
        return edges;
    }

    public List<Corner> calculateSelectionCorners(){
        List<Corner> corners = new ArrayList<>();

//        X
        double pos1x = this.primary.getPos().getX();
        double pos2x = this.secondary.getPos().getX();
        if(pos1x >= pos2x) pos1x = pos1x + 1;
        else pos2x = pos2x + 1;

//        Y
        double pos1y = this.primary.getPos().getY();
        double pos2y = this.secondary.getPos().getY();
        if(pos1y >= pos2y) pos1y = pos1y + 1;
        else pos2y = pos2y + 1;

//        Z
        double pos1z = this.primary.getPos().getZ();
        double pos2z = this.secondary.getPos().getZ();
        if(pos1z >= pos2z) pos1z = pos1z + 1;
        else pos2z = pos2z + 1;

        double[][] cornerMatrix = new double[][]{
                {pos1x, pos1y, pos1z},
                {pos2x, pos1y, pos1z},
                {pos1x, pos1y, pos2z},
                {pos2x, pos1y, pos2z},
                {pos1x, pos2y, pos1z},
                {pos2x, pos2y, pos1z},
                {pos1x, pos2y, pos2z},
                {pos2x, pos2y, pos2z},
        };

//        Corners
//        https://raw.githubusercontent.com/QarthO/qSelectionUI/main/qSelectionUI%20infographic.png
//          Image Note: Orientation might vary - primary (corner1)  won't always be the smallest x,y,z

        for(double[] cornerCoord : cornerMatrix){
            corners.add(new Corner(world, cornerCoord[0], cornerCoord[1], cornerCoord[2], this.owner));
        }

        return corners;

    }

    public void clear(){
        if(this.frame == null) return;
        for(Edge edge : this.frame){
            edge.delete();
        }
    }

    public void reset(){
        this.primary.clear();
        this.secondary.clear();
        this.frame = null;
    }
}