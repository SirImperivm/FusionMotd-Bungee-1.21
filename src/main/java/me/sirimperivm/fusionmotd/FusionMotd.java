package me.sirimperivm.fusionmotd;

import me.sirimperivm.fusionmotd.commands.MainCommand;
import me.sirimperivm.fusionmotd.utils.ConfigManager;
import me.sirimperivm.fusionmotd.utils.ModuleManager;
import me.sirimperivm.fusionmotd.utils.colors.Colors;
import me.sirimperivm.fusionmotd.utils.others.Errors;
import me.sirimperivm.fusionmotd.utils.others.Logger;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

@SuppressWarnings("all")
public final class FusionMotd extends Plugin {

    private FusionMotd plugin;
    private Colors colors;
    private Logger log;
    private ConfigManager configManager;
    private Errors errors;
    private ModuleManager modules;

    @Override
    public void onEnable() {
        plugin = this;
        colors = new Colors(plugin);
        log = new Logger(plugin, "FusionMotd");
        configManager = new ConfigManager(plugin);
        errors = new Errors(plugin);
        modules = new ModuleManager(plugin);

        ProxyServer.getInstance().getPluginManager().registerCommand(plugin, new MainCommand("motd", plugin));

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

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public Errors getErrors() {
        return errors;
    }

    public ModuleManager getModules() {
        return modules;
    }
}
