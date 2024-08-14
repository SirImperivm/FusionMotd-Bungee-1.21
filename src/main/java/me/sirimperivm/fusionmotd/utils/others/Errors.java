package me.sirimperivm.fusionmotd.utils.others;

import me.sirimperivm.fusionmotd.FusionMotd;
import me.sirimperivm.fusionmotd.utils.ConfigManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

@SuppressWarnings("all")
public class Errors {

    private FusionMotd plugin;
    private ConfigManager configManager;

    public Errors(FusionMotd plugin) {
        this.plugin = plugin;
        configManager = plugin.getConfigManager();
    }

    public boolean noPermCommand(CommandSender s, String node) {
        if (s.hasPermission(node))
            return false;
        s.sendMessage(new TextComponent(configManager.getTranslatedString(configManager.getMessages(), "no-perm.command")
                .replace("{node}", node))
        );
        return true;
    }

    public boolean noPermAction(ProxiedPlayer p, String node) {
        if (p.hasPermission(node))
            return false;
        p.sendMessage(new TextComponent(configManager.getTranslatedString(configManager.getMessages(), "no-perm.action")
                .replace("{node}", node))
        );
        return true;
    }

    public boolean noConsole(CommandSender s) {
        if (s instanceof ProxiedPlayer)
            return false;
        s.sendMessage(new TextComponent(configManager.getTranslatedString(configManager.getMessages(), "no-console")));
        return true;
    }
}
