package me.cg360.nsapi.ngapi.exception;

import net.cg360.nsapi.commons.id.Identifier;

public class MissingComponentException extends RuntimeException {

    public MissingComponentException(String system, Identifier componentName) {
        super(String.format("Unable to acquire the %s component '%s'. Is it registered?", system, componentName.getID()));
    }
}
