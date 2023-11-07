package gg.quartzdev.qselectionui;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Display;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;

public class DisplayManager {

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
//        this.brandDisplay(itemDisplay, "selection");
        return itemDisplay;
    }
}
