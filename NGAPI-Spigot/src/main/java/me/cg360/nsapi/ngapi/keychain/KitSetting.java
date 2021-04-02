package me.cg360.nsapi.ngapi.keychain;

import net.cg360.nsapi.commons.data.keyvalue.Key;

/**
 * The keychain holding all built-in supported
 * kit settings keys.
 */
public class KitSetting {

    public static final Key<Integer> COST = new Key<>("kit_cost");
    public static final Key<Boolean> SELECTOR_VISIBLE = new Key<>("is_selector_visible");

}
