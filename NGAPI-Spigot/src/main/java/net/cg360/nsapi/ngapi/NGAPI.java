package net.cg360.nsapi.ngapi;

import net.cg360.nsapi.commons.event.EventManager;
import net.cg360.nsapi.commons.id.Namespace;
import net.cg360.nsapi.ngapi.kit.impl.basic.KitCookie;
import net.cg360.nsapi.ngapi.modules.ModuleContainer;
import net.cg360.nsapi.ngapi.modules.impl.devtest.ModuleKBOnCrouch;
import net.cg360.nsapi.ngapi.registry.KitRegistry;
import net.cg360.nsapi.ngapi.registry.ModuleRegistry;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * NGAPI 2.0 is a minigame api created for Spigot! Based
 * on the
 */
public class NGAPI extends JavaPlugin implements Listener {

    public static final Namespace NAME = new Namespace("ngapi");
    protected static NGAPI ngapi = null;

    // - Managers -
    protected EventManager eventManager; // The master EventManager.

    // - Registries -
    protected ModuleRegistry moduleRegistry;
    protected KitRegistry kitRegistry;

    @Override
    public void onEnable() {

        try {
            ngapi = this;

            // -- Registry + Manager assigns--
            this.eventManager = new EventManager();

            this.moduleRegistry = new ModuleRegistry();
            this.kitRegistry = new KitRegistry();


            // -- Registry + Manager primaries --
            this.eventManager.setAsPrimaryManager();

            this.moduleRegistry.setAsPrimaryRegistry();
            this.kitRegistry.setAsPrimaryRegistry();


            // -- API Config + Defaults --

            this.initModules();

            // -- Server Stuff --
            this.getServer().getPluginManager().registerEvents(this, this);

        } catch (Exception err){
            ngapi = null;
            err.printStackTrace();
            // Just making sure everything is properly nulled.
        }
    }


    protected void initModules() {
        getModuleRegistry()
                .register(new ModuleContainer<>(ModuleKBOnCrouch.ID, ModuleKBOnCrouch.class))
        ;
    }



    public static ModuleRegistry getModuleRegistry() { return get().moduleRegistry; }
    public static KitRegistry getKitRegistry() { return get().kitRegistry; }
    public static Logger getLog() { return get().getLogger(); }

    public static NGAPI get() { return ngapi; }
    public static boolean isNGAPILoaded() { return ngapi != null; }
}
