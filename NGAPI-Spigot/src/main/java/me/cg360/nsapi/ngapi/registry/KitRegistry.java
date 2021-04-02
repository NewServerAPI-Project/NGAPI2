package me.cg360.nsapi.ngapi.registry;

import me.cg360.nsapi.ngapi.NGAPI;
import me.cg360.nsapi.ngapi.kit.KitGroup;
import me.cg360.nsapi.ngapi.kit.impl.basic.KitCookie;
import me.cg360.nsapi.ngapi.kit.impl.core.KitEmpty;
import net.cg360.nsapi.commons.id.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class KitRegistry {

    //TODO: Set default kit
    public static final KitGroup DEFAULT = new KitGroup(NGAPI.NAME.id("default"), "Basic Kits", true, new KitCookie());
    public static final KitGroup CORE = new KitGroup(NGAPI.NAME.id("core"), "Core Kit Group", false, new KitEmpty());
    private static KitRegistry registryInstance;

    private HashMap<Identifier, KitGroup> kitgroups;

    public KitRegistry(){
        this.kitgroups = new HashMap<>();
        registerKitGroup(DEFAULT);
        registerKitGroup(CORE);
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

    public ArrayList<Identifier> getAllKitGroups() { return new ArrayList<>(kitgroups.keySet()); }
}
