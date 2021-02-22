package net.cg360.nsapi.ngapi.modules;

import net.cg360.nsapi.commons.data.Settings;
import net.cg360.nsapi.commons.id.Identifier;

public abstract class Module {

    private boolean isInitialized = false;


    public final void init(Settings settings) {

    }


    /** Called once, it's triggered before the first onEnable. */
    protected abstract void initModule(Settings settings);

    /** Triggered whenever the feature is enabled. */
    protected abstract void onEnable();

    /** Triggered whenever the feature is disabled. */
    protected abstract void onDisable();

    /** Triggered when the game is cleaning up. */
    protected abstract void cleanup();


    /** Get the module's identifier. */
    public abstract Identifier getPackIdentifier();

    public boolean isInitialized() { return isInitialized; }
}
