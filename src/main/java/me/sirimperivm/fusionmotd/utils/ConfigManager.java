package me.sirimperivm.fusionmotd.utils;

import me.sirimperivm.fusionmotd.FusionMotd;
import me.sirimperivm.fusionmotd.utils.colors.Colors;
import me.sirimperivm.fusionmotd.utils.others.Logger;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;

@SuppressWarnings("all")
public class ConfigManager {

    private FusionMotd plugin;
    private Colors colors;
    private Logger log;

    private File folder;
    private File settingsFile, messagesFile, motdFile;
    private Configuration settings, messages, motd;

    public ConfigManager(FusionMotd plugin) {
        this.plugin = plugin;
        colors = plugin.getColors();
        log = plugin.getLog();

        folder = plugin.getDataFolder();
        settingsFile = new File(folder, "settings.yml");
        messagesFile = new File(folder, "messages.yml");
        motdFile = new File(folder, "motds.yml");

        if (!folder.exists()) folder.mkdir();
        if (!settingsFile.exists()) createSettings();
        if (!messagesFile.exists()) createMessages();
        if (!motdFile.exists()) createMotd();

        loadAll();
    }

    private void createSettings() {
        try {
            Files.copy(plugin.getResourceAsStream("bungeeSettings.yml"), settingsFile.toPath(), new CopyOption[0]);
        } catch (IOException e) {
            log.fail("Impossibile creare il file settings.yml!");
            e.printStackTrace();
        }
    }

    private void createMessages() {
        try {
            Files.copy(plugin.getResourceAsStream("bungeeMessages.yml"), messagesFile.toPath(), new CopyOption[0]);
        } catch (IOException e) {
            log.fail("Impossibile creare il file messages.yml!");
            e.printStackTrace();
        }
    }

    private void createMotd() {
        try {
            Files.copy(plugin.getResourceAsStream("bungeeMotd.yml"), motdFile.toPath(), new CopyOption[0]);
        } catch (IOException e) {
            log.fail("Impossibile creare il file motd.yml!");
            e.printStackTrace();
        }
    }

    public void saveSettings() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(settings, settingsFile);
        } catch (IOException e) {
            log.fail("Impossibile salvare il file settings.yml!");
            e.printStackTrace();
        }
    }

    public void saveMessages() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(messages, messagesFile);
        } catch (IOException e) {
            log.fail("Impossibile salvare il file messages.yml!");
            e.printStackTrace();
        }
    }

    public void saveMotd() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(motd, motdFile);
        } catch (IOException e) {
            log.fail("Impossibile salvare il file motd.yml!");
            e.printStackTrace();
        }
    }

    public void loadSettings() {
        try {
            settings = ConfigurationProvider.getProvider(YamlConfiguration.class).load(settingsFile);
        } catch (IOException e) {
            log.fail("Impossibile caricare il file settings.yml!");
            e.printStackTrace();
        }
    }

    public void loadMessages() {
        try {
            messages = ConfigurationProvider.getProvider(YamlConfiguration.class).load(messagesFile);
        } catch (IOException e) {
            log.fail("Impossibile caricare il file messages.yml!");
            e.printStackTrace();
        }
    }

    public void loadMotd() {
        try {
            motd = ConfigurationProvider.getProvider(YamlConfiguration.class).load(motdFile);
        } catch (IOException e) {
            log.fail("Impossibile caricare il file motd.yml!");
            e.printStackTrace();
        }
    }

    public void saveAll() {
        saveSettings();
        saveMessages();
        saveMotd();
    }

    public void loadAll() {
        loadSettings();
        loadMessages();
        loadMotd();
    }

    public File getSettingsFile() {
        return settingsFile;
    }

    public Configuration getSettings() {
        return settings;
    }

    public File getMessagesFile() {
        return messagesFile;
    }

    public Configuration getMessages() {
        return messages;
    }

    public File getMotdFile() {
        return motdFile;
    }

    public Configuration getMotd() {
        return motd;
    }

    public String getTranslatedString(Configuration c, String p) {
        return colors.translateString(c.getString(p));
    }
}
