package gg.quartzdev.qselectionui.Selection;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Display;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;

public class Edge {

    ItemDisplay display;
    Location center;
    World world;
    Material displayMaterial = Material.LIGHT_GRAY_STAINED_GLASS;
    Color color = Color.WHITE;

    public Edge(Location pos1, Location pos2){
        this.center = this.getCenter(pos1, pos2);
        this.world = pos1.getWorld();
        display = summonDisplay(this.center)
    }

    public Edge(Location pos1, Location pos2, Color color){
//        this = new Edge(pos1, pos2);
    }

    @SuppressWarnings("UnstableApiUsage")
    public ItemDisplay summonDisplay(Location pos){
        ItemDisplay itemDisplay = world.spawn(pos, ItemDisplay.class);
        itemDisplay.setItemStack(new ItemStack(displayMaterial));
//        Sets the display to glow
        itemDisplay.setGlowing(true);
        itemDisplay.setGlowColorOverride(color);
//        Sets brightness to 15 to prevent different shades of color when selection intersects with blocks and air
        itemDisplay.setBrightness(new Display.Brightness(15,15));
//        Hides the display for everyone
        itemDisplay.setVisibleByDefault(false);
//        this.brandDisplay(itemDisplay, "selection");
        return itemDisplay;
    }


    private Location getCenter(Location pos1, Location pos2){
        World world = pos1.getWorld();
        double centerX = (double) (pos1.getBlockX() + pos2.getBlockX()) /2;
        double centerY = (double) (pos1.getBlockY() + pos2.getBlockY()) /2;
        double centerZ = (double) (pos1.getBlockZ() + pos2.getBlockZ()) /2;
        return new Location(world, centerX, centerY, centerZ);
    }




}
