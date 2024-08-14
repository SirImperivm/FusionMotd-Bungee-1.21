package me.sirimperivm.fusionmotd.commands;

import me.sirimperivm.fusionmotd.FusionMotd;
import me.sirimperivm.fusionmotd.utils.ConfigManager;
import me.sirimperivm.fusionmotd.utils.ModuleManager;
import me.sirimperivm.fusionmotd.utils.MotdManager;
import me.sirimperivm.fusionmotd.utils.colors.Colors;
import me.sirimperivm.fusionmotd.utils.others.Errors;
import me.sirimperivm.fusionmotd.utils.others.Logger;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class MainCommand extends Command implements TabExecutor {

    private FusionMotd plugin;
    private Colors colors;
    private Logger log;
    private ConfigManager configManager;
    private Errors errors;
    private ModuleManager modules;
    private MotdManager motdManager;

    public MainCommand(String name, FusionMotd plugin) {
        super(name);
        this.plugin = plugin;
        colors = plugin.getColors();
        log = plugin.getLog();
        configManager = plugin.getConfigManager();
        errors = plugin.getErrors();
        modules = plugin.getModules();
        motdManager = plugin.getMotdManager();
    }

    private void getUsage(CommandSender s, int page) {
        modules.createHelp(s, "main-command", page);
    }

    @Override
    public void execute(CommandSender s, String[] a) {
        if (errors.noPermCommand(s, configManager.getSettings().getString("permissions.commands.main-command.main"))) {
            return;
        } else {
            if (a.length == 0) {
                getUsage(s, 1);
            } else if (a.length == 1) {
                if (a[0].equalsIgnoreCase("reload")) {
                    if (errors.noPermCommand(s, configManager.getSettings().getString("permissions.commands.main-command.reload"))) {
                        return;
                    } else {
                        configManager.loadAll();
                        s.sendMessage(new TextComponent(configManager.getTranslatedString(configManager.getMessages(), "plugin-reloaded")));
                    }
                } else {
                    getUsage(s, 1);
                }
            } else if (a.length == 2) {
                if (a[0].equalsIgnoreCase("help")) {
                    String pageTarget = a[1];
                    if (!modules.containsOnlyNumbers(pageTarget)) {
                        s.sendMessage(new TextComponent(configManager.getTranslatedString(configManager.getMessages(), "invalid-args.number-required")
                                .replace("{arg}", pageTarget)
                        ));
                        return;
                    }
                    int page = Integer.parseInt(pageTarget);
                    getUsage(s, page);
                } else {
                    getUsage(s, 1);
                }
            } else {
                getUsage(s, 1);
            }
        }
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender s, String[] a) {
        if (s.hasPermission(configManager.getSettings().getString("permissions.commands.main-command.main"))) {
            if (a.length == 1) {
                List<String> toReturn = new ArrayList<>();
                toReturn.add("help");
                if (s.hasPermission(configManager.getSettings().getString("permissions.commands.main-command.reload"))) {
                    toReturn.add("reload");
                }
                return toReturn;
            } else if (a.length == 2) {
                List<String> toReturn = new ArrayList<>();
                if (a[0].equalsIgnoreCase("help")) {
                    for (int i=0; i<999; i++) {
                        toReturn.add(String.valueOf(i));
                    }
                }
                return toReturn;
            }
        }
        return new ArrayList<>();
    }
}
