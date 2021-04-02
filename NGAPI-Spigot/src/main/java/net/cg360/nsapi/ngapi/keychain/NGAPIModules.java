package net.cg360.nsapi.ngapi.keychain;

import net.cg360.nsapi.commons.data.keyvalue.IdentityKey;
import net.cg360.nsapi.commons.data.keyvalue.Key;
import net.cg360.nsapi.commons.id.Identifier;
import net.cg360.nsapi.ngapi.modules.impl.devtest.ModuleKBOnCrouch;

public class NGAPIModules {

    public static final IdentityKey<ModuleKBOnCrouch> MODKEY_KB_ON_CROUCH = new IdentityKey<>(new Identifier("devtest", "kb_on_crouch"));

}
