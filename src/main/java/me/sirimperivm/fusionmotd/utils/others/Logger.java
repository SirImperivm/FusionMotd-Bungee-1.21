package me.sirimperivm.fusionmotd.utils.others;

import me.sirimperivm.fusionmotd.FusionMotd;
import me.sirimperivm.fusionmotd.utils.colors.Colors;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;

@SuppressWarnings("all")
public class Logger {

    private FusionMotd plugin;
    private Colors colors;

    private String pluginPrefix;

    public Logger(FusionMotd plugin, String pluginPrefix) {
        this.plugin = plugin;
        this.pluginPrefix = pluginPrefix;
        colors = plugin.getColors();
    }

    public void success(String message) {
        ProxyServer.getInstance().getConsole().sendMessage(new TextComponent(colors.translateString("&a[" + pluginPrefix + "] " + message)));
    }

    public void info(String message) {
        ProxyServer.getInstance().getConsole().sendMessage(new TextComponent(colors.translateString("&e[" + pluginPrefix + "] " + message)));
    }

    public void fail(String message) {
        ProxyServer.getInstance().getConsole().sendMessage(new TextComponent(colors.translateString("&c[" + pluginPrefix + "] " + message)));
    }
}
