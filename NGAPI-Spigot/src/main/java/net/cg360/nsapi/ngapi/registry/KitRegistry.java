package net.cg360.nsapi.ngapi.registry;

import net.cg360.nsapi.commons.id.Identifier;
import net.cg360.nsapi.ngapi.NGAPI;
import net.cg360.nsapi.ngapi.kit.KitGroup;

import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

public class KitRegistry {

    //TODO: Set default kit
    public static final KitGroup DEFAULT = new KitGroup(NGAPI.NAME.id("default"), "Default", false, null);
    private static KitRegistry registryInstance;

    private HashMap<Identifier, KitGroup> kitgroups;

    public KitRegistry(){
        this.kitgroups = new HashMap<>();
        kitgroups.put(NGAPI.NAME.id("default"), DEFAULT);
    }

    /**
     * Makes the registry the result provided from KitRegistry#get() and
     * finalizes the instance to an extent.
     */
    public void setAsPrimaryRegistry(){
        if(registryInstance == null) registryInstance = this;
    }

    /**
     * Registers a kit group. Does not accept already registered groups. Case-
     * insensitive.
     * @return true if group was registered.
     */
    public boolean registerKitGroup(KitGroup group) {
        Identifier id = group.getGroupID();
        if(!kitgroups.containsKey(id)){
            kitgroups.put(id, group);
            // KitGroups no longer call #onRegister() for each kit.
            // This was never used in NGAPI 1.0 anyway.
            return true;
        }
        return false;
    }

    /** @return the primary instance of the Registry. */
    public static KitRegistry get(){
        return registryInstance;
    }

    /**
     * Gets a registered kit group.
     * @param id - The ID of the group
     * @return an optional. Check presence with Optional#ifPresent()
     */
    public Optional<KitGroup> getKitGroup(Identifier id) {
        return Optional.ofNullable(kitgroups.get(id));
    }

    public Set<Identifier> getAllKitGroups() { return kitgroups.keySet(); }
}
