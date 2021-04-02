package me.cg360.nsapi.ngapi.kit.impl.basic;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.cg360.nsapi.commons.data.Settings;
import net.cg360.nsapi.commons.id.Identifier;
import me.cg360.nsapi.ngapi.NGAPI;
import me.cg360.nsapi.ngapi.keychain.KitSetting;
import me.cg360.nsapi.ngapi.kit.Kit;
import me.cg360.nsapi.ngapi.kit.KitBehaviour;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.UUID;

public class KitCookie extends Kit {

    // the base64 found in the commands at https://minecraft-heads.com/custom-heads
    // is already correct. Just need to turn the command string into bytes.
    public static final byte[] SKIN_DATA = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTZkMjFmMzQ5MWI4NDFlYTEyNTg0MTlkNzY4ZjViZTU2MzJiOWNjOTMyNDc2NzhiNDQyMjdmMzQzN2VkNDc5ZiJ9fX0=".getBytes(StandardCharsets.UTF_8);

    @Override
    protected Settings genKitSettings() {
        return new Settings()
                .set(KitSetting.COST, 1000)
                .set(KitSetting.SELECTOR_VISIBLE, true);
    }

    @Override
    public Identifier getKitID() {
        return NGAPI.NAME.id("cookie_monster");
    }

    @Override
    public String getKitDisplayName() {
        return "Cookie Critter";
    }

    @Override
    public String getKitDescription() {
        return "*Agressive Non-Copyright Cookie Eating*";
    }



    @Override
    public ItemStack[] getHotbarItems() {
        ItemStack cookie = new ItemStack(Material.COOKIE);
        ItemMeta cookieMeta = cookie.getItemMeta();

        if(cookieMeta == null) return new ItemStack[0];
        cookieMeta.setDisplayName("" + ChatColor.GOLD + ChatColor.BOLD + "Cookie-inator");
        cookieMeta.addEnchant(Enchantment.DAMAGE_ALL, 10, true);
        cookieMeta.addEnchant(Enchantment.KNOCKBACK, 2, true);
        cookie.setItemMeta(cookieMeta);


        return new ItemStack[] {
            cookie
        };
    }

    @Override
    public Optional<ItemStack> getKitHelmet() {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();

        if(meta == null) return Optional.empty();
        GameProfile headProfile = new GameProfile(UUID.randomUUID(), null);
        headProfile.getProperties().put("textures", new Property("textures", new String(SKIN_DATA)));
        Field profileField = null;

        try {
            profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, headProfile);

        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
        skull.setItemMeta(meta);
        return Optional.of(skull);
    }

    @Override
    public Optional<ItemStack> getKitChestplate() {
        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        return applyBlueColour(chestplate);
    }

    @Override
    public Optional<ItemStack> getKitLeggings() {
        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        return applyBlueColour(leggings);
    }

    @Override
    public Optional<ItemStack> getKitBoots() {
        ItemStack sandals = new ItemStack(Material.LEATHER_BOOTS);
        return applyBlueColour(sandals);
    }



    @Override
    public Optional<Class<? extends KitBehaviour>> getKitBehaviour() {
        return Optional.empty();
    }

    protected static Optional<ItemStack> applyBlueColour(ItemStack armour) {
        LeatherArmorMeta meta = (LeatherArmorMeta) armour.getItemMeta();

        if(meta == null) return Optional.empty();
        meta.setColor(Color.BLUE);
        armour.setItemMeta(meta);
        return Optional.of(armour);
    }
}
