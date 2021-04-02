package net.cg360.nsapi.ngapi.game;

import net.cg360.nsapi.commons.Check;
import net.cg360.nsapi.commons.data.Settings;
import net.cg360.nsapi.ngapi.modules.ModuleLoader;
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

    protected ModuleLoader moduleLoader;

    protected ArrayList<Player> participants;
    protected ArrayList<Player> spectators;



    protected SessionHandler(GameTemplate<T> template, T behaviour, Settings initSettings) {
        Check.nullParam(template, "template");
        Check.nullParam(behaviour, "behaviour");

        this.template = template;
        this.behaviour = behaviour;
        this.initSettings = (initSettings == null ? new Settings() : initSettings).lock();
    }



    /** @return the template this game session was created from. */
    public GameTemplate<T> getTemplate() { return template; }
    /** @return the behaviour instance used within this game session. */
    public T getBehaviour() { return behaviour; }
    /** @return the settings used to initialize the game session. */
    public Settings getInitSettings() { return initSettings; }

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
}
