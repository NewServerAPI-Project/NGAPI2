package net.cg360.nsapi.ngapi.kit;

import net.cg360.nsapi.commons.id.Identifier;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

public abstract class Kit extends Loadout {

    @Override
    public void applyTo(Player player, boolean clearPreviousInventory) {
        super.applyTo(player, clearPreviousInventory);
        //TODO: Apply kit behaviour
    }

    public void removeFrom(Player player) { removeFrom(player, true); }
    public void removeFrom(Player player, boolean clearInventory) {
        // May want to remove kit behaviours without removing the inventory?
        // Provide an option at least.

        if(clearInventory) {
            player.getInventory().clear();
            player.setItemOnCursor(new ItemStack(Material.AIR));
        }

        //TODO: Terminate kit behaviour
    }

    /** @return A meaningful identifier for a kit. */
    public abstract Identifier getKitID();

    /** @return The displayed name of a kit. */
    public abstract String getKitDisplayName();

    /** @return A summary of what the kit includes. */
    public abstract String getKitDescription();


    // Shortened from ExtendedKit
    /** @return a class that uses a filtered listener to add more advanced kit abilities. */
    public abstract Optional<Class<? extends KitBehaviour>> getKitBehaviour();
}
