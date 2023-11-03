package gg.quartzdev.qtest.listeners;

import org.bukkit.*;
import org.bukkit.entity.Display;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Transformation;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestListener implements Listener {

    Plugin plugin;
    NamespacedKey key;
    UUID selectionID;
    UUID corner1ID;
    UUID corner2ID;

    Location corner1;
    Location corner2;
    Material displayMaterial = Material.WHITE_CONCRETE;
    double cornerOffset = .1;
    double selectionOffset = .02;

    public TestListener(Plugin plugin){
        this.plugin = plugin;
        this.key = new NamespacedKey(plugin, "qselection-key");
    }

    @EventHandler
    public void onTestListen(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(player.getInventory().getItemInMainHand().getType() != Material.STICK) {
            if (player.isSneaking())
                this.clearSelections();
            return;
        }
        event.setCancelled(true);
        if(event.getClickedBlock() == null) return;
        Location loc = event.getClickedBlock().getLocation();
        World world = loc.getWorld();

//        Left click = corner1
        if(event.getAction().isLeftClick()){
            if(corner1ID != null)
                removeHighlight(corner1ID, loc.getWorld());
            this.corner1 = loc;
//            this.corner1ID = this.highlightBlock(player, loc, Color.GREEN);
        }

//        Right click = corner2
        if(event.getAction().isRightClick()){
            if(corner2ID != null)
                removeHighlight(corner2ID, loc.getWorld());
            this.corner2 = loc;
//            this.corner2ID = this.highlightBlock(player, loc, Color.RED);
        }

        if(this.corner1 == null || this.corner2 == null) return;
        if(this.corner1.getWorld() != this.corner2.getWorld()) return;

        this.removeDisplays();
        this.getCorners(player, this.corner1, this.corner2);

    }

    public Location getCenterLocation(Location corner1, Location corner2){
        World world = corner1.getWorld();
        double centerX = (double) (corner1.getBlockX() + corner2.getBlockX()) /2;
        double centerY = (double) (corner1.getBlockY() + corner2.getBlockY()) /2;
        double centerZ = (double) (corner1.getBlockZ() + corner2.getBlockZ()) /2;
        return new Location(world, centerX, centerY, centerZ);
    }

    public List<Location> getCorners(Player player, Location pos1, Location pos2){
        World world = pos1.getWorld();
        List<Location> corners = new ArrayList<>();

        double pos1x = pos1.getX();
        double pos2x = pos2.getX();
        if(pos1x >= pos2x) pos1x = pos1x + 1;
        else pos2x = pos2x + 1;

        double pos1y = pos1.getY();
        double pos2y = pos2.getY();
        if(pos1y >= pos2y) pos1y = pos1y + 1;
        else pos2y = pos2y + 1;

        double pos1z = pos1.getZ();
        double pos2z = pos2.getZ();
        if(pos1z >= pos2z) pos1z = pos1z + 1;
        else pos2z = pos2z + 1;



        Location corner1 = new Location(world, pos1x, pos1y, pos1z);
        Location corner2 = new Location(world, pos2x, pos1y, pos1z);
        Location corner3 = new Location(world, pos1x, pos1y, pos2z);
        Location corner4 = new Location(world, pos2x, pos1y, pos2z);
        Location corner5 = new Location(world, pos1x, pos2y, pos1z);
        Location corner6 = new Location(world, pos2x, pos2y, pos1z);
        Location corner7 = new Location(world, pos1x, pos2y, pos2z);
        Location corner8 = new Location(world, pos2x, pos2y, pos2z);

//        c1 - c2
        this.highlightSelection(player, corner1, corner2, Color.WHITE);
//        c1 - c3
        this.highlightSelection(player, corner1, corner3, Color.WHITE);
//        c3 - c4
        this.highlightSelection(player, corner3, corner4, Color.WHITE);
//        c2 - c4
        this.highlightSelection(player, corner2, corner4, Color.WHITE);

//        c5 - c6
        this.highlightSelection(player, corner5, corner6, Color.WHITE);
//        c5 - c7
        this.highlightSelection(player, corner5, corner7, Color.WHITE);
//        c7 - c8
        this.highlightSelection(player, corner7, corner8, Color.WHITE);
//        c6 - c8
        this.highlightSelection(player, corner6, corner8, Color.WHITE);

//        c1 - c5
        this.highlightSelection(player, corner1, corner5, Color.WHITE);
//        c2 - c6
        this.highlightSelection(player, corner2, corner6, Color.WHITE);
//        c3 - c7
        this.highlightSelection(player, corner3, corner7, Color.WHITE);
//        c4 - c8
        this.highlightSelection(player, corner4, corner8, Color.WHITE);





        corners.add(pos1);
        corners.add(corner2);
        corners.add(corner3);
        corners.add(corner4);
        corners.add(corner5);
        corners.add(corner6);
        corners.add(corner7);
        corners.add(pos2);
        return corners;
    }

    public UUID highlightSelection(Player player, Location corner1, Location corner2, Color color){
        World world = corner1.getWorld();
        if(selectionID != null) removeHighlight(selectionID, corner2.getWorld());

        Location centerLoc = this.getCenterLocation(corner1, corner2);
        ItemDisplay itemDisplay = summonDisplay(centerLoc, Color.PURPLE);
        this.resizeDisplay(itemDisplay, corner1, corner2, selectionOffset, +.01);
        player.showEntity(plugin, itemDisplay);

        return itemDisplay.getUniqueId();

    }

    public UUID highlightBlock(Player player, Location loc, Color color){
        ItemDisplay itemDisplay = summonDisplay(this.getCenterLocation(loc, loc), color);
        this.brandDisplay(itemDisplay, "block");
        this.resizeDisplay(itemDisplay, loc, loc, cornerOffset, 1);
        player.showEntity(plugin, itemDisplay);
        return itemDisplay.getUniqueId();

    }

    public void resizeDisplay(ItemDisplay itemDisplay, Location loc1, Location loc2, double offset, double size) {

        double length =  Math.abs(offset + loc1.getBlockX()-loc2.getBlockX());
        double width = Math.abs(offset + loc1.getBlockZ()-loc2.getBlockZ());
        double height = Math.abs(offset + loc1.getBlockY()-loc2.getBlockY());

        Transformation transformation = itemDisplay.getTransformation();
        transformation.getScale().set(length+size, height+size, width+size);
        itemDisplay.setTransformation(transformation);
    }

    public ItemDisplay summonDisplay(Location loc, Color color){
        ItemDisplay itemDisplay = loc.getWorld().spawn(loc, ItemDisplay.class);
        itemDisplay.setItemStack(new ItemStack(displayMaterial));
//        Sets the display to glow
        itemDisplay.setGlowing(true);
        itemDisplay.setGlowColorOverride(color);
//        Sets brightness to 0 to prevent different shades of color when selection intersects with blocks and air
        itemDisplay.setBrightness(new Display.Brightness(15,15));
//        Hides the display for everyone
        itemDisplay.setVisibleByDefault(false);
        this.brandDisplay(itemDisplay, "selection");
        return itemDisplay;
    }

    public void removeHighlight(@NotNull UUID id, World world){
        Entity highlightEntity = world.getEntity(id);
        if(highlightEntity == null) return;
        highlightEntity.remove();
    }

    public void brandDisplay(ItemDisplay itemDisplay, String type){
        PersistentDataContainer container = itemDisplay.getPersistentDataContainer();
        container.set(key, PersistentDataType.STRING, type);
    }

    public void clearSelections(){
        removeDisplays();
        selectionID = null;
        corner1ID = null;
        corner2ID = null;
        corner1 = null;
        corner2 = null;
    }

    public void removeDisplays(){
        List<World> worlds = Bukkit.getServer().getWorlds();
        for(World w : worlds){
            for(Entity e : w.getEntities()){
                PersistentDataContainer pdc = e.getPersistentDataContainer();
                if(pdc.has(key, PersistentDataType.STRING)){
                    if(pdc.get(key, PersistentDataType.STRING).equals("selection"))
                        e.remove();
                };
            }
        }
    }

}
