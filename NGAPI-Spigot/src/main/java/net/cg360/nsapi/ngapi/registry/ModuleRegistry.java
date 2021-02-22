package net.cg360.nsapi.ngapi.registry;

import net.cg360.nsapi.commons.data.keyvalue.Key;
import net.cg360.nsapi.commons.data.keyvalue.Value;
import net.cg360.nsapi.commons.id.Identifier;
import net.cg360.nsapi.ngapi.modules.Module;

import java.util.HashMap;
import java.util.Optional;

public class ModuleRegistry {

    private static ModuleRegistry registryInstance;

    private HashMap<String, Value<? extends Module>> modules;

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
     * Registers a module. Does not accept already registered groups. Case-
     * insensitive.
     * @return true if group was registered.
     */
    public boolean registerModule(Module module) {
        Identifier id = module.getPackIdentifier();
        if(!modules.containsKey(id.getID())){
            modules.put(id.getID(), new Value<>(module));
            return true;
        }
        return false;
    }


    /** @return the primary instance of the Registry. */
    public static ModuleRegistry get(){
        return registryInstance;
    }

    @SuppressWarnings("unchecked") // Should be caught in the try-catch statement
    public <T extends Module> Optional<T> getModule(Key<T> key) {
        Value<? extends Module> m = modules.get(key.get());

        if(m == null) { // Not Found
            return Optional.empty();

        } else { // Found but could be the wrong type.
            Module module = m.get();

            try { // This works I guess
                return Optional.of((T) module);
            } catch (ClassCastException err) {
                return Optional.empty();
            }
        }
    }

    /** Returns the module*/
    public Optional<Module> getModule(Identifier key) {
        return getModule(new Key<>(key.getID()));
    }
}
