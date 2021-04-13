package me.cg360.nsapi.ngapi.stage;

import me.cg360.nsapi.ngapi.game.SessionHandler;
import net.cg360.nsapi.commons.Check;
import net.cg360.nsapi.commons.data.Settings;

import java.util.Objects;

/**
 * A block instantiated during a sessions' operation that can be used by
 * multiple game types or server structures to implement a set of behaviours
 * prior to or after a game's operation.
 */
public abstract class Stage {

    private StageManager hostManager;
    private StageContainer<? extends Stage> stageContainer;

    private boolean isInitialized;
    private boolean isRunning;


    protected final void init(Settings initSettings, StageManager hostManager, StageContainer<? extends Stage> stageContainer) {
        Check.nullParam(hostManager, "hostManager");
        Check.nullParam(stageContainer, "stageContainer");

        this.stageContainer = stageContainer;
        this.hostManager = hostManager;

        this.isRunning = false;
        this.isInitialized = initStage(initSettings.lock());
    }


    /** Used to prepare any data or configuration for the stage once the game session is prepared. */
    protected abstract boolean initStage(Settings initSettings);

    /** Executed when the stage starts. */
    protected abstract void onStageStart();

    /**
     * May be executed externally or internally, should clean up the stage's
     * actions and ensure the current state keys are updated.
     */
    protected abstract void onStageStop();



    public final StageManager getHostManager() { return hostManager; }
    public final StageContainer<? extends Stage> getStageContainer() { return stageContainer; }

    public final boolean isInitialized() { return isInitialized; }
    public final boolean isRunning() { return isRunning; }
}
