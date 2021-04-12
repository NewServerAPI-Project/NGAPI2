package me.cg360.nsapi.ngapi.registry;

import me.cg360.nsapi.ngapi.module.Module;
import me.cg360.nsapi.ngapi.module.ModuleContainer;
import net.cg360.nsapi.commons.data.keyvalue.Key;
import net.cg360.nsapi.commons.id.Identifier;

import java.util.HashMap;
import java.util.Optional;

public class ModuleRegistry {

    private static ModuleRegistry registryInstance;

    private HashMap<String, ModuleContainer<? extends Module>> modules;

    public ModuleRegistry(){
        this.modules = new HashMap<>();
    }

    /**
     * Makes the registry the result provided from ModuleRegistry#get() and
     * finalizes the instance to an extent.
     */
    public void setAsPrimaryRegistry(){
        if(registryInstance == null) registryInstance = this;
    }

    /**
     * Registers a module. Does not accept already registered groups.
     * @return true if group was registered.
     */
    public boolean registerModule(ModuleContainer<? extends Module> module) {
        Identifier id = module.getIdentifier();

        if(!modules.containsKey(id.getID())){
            modules.put(id.getID(), module);
            return true;
        }
        return false;
    }

    /**
     * Registers a module. Does not accept already registered groups.
     * @return self for chaining.
     */
    public ModuleRegistry register(ModuleContainer<? extends Module> module) {
        registerModule(module);
        return this;
    }


    /** @return the primary instance of the Registry. */
    public static ModuleRegistry get(){
        return registryInstance;
    }

    @SuppressWarnings("unchecked") // Should be caught in the try-catch statement
    public <T extends Module> Optional<ModuleContainer<T>> getModule(Key<T> key) {
        ModuleContainer<? extends Module> m = modules.get(key.get());

        if(m == null) { // Not Found
            return Optional.empty();

        } else { // Found but could be the wrong type.

            try { // This works I guess
                return Optional.of((ModuleContainer<T>) m);

            } catch (ClassCastException err) {
                return Optional.empty();
            }
        }
    }

    /** Returns the module*/
    public Optional<ModuleContainer<Module>> getModule(Identifier key) {
        return getModule(new Key<>(key.getID()));
    }
}
