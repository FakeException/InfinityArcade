package com.infinitymine.arcade.system.resource.sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SQL {
    public static boolean tableExists(String table) {
        if (table == null) {
            return true;
        } else {
            try {
                Connection connection = MySQL.getConnection();
                if (connection == null) {
                    return true;
                }

                DatabaseMetaData metadata = connection.getMetaData();
                if (metadata == null) {
                    return true;
                }

                ResultSet rs = metadata.getTables(null, null, table, null);
                if (rs.next()) {
                    return false;
                }
            } catch (Exception ignored) {
            }

            return true;
        }
    }

    public static void insertData(String columns, String values, String table) {
        MySQL.update("INSERT INTO " + table + " (" + columns + ") VALUES (" + values + ");");
    }

    public static void deleteData(String column, String logic_gate, String data, String table) {
        if (data != null) {
            data = "'" + data + "'";
        }

        MySQL.update("DELETE FROM " + table + " WHERE " + column + logic_gate + data + ";");
    }

    public static boolean exists(String column, String data, String table) {
        if (data != null) {
            data = "'" + data + "'";
        }

        try {
            ResultSet rs = MySQL.query("SELECT * FROM " + table + " WHERE " + column + "=" + data + ";");

            while(rs.next()) {
                if (rs.getString(column) != null) {
                    return true;
                }
            }
        } catch (Exception ignored) {
        }

        return false;
    }

    public static void deleteTable(String table) {
        MySQL.update("DROP TABLE " + table + ";");
    }

    public static void truncateTable(String table) {
        MySQL.update("TRUNCATE TABLE " + table + ";");
    }

    public static void createTable(String table, String columns) {
        if (tableExists(table)) {
            MySQL.update("CREATE TABLE " + table + " (" + columns + ");");
        }

    }

    public static void set(String selected, Object object, String column, String logic_gate, String data, String table) {
        if (object != null) {
            object = "'" + object + "'";
        }

        if (data != null) {
            data = "'" + data + "'";
        }

        MySQL.update("UPDATE " + table + " SET " + selected + "=" + object + " WHERE " + column + logic_gate + data + ";");
    }

    public static Object get(String selected, String column, String logic_gate, String data, String table) {
        if (data != null) {
            data = "'" + data + "'";
        }

        try {
            ResultSet rs = MySQL.query("SELECT * FROM " + table + " WHERE " + column + logic_gate + data + ";");
            if (rs.next()) {
                return rs.getObject(selected);
            }
        } catch (Exception ignored) {}

        return null;
    }

    public static ArrayList<Object> listGet(String selected, String column, String logic_gate, String data, String table) {
        ArrayList<Object> array = new ArrayList<>();
        if (data != null) {
            data = "'" + data + "'";
        }

        try {
            ResultSet rs = MySQL.query("SELECT * FROM " + table + " WHERE " + column + logic_gate + data + ";");

            while(rs.next()) {
                array.add(rs.getObject(selected));
            }
        } catch (Exception ignored) {}

        return array;
    }

    public int countRows(String table) {
        int i = 0;
        if (table == null) {
            return i;
        } else {
            ResultSet rs = MySQL.query("SELECT * FROM " + table + ";");

            try {
                while(rs.next()) {
                    ++i;
                }
            } catch (Exception ignored) {}

            return i;
        }
    }
}