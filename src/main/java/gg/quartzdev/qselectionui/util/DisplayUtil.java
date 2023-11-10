package gg.quartzdev.qselectionui.util;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Display;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Transformation;

public class DisplayUtil {

    @SuppressWarnings("UnstableApiUsage")
    public static ItemDisplay summonDisplay(Location pos, Material displayMaterial, Color color){
        ItemDisplay itemDisplay = pos.getWorld().spawn(pos, ItemDisplay.class);
        itemDisplay.setItemStack(new ItemStack(displayMaterial));
//        Sets the display to glow
        itemDisplay.setGlowing(true);
        itemDisplay.setGlowColorOverride(color);
//        Sets brightness to 15 to prevent different shades of color when selection intersects with blocks and air
        itemDisplay.setBrightness(new Display.Brightness(15,15));
//        Hides the display for everyone
        itemDisplay.setVisibleByDefault(false);
        return itemDisplay;
    }

    public static void resizeDisplay(ItemDisplay itemDisplay, Location locCornerA, Location locCornerB, double offset, double size) {

        double length =  Math.abs(offset + locCornerA.getBlockX()-locCornerB.getBlockX());
        double width = Math.abs(offset + locCornerA.getBlockZ()-locCornerB.getBlockZ());
        double height = Math.abs(offset + locCornerA.getBlockY()-locCornerB.getBlockY());

        Transformation transformation = itemDisplay.getTransformation();
        transformation.getScale().set(length+size, height+size, width+size);
        itemDisplay.setTransformation(transformation);
    }

    public static void brandDisplay(ItemDisplay itemDisplay, NamespacedKey key, String type){
        PersistentDataContainer container = itemDisplay.getPersistentDataContainer();
        container.set(key, PersistentDataType.STRING, type);
    }
}
