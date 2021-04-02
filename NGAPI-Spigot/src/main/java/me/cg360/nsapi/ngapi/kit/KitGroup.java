package me.cg360.nsapi.ngapi.kit;

import net.cg360.nsapi.commons.id.Identifier;

import java.util.HashMap;

/**
 * A group of kits that a game can reference as their
 * selection. It has a default kit that players fallback
 * to if they do not have access to other kits.
 */
public class KitGroup {

    private Identifier groupID;
    private String displayName;
    private Identifier defaultKitID;
    private boolean visibleInKitGroupSelector;
    private HashMap<Identifier, Kit> groupkits;

    public KitGroup(Identifier groupID, String displayName, boolean visibleInKitGroupSelector, Kit defaultKit, Kit... kits){
        this.groupID = groupID;
        this.displayName = displayName;
        this.visibleInKitGroupSelector = visibleInKitGroupSelector;
        this.groupkits = new HashMap<>();
        this.defaultKitID = defaultKit.getKitID();

        groupkits.put(defaultKit.getKitID(), defaultKit);
        for(Kit kit: kits){
            groupkits.put(kit.getKitID(), kit);
        }
    }

    public Identifier getGroupID() { return groupID; }
    public String getDisplayName () { return displayName; }
    public Identifier getDefaultKitID() { return defaultKitID; }
    public boolean isVisibleInKitGroupSelector () { return visibleInKitGroupSelector; }

    public Kit getDefaultKit() { return groupkits.get(defaultKitID); }

    public HashMap<Identifier, Kit> getGroupKits() {
        return new HashMap<>(groupkits);
    }

}
