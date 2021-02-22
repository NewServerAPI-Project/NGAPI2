package net.cg360.nsapi.ngapi.modules;

import net.cg360.nsapi.commons.id.Identifier;

public final class ModuleContainer<T extends Module> {

    private Identifier identifier;
    private Class<T> module;

    public ModuleContainer(Identifier identifier, Class<T> module) {

    }


    /** Get the module's identifier. */
    public Identifier getPackIdentifier() {
        return this.identifier;
    }

}
