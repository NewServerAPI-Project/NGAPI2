package me.cg360.nsapi.ngapi.module.impl.devtest;

import me.cg360.nsapi.ngapi.module.Module;
import net.cg360.nsapi.commons.data.Settings;
import net.cg360.nsapi.commons.event.VanillaEvent;
import net.cg360.nsapi.commons.event.handler.EventHandler;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.util.Vector;

import java.util.Random;

// Guide:
// Create a base identifier object and reference it with ModuleContainer#getPackIdentifier() + your KEY
// Create a KEY with the class inbetween the < > being your class.
// This makes accessing more convenient for game developers.

// Key should have a similar name to the module just in-case someone extends
// the module and wants to differentiate from it.
public class ModuleKBOnCrouch extends Module {

    @Override
    protected boolean initModule(Settings settings) {
        return true;
    }

    @Override protected void onEnable() { }
    @Override protected void onDisable() { }
    @Override protected void cleanup() { }

    @EventHandler
    public void onCrouchToggle(VanillaEvent<PlayerToggleSneakEvent> event) {

        if(event.getWrappedEvent().isSneaking()) { // Note how there's no need to check the player!
            Random random = new Random();
            Vector vec = new Vector(random.nextFloat()-0.5f, random.nextFloat()/2f,random.nextFloat()-0.5f);
            event.getWrappedEvent().getPlayer().setVelocity(vec.normalize().multiply(2f));
        }
    }

}
