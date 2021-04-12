package me.cg360.nsapi.ngapi.stage;

import me.cg360.nsapi.ngapi.NGAPI;
import me.cg360.nsapi.ngapi.game.SessionHandler;
import net.cg360.nsapi.commons.Check;
import net.cg360.nsapi.commons.data.Settings;
import net.cg360.nsapi.commons.data.keyvalue.IdentityKey;
import net.cg360.nsapi.commons.id.Identifier;

import java.util.Optional;

public final class StageContainer<T extends Stage> {

    private final Identifier identifier;
    private final Class<T> module;
    private final StageType lifecycleType;


    public StageContainer(IdentityKey<T> identifier, Class<T> module, StageType lifecycleType) {
        this(identifier.get(), module, lifecycleType);
    }

    public StageContainer(Identifier identifier, Class<T> module, StageType lifecycleType) {
        Check.nullParam(identifier, "Module Identifier");
        Check.nullParam(module, "Module Class");
        Check.nullParam(lifecycleType, "Lifecycle Type");

        this.identifier = identifier;
        this.module = module;
        this.lifecycleType = lifecycleType;
    }


    /**
     * Called when a stage is prepared by a StageManager.
     * @param settings any settings that should be passed through to the module.
     * @return an optional of the module. Empty if something went wrong.
     */
    protected Optional<T> initializeInstance(Settings settings, SessionHandler<?> sessionHandler) {

        try {
            T m = module.newInstance();
            m.init(settings.lock(), sessionHandler, this); // Ensure settings are locked.
            return Optional.of(m);

        } catch (Exception err) {
            NGAPI.getLog().severe(" !!! Unable to initialize stage: "+identifier.getID());
            err.printStackTrace();
            return Optional.empty();
        }
    }


    /** @return  the stage type's identifier. */
    public Identifier getIdentifier() {
        return this.identifier;
    }

    /** @return the class of the stage type encased by this container. */
    public Class<T> getModuleClass() {
        return module;
    }

    /** @return the type of lifecycle stage this contains. */
    public StageType getLifecycleType() {
        return lifecycleType;
    }
}
