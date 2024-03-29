package com.infinitymine.arcade.system.resource;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class TemporaryConfig {

    private File file;
    private FileConfiguration fileConfig;

    /** Creates a new config at the path, with the fileName, with a configCreate method caller, and uses the Plugin */
    public TemporaryConfig(String path, String fileName, Runnable callback, Plugin plugin) {
        if (!fileName.contains(".yml")) {
            fileName = fileName + ".yml";
        }
        file = new File(path, fileName);
        fileConfig = YamlConfiguration.loadConfiguration(file);

        if (!file.exists()) {
            fileConfig.options().copyDefaults(true);
            callback.run();
            try {
                fileConfig.save(file);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    /** Creates a new config at the path, with the fileName, and uses the Plugin */
    public TemporaryConfig(String path, String fileName, Plugin plugin) {
        if (!fileName.contains(".yml")) {
            fileName = fileName + ".yml";
        }
        file = new File(path, fileName);
        fileConfig = YamlConfiguration.loadConfiguration(file);

        if (!file.exists()) {
            fileConfig.options().copyDefaults(true);
            try {
                fileConfig.save(file);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    /** Get the Configuration section */
    public FileConfiguration getConfig() {
        return fileConfig;
    }

    /** Save the config */
    public void saveConfig() {
        try {
            fileConfig.save(file);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /** Set a location in the config */
    public void setLocation(String path, Location location) {
        fileConfig.set(path + ".World", location.getWorld().getName());
        fileConfig.set(path + ".X", location.getX());
        fileConfig.set(path + ".Y", location.getY());
        fileConfig.set(path + ".Z", location.getZ());
        fileConfig.set(path + ".Yaw", location.getYaw());
        fileConfig.set(path + ".Pitch", location.getPitch());
        saveConfig();
    }

    /** Get a location in the config */
    public Location getLocation(String path) {
        if (fileConfig.getString(path + ".World") == null) {
            return null;
        }
        return new Location(Bukkit.getWorld(fileConfig.getString(path + ".World")), fileConfig.getDouble(path + ".X"), fileConfig.getDouble(path + ".Y"), fileConfig.getDouble(path + ".Z"), (float) fileConfig.getDouble(path + ".Yaw"), (float) fileConfig.getDouble(path + ".Pitch"));
    }

    public boolean isPathNull(String path) {
        return fileConfig.getString(path) == null;
    }
}