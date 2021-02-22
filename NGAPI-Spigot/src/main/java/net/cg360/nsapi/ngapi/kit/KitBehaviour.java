package net.cg360.nsapi.ngapi.kit;

import net.cg360.nsapi.commons.event.handler.EventHandler;

public class KitBehaviour {

    private Kit parentKit;


    public KitBehaviour(Kit parentKit) {
        this.parentKit = parentKit;
    }

    @EventHandler
    public void onAssignKit() {
        // This is just here to point people that live off autocomplete in the right direction.
    }

    public Kit getParentKit() { return parentKit; }
}
