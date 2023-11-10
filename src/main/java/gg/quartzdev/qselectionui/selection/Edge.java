package gg.quartzdev.qselectionui.selection;

import gg.quartzdev.qselectionui.util.DisplayUtil;
import org.bukkit.*;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;

public class Edge {

    ItemDisplay display;
    Location center;
    Material displayMaterial = Material.LIGHT_GRAY_STAINED_GLASS;
    Color color = Color.WHITE;

    public Edge(Location pos1, Location pos2){
        this.center = this.getCenter(pos1, pos2);
        this.setupEdgeDisplay();
    }
    private Location getCenter(Location pos1, Location pos2){
        World world = pos1.getWorld();
        double centerX = (double) (pos1.getBlockX() + pos2.getBlockX()) /2;
        double centerY = (double) (pos1.getBlockY() + pos2.getBlockY()) /2;
        double centerZ = (double) (pos1.getBlockZ() + pos2.getBlockZ()) /2;
        return new Location(world, centerX, centerY, centerZ);
    }

    private void setupEdgeDisplay(){
        this.display = DisplayUtil.summonDisplay(this.center, this.displayMaterial, this.color);
    }

    public void brand(NamespacedKey key){
        DisplayUtil.brandDisplay(this.display, key, "edge");
    }

    public void delete(){
        this.display.remove();
    }

    public ItemDisplay getDisplay(){
        return this.display;
    }




}
