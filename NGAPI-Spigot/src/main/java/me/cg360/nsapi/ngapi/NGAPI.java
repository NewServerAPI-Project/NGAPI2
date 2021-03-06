package me.cg360.nsapi.ngapi;

import me.cg360.nsapi.ngapi.module.ModuleContainer;
import me.cg360.nsapi.ngapi.registry.StageRegistry;
import net.cg360.nsapi.commons.SpigotCommons;
import net.cg360.nsapi.commons.event.EventManager;
import net.cg360.nsapi.commons.id.Namespace;
import me.cg360.nsapi.ngapi.keychain.NGAPIModule;
import me.cg360.nsapi.ngapi.module.impl.devtest.ModuleKBOnCrouch;
import me.cg360.nsapi.ngapi.registry.KitRegistry;
import me.cg360.nsapi.ngapi.registry.ModuleRegistry;
import org.bukkit.event.Listener;
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
    protected StageRegistry stageRegistry;
    protected KitRegistry kitRegistry;

    @Override
    public void onEnable() {

        try {
            ngapi = this;

            // -- Registry + Manager assigns--
            this.eventManager = new EventManager();

            this.moduleRegistry = new ModuleRegistry();
            this.stageRegistry = new StageRegistry();
            this.kitRegistry = new KitRegistry();


            // -- Registry + Manager primaries --
            this.eventManager.setAsPrimaryManager();

            this.moduleRegistry.setAsPrimaryRegistry();
            this.stageRegistry.setAsPrimaryRegistry();
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
                .register(new ModuleContainer<>(NGAPIModule.MODKEY_KB_ON_CROUCH, ModuleKBOnCrouch.class))
        ;
    }



    public static ModuleRegistry getModuleRegistry() { return get().moduleRegistry; }
    public static StageRegistry getStageRegistry() { return get().stageRegistry; }
    public static KitRegistry getKitRegistry() { return get().kitRegistry; }
    public static Logger getLog() { return get().getLogger(); }
    public static EventManager getEventManager() { return SpigotCommons.getEventManager(); } // Replace in nukkit port.

    public static NGAPI get() { return ngapi; }
    public static boolean isNGAPILoaded() { return ngapi != null; }
}
