package com.infinitymine.arcade.system.files.yml;

import com.google.common.collect.Lists;
import com.infinitymine.arcade.InfinityArcade;
import com.infinitymine.arcade.system.files.ResourceType;
import com.infinitymine.arcade.system.files.Resources;
import com.infinitymine.arcade.system.resource.yml.ResourceYaml;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;
import java.util.stream.Collectors;

public enum Config {

    CONFIG_VERSION("config-version", 1),

    SETUP("setup", true),

    LANGUAGE_FILE("language-file", "Language-EN.yml"),

    SPAWN_ON_JOIN("spawnConfig.spawnConfig-on-join", false),

    SPAWN_ON_DEATH("spawnConfig.spawnConfig-on-death", false),

    MYSQL_ENABLED("mysql.enabled", false),

    MYSQL_HOST("mysql.hostname", ""),

    MYSQL_PORT("mysql.port", "3306"),

    MYSQL_USER("mysql.user", ""),

    MYSQL_PASSWORD("mysql.password", ""),

    MYSQL_DATABASE("mysql.database", "")

    ;

    public static ResourceYaml getConfig() {
        return (ResourceYaml) Resources.get().getResource(ResourceType.CONFIG);
    }

    private final String path;
    private final Object def;

    /**
     * @param path the path to the setting.
     * @param def  default value of the setting.
     */
    Config(String path, Object def) {
        this.path = path;
        this.def = def;
    }

    /**
     * @return path of the setting in Config.yml.
     */
    public String getPath() {
        return path;
    }

    public static void saveFile() {
        InfinityArcade.getResourceProvider().saveResource(Config.getConfig());
    }

    /**
     * @return value of the path as a string, also replaces the {prefix} placeholder.
     */
    public String getAsString() {
        return getConfig().getString(path, String.valueOf(def));
    }

    /**
     * @return value of the path as a string list.
     */
    public List<String> getAsStringList() {
        List<String> list = Lists.newArrayList();

        if (getConfig().getConfiguration().isString(path)) {
            list.add(getAsString());
        } else {
            list.addAll(getConfig().getConfiguration().getStringList(path));
        }

        return list.stream()
                .map(s -> s.replace("{prefix}", Language.PREFIX.getMessage().get(0)))
                .collect(Collectors.toList());
    }

    /**
     * @return value of the path as an int.
     */
    public int getAsInt() {
        return getConfig().getInt(path, Integer.parseInt(getAsString()));
    }

    /**
     * @return default value as an integer.
     */
    public int getAsIntDefault() {
        return Integer.parseInt(String.valueOf(def));
    }

    /**
     * @return value of the path as a short.
     */
    public short getAsShort() {
        return getConfig().getShort(path, Short.parseShort(getAsString()));
    }

    /**
     * @return value of the path as a double.
     */
    public double getAsDouble() {
        return getConfig().getDouble(path, Double.parseDouble(getAsString()));
    }

    /**
     * @return value of the path as a boolean.
     */
    public boolean getAsBoolean() {
        return getConfig().getBoolean(path, Boolean.parseBoolean(getAsString()));
    }

    public void setValue(Object value) {
        getConfig().set(path, value);
    }

    /**
     * @return value of the path as a configuration section.
     */
    public ConfigurationSection getAsConfigurationSection() {
        return getConfig().getConfigurationSection(path);
    }
}