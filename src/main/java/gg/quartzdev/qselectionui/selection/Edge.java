package gg.quartzdev.qselectionui.selection;

import gg.quartzdev.qselectionui.DisplayManager;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ItemDisplay;

public class Edge {

    ItemDisplay display;
    Location center;
    World world;
    Material displayMaterial = Material.LIGHT_GRAY_STAINED_GLASS;
    Color color = Color.WHITE;

    public Edge(Location pos1, Location pos2){
        this.center = this.getCenter(pos1, pos2);
        this.world = pos1.getWorld();
        display = DisplayManager.summonDisplay(this.center, displayMaterial, color);
    }

    public Edge(Location pos1, Location pos2, Color color){
//        this = new Edge(pos1, pos2);
    }




    private Location getCenter(Location pos1, Location pos2){
        World world = pos1.getWorld();
        double centerX = (double) (pos1.getBlockX() + pos2.getBlockX()) /2;
        double centerY = (double) (pos1.getBlockY() + pos2.getBlockY()) /2;
        double centerZ = (double) (pos1.getBlockZ() + pos2.getBlockZ()) /2;
        return new Location(world, centerX, centerY, centerZ);
    }




}
