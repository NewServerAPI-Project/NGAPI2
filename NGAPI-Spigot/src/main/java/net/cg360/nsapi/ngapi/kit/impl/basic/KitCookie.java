package net.cg360.nsapi.ngapi.kit.impl.basic;

import net.cg360.nsapi.commons.data.Settings;
import net.cg360.nsapi.commons.id.Identifier;
import net.cg360.nsapi.ngapi.NGAPI;
import net.cg360.nsapi.ngapi.keys.KitSetting;
import net.cg360.nsapi.ngapi.kit.Kit;
import net.cg360.nsapi.ngapi.kit.KitBehaviour;

import java.util.Optional;

public class KitCookie extends Kit {

    @Override
    protected Settings genKitSettings() {
        return new Settings()
                .set(KitSetting.COST, 1000)
                .set(KitSetting.SELECTOR_VISIBLE, true);
    }

    @Override
    public Identifier getKitID() {
        return NGAPI.NAME.id("cookie_monster");
    }

    @Override
    public String getKitDisplayName() {
        return "Cookie Critter";
    }

    @Override
    public String getKitDescription() {
        return "*Agressive Non-Copyright Cookie Eating*";
    }

    @Override
    public Optional<Class<? extends KitBehaviour>> getKitBehaviour() {
        return Optional.empty();
    }
}
