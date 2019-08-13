package com.infinitymine.arcade;

import com.infinitymine.arcade.Commands.event;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class listener implements Listener {

    private static String prefix = "§3§lInfinity §b§lMine §e➦ §7";

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        Player p = e.getPlayer();
        if(!event.blockbreak){
            e.setCancelled(true);
            p.sendMessage(prefix + "You cannot break blocks!");
        }
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        Player p = e.getPlayer();
        if(!event.blockplace){
            e.setCancelled(true);
            p.sendMessage(prefix + "You cannot place blocks!");
        }
    }
    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e){
        Player p = e.getPlayer();
        if(!event.itemdrop){
            e.setCancelled(true);
            p.sendMessage(prefix + "You cannot drop §6" + e.getItemDrop() + "§7!");
        }
    }
    @EventHandler
    public void onItemPickUp(PlayerPickupItemEvent e){
        Player p = e.getPlayer();
        if(!event.itempickup){
            e.setCancelled(true);
            p.sendMessage(prefix + "You cannot pickup §6" + e.getItem() + "§7!");
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e){
        if(e.getEntity() instanceof Player){
            if(!event.damage){
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void damagePvP(EntityDamageByEntityEvent e){
        if(e.getEntity() instanceof Player){
            if(e.getDamager() instanceof Player){
                if(!event.damagepvp){
                    e.setCancelled(true);
                }
            } else {
                if(!event.damagepve){
                    e.setCancelled(true);
                }
            }
        }
    }

}
