package me.cg360.nsapi.ngapi.game;

import me.cg360.nsapi.ngapi.NGAPI;
import net.cg360.nsapi.commons.Check;
import net.cg360.nsapi.commons.SpigotCommons;
import net.cg360.nsapi.commons.data.Settings;
import me.cg360.nsapi.ngapi.modules.ModuleLoader;
import net.cg360.nsapi.commons.event.FilteredListener;
import net.cg360.nsapi.commons.event.filter.FilterDynamicPlayerWhitelist;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * The base engine of all game instances. This runs each
 * GameBehaviour and it's modules in it's own environment.
 */
public class SessionHandler<T extends GameBehaviour> {

    protected GameTemplate<T> template;
    protected T behaviour;
    protected Settings initSettings;
    protected SessionState sessionState;

    protected FilteredListener eventListener;
    protected ModuleLoader moduleLoader;

    protected ArrayList<Player> participants;
    protected ArrayList<Player> spectators;

    // -- Core lifecycle methods --
    // Starting, stopping, etc.

    protected SessionHandler(GameTemplate<T> template, T behaviour, Settings initSettings) {
        Check.nullParam(template, "template");
        Check.nullParam(behaviour, "behaviour");

        this.template = template;
        this.behaviour = behaviour;
        this.initSettings = (initSettings == null ? new Settings() : initSettings).lock();

        // Any empty structures might as well be put here.
        // Stuff that requires a reference back to the SessionHandler or
        // proper logic should go in #initializeGame()
        this.participants = new ArrayList<>();
        this.spectators = new ArrayList<>();

        this.sessionState = SessionState.PENDING;
    }

    public void initializeGame() {
        if(sessionState == SessionState.PENDING) {
            this.sessionState = SessionState.ACTIVE;

            //TODO: Add Scheduler first.
            this.moduleLoader = new ModuleLoader(this);
            this.eventListener = new FilteredListener(this,
                    new FilterDynamicPlayerWhitelist(this::getViewers)
            );

            behaviour.initialize(this);
            NGAPI.getEventManager().addListener(eventListener);
        }
    }

    public void haltGame() {
        if(this.sessionState == SessionState.ACTIVE) {
            this.sessionState = SessionState.INACTIVE;

            behaviour.halt();
            NGAPI.getEventManager().removeListener(eventListener);

            this.moduleLoader.disableAll();
            //TODO: Remove Scheduler last
        }
    }



    /** @return the template this game session was created from. */
    public GameTemplate<T> getTemplate() { return template; }
    /** @return the behaviour instance used within this game session. */
    public T getBehaviour() { return behaviour; }
    /** @return the settings used to initialize the game session. */
    public Settings getInitSettings() { return initSettings; }
    /** @return the current state of the SessionHandler. */
    public SessionState getSessionState() { return sessionState; }

    /** @return the game session's filtered event listener. */
    public FilteredListener getEventListener() { return eventListener; }
    /** @return the game session's module loader. */
    public ModuleLoader getModuleLoader() { return moduleLoader; }

    /** @return a list of players that are playing within the current session. */
    public ArrayList<Player> getParticipants() { return new ArrayList<>(participants); }
    /** @return a list of players that do not have an active role in the current session. */
    public ArrayList<Player> getSpectators() { return new ArrayList<>(spectators); }

    /** @return a combined list of players and spectators. Anyone that can see the game is included. */
    public Player[] getViewers () {
        ArrayList<Player> targetList = new ArrayList<>();
        targetList.addAll(getParticipants());
        targetList.addAll(getSpectators());
        return targetList.toArray(new Player[0]);
    }



    public enum SessionState {
        ACTIVE, // Game + Stages are running
        PENDING, // Hasn't been fully initialized
        INACTIVE // Game has been concluded or terminated
    }
}
