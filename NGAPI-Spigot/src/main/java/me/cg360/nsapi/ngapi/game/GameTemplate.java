package me.cg360.nsapi.ngapi.game;

import me.cg360.nsapi.ngapi.NGAPI;
import net.cg360.nsapi.commons.Check;
import net.cg360.nsapi.commons.data.Settings;
import net.cg360.nsapi.commons.data.keyvalue.Key;
import net.cg360.nsapi.commons.id.Identifier;

public class GameTemplate<T extends GameBehaviour> {

    protected Identifier identifier;
    protected Class<T> behaviourClass;

    protected Settings properties;


    public GameTemplate(Identifier identifier, Class<T> behaviourClass, Settings properties) {
        Check.nullParam(identifier, "identifier");
        Check.nullParam(behaviourClass, "behaviourClass");

        this.identifier = identifier;
        this.behaviourClass = behaviourClass;

        this.properties = (properties == null ? new Settings() : properties).lock();
    }


    /** Creates a local instance of the game on the server.*/
    public SessionHandler<T> createInstance(Settings settings) {
        try {
            T inst = behaviourClass.newInstance();
            SessionHandler<T> handler = new SessionHandler<>(this, inst, settings);

            try {
                handler.initializeGame();
                //handler.initRules(); Rules will be handled on a per-stage basis

            } catch (Exception err) {
                err.printStackTrace();
                NGAPI.getLog().severe(String.format("Error whilst running the game '%s', stopping game.", identifier.getID()));
                handler.haltGame();
            }
            return handler;

        } catch (InstantiationException | IllegalAccessException err) {
            err.printStackTrace();
            return null;
        }
    }


    /** @return a typed key for this game profile. */
    public final Key<GameTemplate<T>> getKey() {
        return new Key<>(identifier);
    }

    public Identifier getIdentifier() { return identifier; }
    public Class<T> getBehaviours() { return behaviourClass; }
    public Settings getProperties() { return properties.getUnlockedCopy(); }
}
