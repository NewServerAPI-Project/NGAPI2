package me.cg360.nsapi.ngapi.stage;

import me.cg360.nsapi.ngapi.game.SessionHandler;
import net.cg360.nsapi.commons.data.Settings;

public abstract class Stage {

    /** Used to prepare any data or configuration for the stage once the game session is prepared. */
    protected abstract void init(Settings initSettings, SessionHandler<?> sessionHandler, StageContainer<? extends Stage> stageContainer);

    /** Excecuted when the stage starts. */
    protected abstract void onStageStart();

    /**
     * May be executed externally or internally, should clean up the stage's
     * actions and ensure the current state keys are updated.
     */
    protected abstract void onStageStop();

}
