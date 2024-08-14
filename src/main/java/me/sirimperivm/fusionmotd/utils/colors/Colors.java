package me.sirimperivm.fusionmotd.utils.colors;

import cz.foresttech.api.ColorAPI;
import me.sirimperivm.fusionmotd.FusionMotd;

@SuppressWarnings("all")
public class Colors {

    private FusionMotd plugin;

    public Colors(FusionMotd plugin) {
        this.plugin = plugin;
    }

    public String translateString(String t) {
        return ColorAPI.colorize(t);
    }
}
