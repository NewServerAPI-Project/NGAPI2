package me.cg360.nsapi.ngapi.exception;

import net.cg360.nsapi.commons.id.Identifier;

public class BrokenComponentException extends RuntimeException {

    public BrokenComponentException(String system, Identifier componentName) {
        super(String.format("Unable to initialize the %s component '%s'. It's broken :')", system, componentName.getID()));
    }
}
