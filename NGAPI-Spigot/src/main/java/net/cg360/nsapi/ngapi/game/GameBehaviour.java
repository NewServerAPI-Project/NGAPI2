package net.cg360.nsapi.ngapi.game;

import net.cg360.nsapi.commons.Check;
import net.cg360.nsapi.commons.data.Settings;
import net.cg360.nsapi.commons.event.FilteredListener;
import net.cg360.nsapi.commons.event.filter.FilterDynamicPlayerWhitelist;

public abstract class GameBehaviour {

    private SessionHandler<?> sessionHandler;
    private FilteredListener eventListener;

    protected void initialize(SessionHandler<?> sessionHandler) {
        Check.nullParam(sessionHandler, "sessionHandler");

        this.sessionHandler = sessionHandler;
        this.eventListener = new FilteredListener(this,
                new FilterDynamicPlayerWhitelist(getSessionHandler()::getViewers)
        );
        this.init(getSessionHandler().getInitSettings()); // It's more there to remind the developer that they exist.
    }

    protected abstract void init(Settings initSettings);


    public final SessionHandler<?> getSessionHandler() { return sessionHandler; }
    public final FilteredListener getEventListener() { return eventListener; }
}
