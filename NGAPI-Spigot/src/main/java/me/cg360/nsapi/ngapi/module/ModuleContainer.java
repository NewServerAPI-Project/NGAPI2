package me.cg360.nsapi.ngapi.module;

import me.cg360.nsapi.ngapi.game.SessionHandler;
import net.cg360.nsapi.commons.Check;
import net.cg360.nsapi.commons.Immutable;
import net.cg360.nsapi.commons.data.Settings;
import net.cg360.nsapi.commons.data.keyvalue.IdentityKey;
import net.cg360.nsapi.commons.id.Identifier;
import me.cg360.nsapi.ngapi.NGAPI;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public final class ModuleContainer<T extends Module> {

    private final Identifier identifier;
    private final Class<T> module;
    private final List<Identifier> dependencies;


    public ModuleContainer(IdentityKey<T> identifier, Class<T> module, Identifier... dependencies) {
        this(identifier.get(), module, dependencies);
    }

    public ModuleContainer(Identifier identifier, Class<T> module, Identifier... dependencies) {
        Check.nullParam(identifier, "Module Identifier");
        Check.nullParam(module, "Module Class");

        this.identifier = identifier;
        this.module = module;
        this.dependencies = Immutable.uList(Arrays.asList(dependencies == null ? new Identifier[0] : dependencies), true);
    }


    /**
     * Called when a module is first imported/required in a
     * game. This is often a while before it is used, thus it
     * should only be used for initial setup by the ModuleLoader.
     * @param settings any settings that should be passed through to the module.
     * @return an optional of the module. Empty if something went wrong.
     */
    protected Optional<T> initializeModule(Settings settings, SessionHandler<?> sessionHandler) {

        try {
            T m = module.newInstance();
            m.init(settings, sessionHandler, this); // Ensure settings are locked.
            return Optional.of(m);

        } catch (Exception err) {
            NGAPI.getLog().severe(" !!! Unable to initialize module: "+identifier.getID());
            err.printStackTrace();
            return Optional.empty();
        }
    }


    /** @return  the module's identifier. */
    public Identifier getIdentifier() {
        return this.identifier;
    }

    /** @return the class of the module encased by this container. */
    public Class<T> getModuleClass() {
        return module;
    }

    /** @return the ids of other modules that this module depends on. */
    public List<Identifier> getDependencies() {
        return dependencies;
        // Any Module loading implementation should try and enable these first.
        // Beware of cycles!
    }
}
