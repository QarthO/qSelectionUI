package gg.quartzdev.qtest;

import gg.quartzdev.qtest.listeners.TestListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class qTest extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new TestListener(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
