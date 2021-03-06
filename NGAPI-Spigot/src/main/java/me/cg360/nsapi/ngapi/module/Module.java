package me.cg360.nsapi.ngapi.module;

import me.cg360.nsapi.ngapi.game.SessionHandler;
import net.cg360.nsapi.commons.Check;
import net.cg360.nsapi.commons.data.Settings;
import net.cg360.nsapi.commons.event.FilteredListener;
import net.cg360.nsapi.commons.event.filter.EventFilter;

/**
 * A reusable collection of behaviours that can be used
 * across multiple game types and stages.
 */
public abstract class Module {

    private boolean isInitialized = false;
    private boolean isEnabled = false;

    protected Settings settings;
    private SessionHandler<?> sessionHandler;
    private ModuleContainer<? extends Module> container;

    protected FilteredListener listener;


    protected final void init(Settings settings, SessionHandler<?> sessionHandler, ModuleContainer<? extends Module> container) {
        Check.nullParam(sessionHandler, "sessionHandler");
        Check.nullParam(container, "container");

        this.settings = settings;
        this.sessionHandler = sessionHandler;
        this.container = container;

        this.listener = new FilteredListener(
                this,
                sessionHandler.getEventListener().getFilters().toArray(new EventFilter[0]) // Copy SessionHandler's listeners.
        );

        this.isInitialized = this.initModule((settings == null ? new Settings() : settings).lock());
    }


    /** Called once, it's triggered before the first onEnable. */
    protected abstract boolean initModule(Settings settings);

    /** Triggered whenever the feature is enabled. */
    protected abstract void onEnable();

    /** Triggered whenever the feature is disabled. */
    protected abstract void onDisable();

    /** Triggered when the game is cleaning up. */
    protected abstract void cleanup();



    protected final boolean enableModule() {
        if(this.isInitialized && (!this.isEnabled) ) {
            onEnable();
            this.isEnabled = true;
            return true;
        }
        return false;
    }

    protected final boolean disableModule() {
        if(this.isEnabled) {
            onDisable();
            this.isEnabled = false;
            return true;
        }
        return false;
    }



    public final boolean isInitialized() { return isInitialized; }
    public final boolean isEnabled() { return isEnabled; }

    public Settings getSettings() { return settings; }
    public final SessionHandler<?> getSessionHandler() { return sessionHandler; }
    public final ModuleContainer<? extends Module> getContainer() { return container; }

    public FilteredListener getListener() { return listener; }

    /** Sets the module's settings. */
    protected void setSettings(Settings settings) {
        this.settings = settings;
    }
}
