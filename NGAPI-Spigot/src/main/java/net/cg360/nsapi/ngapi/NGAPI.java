package net.cg360.nsapi.ngapi;

import net.cg360.nsapi.commons.id.Namespace;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * NGAPI 2.0 is a minigame api created for Spigot! Based
 * on the
 */
public class NGAPI extends JavaPlugin {

    public static final Namespace NAME = new Namespace("ngapi");
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
