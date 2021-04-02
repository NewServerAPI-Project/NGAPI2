package net.cg360.nsapi.ngapi.modules.impl.devtest;

import net.cg360.nsapi.commons.data.Settings;
import net.cg360.nsapi.ngapi.modules.Module;
import net.cg360.nsapi.ngapi.modules.ModuleContainer;

public class ModuleKBOnCrouch extends Module {

    // Guide:
    // Create a base identifier object and reference it with ModuleContainer#getPackIdentifier() + your KEY
    // Create a KEY with the class inbetween the < > being your class.
    // This makes accessing more convenient for game developers.

    // Key should have a similar name to the module just in-case someone extends
    // the module and wants to differentiate from it.

    @Override
    protected boolean initModule(Settings settings, ModuleContainer<? extends Module> container) {

    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    @Override
    protected void cleanup() {

    }

}
