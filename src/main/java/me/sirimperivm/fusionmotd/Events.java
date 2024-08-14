package me.sirimperivm.fusionmotd;

import me.sirimperivm.fusionmotd.utils.ConfigManager;
import me.sirimperivm.fusionmotd.utils.ModuleManager;
import me.sirimperivm.fusionmotd.utils.MotdManager;
import me.sirimperivm.fusionmotd.utils.colors.Colors;
import me.sirimperivm.fusionmotd.utils.others.Errors;
import me.sirimperivm.fusionmotd.utils.others.Logger;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.List;

@SuppressWarnings("all")
public class Events implements Listener {

    private FusionMotd plugin;
    private Colors colors;
    private Logger log;
    private ConfigManager configManager;
    private Errors errors;
    private ModuleManager modules;
    private MotdManager motdManager;

    public Events(FusionMotd plugin) {
        this.plugin = plugin;
        colors = plugin.getColors();
        log = plugin.getLog();
        configManager = plugin.getConfigManager();
        errors = plugin.getErrors();
        modules = plugin.getModules();
        motdManager = plugin.getMotdManager();
    }

    @EventHandler
    public void onPing(ProxyPingEvent e) {
        ServerPing serverPing = e.getResponse();
        if (configManager.getSettings().getBoolean("settings.edit-players-count")) {
            int minPlayers = plugin.getProxy().getOnlineCount();
            int maxPlayers = minPlayers+1;
            ServerPing.Players players = new ServerPing.Players(maxPlayers, minPlayers, new ServerPing.PlayerInfo[0]);
            serverPing.setPlayers(players);
        }
        String motd = motdManager.getRandomMotd();
        if (motd == null) {
            serverPing.setDescriptionComponent(new TextComponent(configManager.getMessages().getString("default-motd-message")));
        } else {
            serverPing.setDescriptionComponent(new TextComponent(colors.translateString(motd)));
        }
        if (configManager.getSettings().getBoolean("settings.edit-protocol-version", true)) {
            ServerPing.Protocol protocol = new ServerPing.Protocol(configManager.getMotd().getString("protocol-version.name"), configManager.getMotd().getInt("protocol-version.protocol-id"));
            serverPing.setVersion(protocol);
        }
        if (configManager.getSettings().getBoolean("settings.show-hover-text")) {
            List<String> hoverText = motdManager.getHoverText();
            ServerPing.PlayerInfo[] playerInfos = new ServerPing.PlayerInfo[hoverText.size()];
            for (int i=0; i< hoverText.size(); i++) {
                playerInfos[i] = new ServerPing.PlayerInfo(colors.translateString(hoverText.get(i)), "");
            }
            serverPing.getPlayers().setSample(playerInfos);
        }
        e.setResponse(serverPing);
    }

    private String centerMotd(String motd) {
        int lineWidth = 80;

        int spaces = (lineWidth - motd.length()) / 2;

        StringBuilder padding = new StringBuilder();
        for (int i = 0; i < spaces; i++) {
            padding.append(" ");
        }

        return padding.toString() + motd;
    }
}
