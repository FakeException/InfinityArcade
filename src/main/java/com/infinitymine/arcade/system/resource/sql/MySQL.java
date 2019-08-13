package com.infinitymine.arcade.system.resource.sql;

import com.infinitymine.arcade.InfinityArcade;
import com.infinitymine.arcade.system.files.yml.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQL {
    private static Connection con;

    public static Connection getConnection() {
        return con;
    }

    public static void setConnection(String host, String user, String password, String database, String port) {
        if (host != null && user != null && password != null && database != null) {
            disconnect(false);

            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user, password);
                InfinityArcade.getInstance().getConsole().info("§bMySQL Connected!");
            } catch (Exception var6) {
                InfinityArcade.getInstance().getConsole().error("§cCould not connect to your MySQL database!");
            }

        }
    }

    public static void connect() {
        connect(true);
    }

    private static void connect(boolean message) {
        String host = Config.MYSQL_HOST.getAsString();
        String user = Config.MYSQL_USER.getAsString();
        String password = Config.MYSQL_PASSWORD.getAsString();
        String database = Config.MYSQL_DATABASE.getAsString();
        String port = Config.MYSQL_PORT.getAsString();
        if (isConnected()) {
            if (message) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL Connect Error: Already connected");
            }
        } else if (host.equalsIgnoreCase("")) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Config Error: Host is blank");
        } else if (user.equalsIgnoreCase("")) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Config Error: User is blank");
        } else if (password.equalsIgnoreCase("")) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Config Error: Password is blank");
        } else if (database.equalsIgnoreCase("")) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Config Error: Database is blank");
        } else if (port.equalsIgnoreCase("")) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Config Error: Port is blank");
        } else {
            setConnection(host, user, password, database, port);
        }

    }

    public static void disconnect() {
        disconnect(true);
    }

    private static void disconnect(boolean message) {
        try {
            if (isConnected()) {
                con.close();
                if (message) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "MySQL disconnected.");
                }
            } else if (message) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL Disconnect Error: No existing connection");
            }
        } catch (Exception var2) {
            if (message) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL Disconnect Error: " + var2.getMessage());
            }
        }

        con = null;
    }

    public static void reconnect() {
        disconnect();
        connect();
    }

    public static boolean isConnected() {
        if (con != null) {
            try {
                return !con.isClosed();
            } catch (Exception var1) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL Connection:");
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error: " + var1.getMessage());
            }
        }

        return false;
    }

    public static void update(String command) {
        if (command != null) {
            connect(false);

            try {
                Statement st = getConnection().createStatement();
                st.executeUpdate(command);
                st.close();
            } catch (Exception var2) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL Update:");
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Command: " + command);
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error: " + var2.getMessage());
            }

            disconnect(false);
        }
    }

    public static ResultSet query(String command) {
        if (command == null) {
            return null;
        } else {
            connect(false);
            ResultSet rs = null;

            try {
                Statement st = getConnection().createStatement();
                rs = st.executeQuery(command);
            } catch (Exception var3) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL Query:");
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Command: " + command);
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error: " + var3.getMessage());
            }

            return rs;
        }
    }
}