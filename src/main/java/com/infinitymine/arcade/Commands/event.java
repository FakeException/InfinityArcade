package com.infinitymine.arcade.Commands;

import com.infinitymine.arcade.system.register.command.OmegaCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class event extends OmegaCommand {


    public event(){
        this.setAliases("event", "e");
    }
    private static String prefix = "§3§lInfinity §b§lMine §e➦ §7";
    private static String eprefix = "§3§lGame settings §e➦ §f";
    private static String attivo = "§a True";
    private static String disattivo = "§4 False";

    public static boolean damagepvp = true;
    public static boolean damage = true;
    public static boolean damagepve = true;
    public static boolean blockbreak = false;
    public static boolean blockplace = false;
    public static boolean itempickup = false;
    public static boolean itemdrop = false;


    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!sender.hasPermission("arcade.admin")){
            Bukkit.broadcastMessage(prefix + "You don't have sufficient permission to do this!");
        } else {
            if(args.length == 1){
                if(args[0].equalsIgnoreCase("damagepvp")){
                    if(damagepvp){
                        damagepvp = false;
                        Bukkit.broadcastMessage(eprefix + "Damage PvP:" + disattivo);
                    } else {
                        damagepvp = true;
                        Bukkit.broadcastMessage(eprefix + "Damage PvP:" + attivo);
                    }
                } else if(args[0].equalsIgnoreCase("damagepve")){
                    if(damagepve){
                        damagepve = false;
                        Bukkit.broadcastMessage(eprefix + "Damage PvE:" + disattivo);
                    } else {
                        damagepve = true;
                        Bukkit.broadcastMessage(eprefix + "Damage PvE:" + attivo);
                    }

                } else if(args[0].equalsIgnoreCase("damage")){
                    if(damage){
                        damage = false;
                        Bukkit.broadcastMessage(eprefix + "Damage:" + disattivo);
                    } else {
                        damage = true;
                        Bukkit.broadcastMessage(eprefix + "Damage:" + attivo);
                    }
                } else if(args[0].equalsIgnoreCase("blockbreak")){
                    if(blockbreak){
                        blockbreak = false;
                        Bukkit.broadcastMessage(eprefix + "Block Break:" + disattivo);
                    } else {
                        blockbreak = true;
                        Bukkit.broadcastMessage(eprefix + "Block Break:" + attivo);
                    }
                } else if(args[0].equalsIgnoreCase("blockplace")){
                    if(blockplace){
                        blockplace = false;
                        Bukkit.broadcastMessage(eprefix + "Block Place:" + disattivo);
                    } else {
                        blockplace= true;
                        Bukkit.broadcastMessage(eprefix + "Block Place:" + attivo);
                    }
                } else if(args[0].equalsIgnoreCase("itempickup")){
                    if(itempickup){
                        itempickup = false;
                        Bukkit.broadcastMessage(eprefix + "Item Pickup:" + disattivo);
                    } else {
                        itempickup= true;
                        Bukkit.broadcastMessage(eprefix + "Item Pickup:" + attivo);
                    }
                } else if(args[0].equalsIgnoreCase("itemdrop")){
                    if(itemdrop){
                        itemdrop = false;
                        Bukkit.broadcastMessage(eprefix + "Item Drop:" + disattivo);
                    } else {
                        itemdrop= true;
                        Bukkit.broadcastMessage(eprefix + "Item Drop:" + attivo);
                    }
                }
            }
        }

    }



}
