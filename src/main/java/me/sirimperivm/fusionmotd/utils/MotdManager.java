package me.sirimperivm.fusionmotd.utils;

import me.sirimperivm.fusionmotd.FusionMotd;
import me.sirimperivm.fusionmotd.utils.colors.Colors;
import me.sirimperivm.fusionmotd.utils.others.Errors;
import me.sirimperivm.fusionmotd.utils.others.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("all")
public class MotdManager {

    private FusionMotd plugin;
    private Colors colors;
    private Logger log;
    private ConfigManager configManager;
    private Errors errors;
    private ModuleManager modules;

    public MotdManager(FusionMotd plugin) {
        this.plugin = plugin;
        colors = plugin.getColors();
        log = plugin.getLog();
        configManager = plugin.getConfigManager();
        errors = plugin.getErrors();
        modules = plugin.getModules();
    }

    public String getRandomMotd() {
        List<String> motdsList = configManager.getMotd().getStringList("enabled-motds");
        int listSize = motdsList.size();
        if (listSize < 1)
            return null;
        Random random = new Random();
        String motdName = motdsList.get(random.nextInt(listSize));
        return getMotd(motdName);
    }

    private String getMotd(String motdName) {
        String motd = "";
        if (configManager.getMotd().getSection("motds." + motdName).getKeys() == null)
            return null;
        String line1 = configManager.getMotd().getString("motds." + motdName + ".line-1", null);
        String line2 = configManager.getMotd().getString("motds." + motdName + ".line-2", null);
        if (line2 == null || line2.equals("")) {
            line2 = "&r";
        }
        motd = line1 + "\n" + line2;
        return motd;
    }

    public List<String> getHoverText() {
        List<String> configLines = configManager.getMotd().getStringList("hover-text");
        List<String> hoverText = new ArrayList<>();
        boolean editPlayersCount = configManager.getSettings().getBoolean("settings.edit-players-count", true);
        int onlinePlayers = plugin.getProxy().getOnlineCount();
        int maxPlayers = editPlayersCount ? onlinePlayers+1 : plugin.getProxy().getConfig().getPlayerLimit();
        for (String line : configLines) {
            line = line
                    .replace("%online%", String.valueOf(onlinePlayers))
                    .replace("%maxplayers%", String.valueOf(maxPlayers));
            hoverText.add(line);
        }
        return hoverText;
    }
}
