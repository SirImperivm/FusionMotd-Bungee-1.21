package me.sirimperivm.fusionmotd.utils.colors;

import me.sirimperivm.fusionmotd.FusionMotd;
import net.md_5.bungee.api.ChatColor;

@SuppressWarnings("all")
public class Colors {

    private FusionMotd plugin;

    public Colors(FusionMotd plugin) {
        this.plugin = plugin;
    }

    public String translateString(String t) {
        return ChatColor.translateAlternateColorCodes('&', t);
    }
}
