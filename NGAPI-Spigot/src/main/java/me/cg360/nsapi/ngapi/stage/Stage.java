package me.cg360.nsapi.ngapi.stage;

import me.cg360.nsapi.ngapi.game.SessionHandler;
import net.cg360.nsapi.commons.data.Settings;

/**
 * A block instantiated during a sessions' operation that can be used by
 * multiple game types or server structures to implement a set of behaviours
 * prior to or after a game's operation.
 */
public abstract class Stage {

    private StageContainer<? extends Stage> container;
    private StageManager hostManager;

    /** Used to prepare any data or configuration for the stage once the game session is prepared. */
    protected abstract void init(Settings initSettings, SessionHandler<?> sessionHandler, StageContainer<? extends Stage> stageContainer);

    /** Executed when the stage starts. */
    protected abstract void onStageStart();

    /**
     * May be executed externally or internally, should clean up the stage's
     * actions and ensure the current state keys are updated.
     */
    protected abstract void onStageStop();


    public final StageContainer<? extends Stage> getContainer() { return container; }
    public final StageManager getHostManager() {
        return hostManager;
    }
}
