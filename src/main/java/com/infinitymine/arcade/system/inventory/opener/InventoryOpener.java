package com.infinitymine.arcade.system.inventory.opener;

import com.infinitymine.arcade.system.inventory.content.InventoryContents;
import com.infinitymine.arcade.system.inventory.ClickableItem;
import com.infinitymine.arcade.system.inventory.OmegaInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public interface InventoryOpener {

    Inventory open(OmegaInventory inv, Player player);
    boolean supports(InventoryType type);

    default void fill(Inventory handle, InventoryContents contents) {
        ClickableItem[][] items = contents.all();

        for(int row = 0; row < items.length; row++) {
            for(int column = 0; column < items[row].length; column++) {
                if(items[row][column] != null)
                    handle.setItem(9 * row + column, items[row][column].getItem());
            }
        }
    }

}
