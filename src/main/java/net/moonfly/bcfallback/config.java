package net.moonfly.bcfallback;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class config {
    private static File loadResource(Plugin plugin) {
        File folder = plugin.getDataFolder();
        if (!folder.exists())
            folder.mkdir();
        File resourceFile = new File(folder, "config.yml");
        try {
            if (!resourceFile.exists()) {
                InputStream in = plugin.getResourceAsStream("config.yml");
                    Files.copy(in,resourceFile.toPath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resourceFile;
    }
    public boolean getKey(Plugin plugin, String keyname) {
        Configuration configuration = null;
        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(config.loadResource(plugin));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert configuration != null;
        return configuration.getBoolean(keyname);
    }

    public String getString(Plugin plugin, String keyname) {
        Configuration configuration = null;
        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(config.loadResource(plugin));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert configuration != null;
        return configuration.getString(keyname);
    }

}