package net.cg360.nsapi.ngapi.kit;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

// Nukkit uses "Item" instead of Spigot's "ItemStack"
public abstract class Loadout {


    public void applyTo(Player player, boolean clearPreviousInventory) {

        if(clearPreviousInventory) {
            player.getInventory().clear();
            player.setItemOnCursor(new ItemStack(Material.AIR));
        }


        for(int i = 0; i < 9 && i < getHotbarItems().length; i++){
            ItemStack item = getHotbarItems()[i];

            if(item != null && item.getType() != Material.AIR) {
                player.getInventory().setItem(i, item.clone());
            }
        }

        for(ItemStack item: getInventoryItems()) player.getInventory().addItem(item.clone());


        getKitHelmet().ifPresent(itemStack -> player.getInventory().setHelmet(itemStack.clone()));
        getKitChestplate().ifPresent(itemStack -> player.getInventory().setChestplate(itemStack.clone()));
        getKitLeggings().ifPresent(itemStack -> player.getInventory().setLeggings(itemStack.clone()));
        getKitBoots().ifPresent(itemStack -> player.getInventory().setBoots(itemStack.clone()));

    }

    /** @return the items a player should recieve when applied, only in the hotbar.*/
    public ItemStack[] getHotbarItems(){ return new ItemStack[0]; }

    /** @return the items a player should recieve when applied.*/
    public ItemStack[] getInventoryItems(){ return new ItemStack[0]; }

    /** @return the item a player should recieve in their head armour slot when applied.*/
    public Optional<ItemStack> getKitHelmet() { return Optional.empty(); }
    /** @return the item a player should recieve in their body armour slot when applied.*/
    public Optional<ItemStack> getKitChestplate() { return Optional.empty(); }
    /** @return the item a player should recieve in their leg armour slot when applied.*/
    public Optional<ItemStack> getKitLeggings() { return Optional.empty(); }
    /** @return the item a player should recieve in their feet armour slot when applied.*/
    public Optional<ItemStack> getKitBoots() { return Optional.empty(); }

}
