package gg.quartzdev.qselectionui;

import gg.quartzdev.qselectionui.listeners.SelectionListener;
import gg.quartzdev.qselectionui.selection.SelectionManager;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public final class qSelectionUI extends JavaPlugin {

    int BSTATS_PLUGIN_ID = 20229;

    @Override
    public void onEnable() {
        // Plugin startup logic
        setupMetrics(BSTATS_PLUGIN_ID);
        registerSelectionHandler();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void registerSelectionHandler(){
        this.getLogger().info("Registering SelectionListener");
        try {
            this.getServer().getPluginManager().registerEvents(new SelectionListener(this, new SelectionManager(this)), this);
        } catch(Exception e){
            this.getLogger().severe("- Failed to register SelectionListener");
            this.getLogger().severe(e.getMessage());
        }
    }

    public void setupMetrics(int pluginId){
        this.getLogger().info("Enabling bStats Metrics...");
        Metrics metrics = new Metrics(this, pluginId);
//        TODO: Add custom metrics
    }

}
