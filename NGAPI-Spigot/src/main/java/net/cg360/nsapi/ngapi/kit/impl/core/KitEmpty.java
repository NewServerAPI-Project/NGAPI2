package net.cg360.nsapi.ngapi.kit.impl.core;

import net.cg360.nsapi.commons.data.Settings;
import net.cg360.nsapi.commons.id.Identifier;
import net.cg360.nsapi.ngapi.NGAPI;
import net.cg360.nsapi.ngapi.keys.KitSetting;
import net.cg360.nsapi.ngapi.kit.Kit;
import net.cg360.nsapi.ngapi.kit.KitBehaviour;

import java.util.Optional;

/**
 * Internal kit used for empty inventories.
 */
public class KitEmpty extends Kit {

    @Override
    protected Settings genKitSettings() {
        return new Settings()
                .set(KitSetting.COST, 0)
                .set(KitSetting.SELECTOR_VISIBLE, false);
    }

    @Override
    public Identifier getKitID() {
        return NGAPI.NAME.id("empty");
    }

    @Override
    public String getKitDisplayName() {
        return "Empty";
    }

    @Override
    public String getKitDescription() {
        return "Somehow you've found this description for a kit that technically doesn't exist depending on who" +
                "you ask. Congrats I guess. \n-CG360";
    }

    @Override
    public Optional<Class<? extends KitBehaviour>> getKitBehaviour() {
        return Optional.empty();
    }

}
