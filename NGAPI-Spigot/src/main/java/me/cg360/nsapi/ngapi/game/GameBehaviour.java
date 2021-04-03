package me.cg360.nsapi.ngapi.game;

import net.cg360.nsapi.commons.Check;
import net.cg360.nsapi.commons.data.Settings;
import net.cg360.nsapi.commons.event.FilteredListener;
import net.cg360.nsapi.commons.event.filter.FilterDynamicPlayerWhitelist;

public abstract class GameBehaviour {

    private SessionHandler<?> sessionHandler;

    protected final void initialize(SessionHandler<?> sessionHandler) {
        Check.nullParam(sessionHandler, "sessionHandler");

        this.sessionHandler = sessionHandler;
        this.init(getSessionHandler().getInitSettings()); // Settings are more there to remind the developer that they exist.
    }

    protected abstract void init(Settings initSettings);

    /** Stops any running features of the game and cleans up any remaining content
     * that is not managed by the API. */
    protected abstract void halt();


    public final SessionHandler<?> getSessionHandler() { return sessionHandler; }
}
