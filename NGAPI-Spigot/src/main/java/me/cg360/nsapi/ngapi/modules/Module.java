package me.cg360.nsapi.ngapi.modules;

import net.cg360.nsapi.commons.data.Settings;

public abstract class Module {

    private boolean isInitialized = false;

    protected Settings settings;
    protected ModuleContainer<? extends Module> container;


    protected final void init(Settings settings, ModuleContainer<? extends Module> container) {
        this.settings = settings;
        this.container = container;
        this.isInitialized = this.initModule(settings, container);
    }


    /** Called once, it's triggered before the first onEnable. */
    protected abstract boolean initModule(Settings settings, ModuleContainer<? extends Module> container);

    /** Triggered whenever the feature is enabled. */
    protected abstract void onEnable();

    /** Triggered whenever the feature is disabled. */
    protected abstract void onDisable();

    /** Triggered when the game is cleaning up. */
    protected abstract void cleanup();


    /** Sets the module's settings. */
    protected void setSettings(Settings settings) {
        this.settings = settings;
    }

    public boolean isInitialized() { return isInitialized; }
    public Settings getSettings() { return settings; }
    public ModuleContainer<? extends Module> getContainer() { return container; }
}