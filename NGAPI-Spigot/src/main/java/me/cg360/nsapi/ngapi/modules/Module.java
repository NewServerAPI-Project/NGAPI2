package me.cg360.nsapi.ngapi.modules;

import me.cg360.nsapi.ngapi.game.SessionHandler;
import net.cg360.nsapi.commons.Check;
import net.cg360.nsapi.commons.data.Settings;
import net.cg360.nsapi.commons.event.FilteredListener;
import net.cg360.nsapi.commons.event.filter.EventFilter;
import net.cg360.nsapi.commons.event.filter.FilterDynamicPlayerWhitelist;

public abstract class Module {

    private boolean isInitialized = false;

    protected Settings settings;
    protected SessionHandler<?> sessionHandler;
    protected ModuleContainer<? extends Module> container;

    protected FilteredListener listener;


    protected final void init(Settings settings, SessionHandler<?> sessionHandler, ModuleContainer<? extends Module> container) {
        Check.nullParam(settings, "settings");
        Check.nullParam(sessionHandler, "sessionHandler");
        Check.nullParam(container, "container");

        this.settings = settings;
        this.sessionHandler = sessionHandler;
        this.container = container;

        this.listener = new FilteredListener(
                this,
                sessionHandler.getEventListener().getFilters().toArray(new EventFilter[0]) // Copy SessionHandler's listeners.
        );

        this.isInitialized = this.initModule(settings);
    }


    /** Called once, it's triggered before the first onEnable. */
    protected abstract boolean initModule(Settings settings);

    /** Triggered whenever the feature is enabled. */
    protected abstract void onEnable();

    /** Triggered whenever the feature is disabled. */
    protected abstract void onDisable();

    /** Triggered when the game is cleaning up. */
    protected abstract void cleanup();



    public boolean isInitialized() { return isInitialized; }
    public Settings getSettings() { return settings; }
    public ModuleContainer<? extends Module> getContainer() { return container; }
    public FilteredListener getListener() { return listener; }

    /** Sets the module's settings. */
    protected void setSettings(Settings settings) {
        this.settings = settings;
    }
}
