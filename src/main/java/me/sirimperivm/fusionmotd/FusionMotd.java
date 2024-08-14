package me.sirimperivm.fusionmotd;

import me.sirimperivm.fusionmotd.utils.colors.Colors;
import me.sirimperivm.fusionmotd.utils.others.Logger;
import net.md_5.bungee.api.plugin.Plugin;

@SuppressWarnings("all")
public final class FusionMotd extends Plugin {

    private FusionMotd plugin;
    private Colors colors;
    private Logger log;

    @Override
    public void onEnable() {
        plugin = this;
        colors = new Colors(plugin);
        log = new Logger(plugin, "FusionMotd");

        log.success("Plugin attivato correttamente.");
    }

    @Override
    public void onDisable() {
        log.success("Plugin disattivato correttamente.");
    }

    public FusionMotd getPlugin() {
        return plugin;
    }

    public Colors getColors() {
        return colors;
    }

    public Logger getLog() {
        return log;
    }
}
