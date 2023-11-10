package gg.quartzdev.qselectionui;

import gg.quartzdev.qselectionui.listeners.PlayerJoinListener;
import gg.quartzdev.qselectionui.listeners.SelectionListener;
import gg.quartzdev.qselectionui.selection.SelectionManager;
import org.bstats.bukkit.Metrics;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public final class qSelectionUI extends JavaPlugin {

    int BSTATS_PLUGIN_ID = 20229;
    NamespacedKey key;
    SelectionManager sm;

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.setupMetrics(BSTATS_PLUGIN_ID);
        this.key = new NamespacedKey(this, "qSUI");
        this.setupSelectionManager();
        this.setupSelectionHandler();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void setupSelectionManager(){
        this.getLogger().info("Setting up SelectionManager");
        this.sm = new SelectionManager(this);
    }
    public void setupSelectionHandler(){
        this.getLogger().info("Registering SelectionListener");
        this.getServer().getPluginManager().registerEvents(new SelectionListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
    }

    public void setupMetrics(int pluginId){
        this.getLogger().info("Enabling bStats Metrics...");
        Metrics metrics = new Metrics(this, pluginId);
//        TODO: Add custom metrics
    }

    public SelectionManager getSelectionManager(){
        return sm;
    }

}
