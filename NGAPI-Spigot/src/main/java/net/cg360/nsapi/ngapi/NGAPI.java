package net.cg360.nsapi.ngapi;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * NGAPI 2.0 is a minigame api created for Spigot! Based
 * on the
 */
public class NGAPI extends JavaPlugin {

    protected static NGAPI ngapi = null;

    @Override
    public void onEnable() {

        try {
            ngapi = this;
            // TODO: Load systems.

        } catch (Exception err){
            ngapi = null;
            err.printStackTrace();
            // Just making sure everything is properly nulled.
        }
    }

    public static NGAPI get() { return ngapi; }
    public static boolean isNGAPILoaded() { return ngapi != null; }
}
