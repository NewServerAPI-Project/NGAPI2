package me.cg360.nsapi.ngapi.keychain;

import net.cg360.nsapi.commons.data.keyvalue.IdentityKey;
import net.cg360.nsapi.commons.id.Identifier;
import me.cg360.nsapi.ngapi.module.impl.devtest.ModuleKBOnCrouch;

public class NGAPIModule {

    public static final IdentityKey<ModuleKBOnCrouch> MODKEY_KB_ON_CROUCH = new IdentityKey<>(new Identifier("devtest", "kb_on_crouch"));

}
