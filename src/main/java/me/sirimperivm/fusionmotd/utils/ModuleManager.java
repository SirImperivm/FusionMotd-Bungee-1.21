package me.sirimperivm.fusionmotd.utils;

import me.sirimperivm.fusionmotd.FusionMotd;
import me.sirimperivm.fusionmotd.utils.colors.Colors;
import me.sirimperivm.fusionmotd.utils.others.Errors;
import me.sirimperivm.fusionmotd.utils.others.Logger;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.List;

@SuppressWarnings("all")
public class ModuleManager {

    private FusionMotd plugin;
    private Colors colors;
    private Logger log;
    private ConfigManager configManager;
    private Errors errors;

    private List<String> playerBlacklist;
    private List<String> ipBlacklist;

    public ModuleManager(FusionMotd plugin) {
        this.plugin = plugin;
        colors = plugin.getColors();
        log = plugin.getLog();
        configManager = plugin.getConfigManager();
        errors = plugin.getErrors();
    }

    public void createHelp(CommandSender s, String helpTarget, int page) {
        int visualizedPage = page;
        page--;

        List<String> totalLines = configManager.getMessages().getStringList("helps-creator." + helpTarget + ".lines");
        int commandsPerPage = configManager.getMessages().getInt("helps-creator." + helpTarget + ".max-lines-per-page", 5);
        int startIndex = page*commandsPerPage;
        int totalCommands = totalLines.size();
        int endIndex = Math.min((page+1) * commandsPerPage, totalCommands);

        if (visualizedPage <= 0 || visualizedPage > (int) Math.floor((double) totalCommands / commandsPerPage) + 1) {
            s.sendMessage(new TextComponent(configManager.getTranslatedString(configManager.getMessages(), "invalid-args.number-required")
                    .replace("{page}", String.valueOf(visualizedPage))
            ));
            return;
        }

        s.sendMessage(new TextComponent(configManager.getTranslatedString(configManager.getMessages(), "helps-creator." + helpTarget + ".header")));
        s.sendMessage(new TextComponent(configManager.getTranslatedString(configManager.getMessages(), "helps-creator." + helpTarget + ".title")));
        s.sendMessage(new TextComponent(configManager.getTranslatedString(configManager.getMessages(), "helps-creator." + helpTarget + ".spacer")));

        for (int i=startIndex; i<endIndex; i++) {
            String line = totalLines.get(i);
            if (line != null) {
                String[] parts = line.split("-");
                if (parts.length == 2) {
                    String commandName = parts[0].trim();
                    String commandDescription = parts[1].trim();
                    s.sendMessage(new TextComponent(configManager.getTranslatedString(configManager.getMessages(), "helps-creator." + helpTarget + ".line-format")
                            .replace("{command-name}", colors.translateString(commandName))
                            .replace("{command-description}", colors.translateString(commandDescription))
                    ));
                }
            }
        }

        s.sendMessage(new TextComponent(configManager.getTranslatedString(configManager.getMessages(), "helps-creator." + helpTarget + ".spacer")));
        s.sendMessage(new TextComponent(configManager.getTranslatedString(configManager.getMessages(), "helps-creator." + helpTarget + ".page-format")
                .replace("{currentpage}", String.valueOf(visualizedPage))
        ));
        s.sendMessage(new TextComponent(configManager.getTranslatedString(configManager.getMessages(), "helps-creator." + helpTarget + ".footer")));
    }

    public boolean containsOnlyNumbers(String target) {
        try {
            Integer.parseInt(target);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
