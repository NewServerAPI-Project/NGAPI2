package net.cg360.nsapi.ngapi;

import net.cg360.nsapi.commons.event.EventManager;
import net.cg360.nsapi.commons.id.Namespace;
import net.cg360.nsapi.ngapi.registry.KitRegistry;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * NGAPI 2.0 is a minigame api created for Spigot! Based
 * on the
 */
public class NGAPI extends JavaPlugin {

    public static final Namespace NAME = new Namespace("ngapi");
    protected static NGAPI ngapi = null;

    // - Managers -
    protected EventManager eventManager; // The master EventManager.

    // - Registries -
    protected KitRegistry kitRegistry;

    @Override
    public void onEnable() {

        try {
            ngapi = this;

            // -- Registry + Manager assigns--
            this.eventManager = new EventManager();
            this.kitRegistry = new KitRegistry();


            // -- Registry + Manager primaries --
            this.eventManager.setAsPrimaryManager();
            this.kitRegistry.setAsPrimaryRegistry();


        } catch (Exception err){
            ngapi = null;
            err.printStackTrace();
            // Just making sure everything is properly nulled.
        }
    }

    public KitRegistry getKitRegistry() { return kitRegistry; }

    public static NGAPI get() { return ngapi; }
    public static boolean isNGAPILoaded() { return ngapi != null; }
}
