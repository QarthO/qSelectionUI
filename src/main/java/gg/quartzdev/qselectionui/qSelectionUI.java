package gg.quartzdev.qselectionui;

import gg.quartzdev.qselectionui.listeners.SelectionListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class qSelectionUI extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        registerSelectionListener();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void registerSelectionListener(){
        this.getServer().getPluginManager().registerEvents(new SelectionListener(this), this);
    }

}
