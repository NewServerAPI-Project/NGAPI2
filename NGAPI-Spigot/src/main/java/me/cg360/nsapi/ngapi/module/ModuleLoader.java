package me.cg360.nsapi.ngapi.module;

import me.cg360.nsapi.ngapi.game.SessionHandler;

public class ModuleLoader {

    protected SessionHandler<?> sessionHandler;

    public ModuleLoader(SessionHandler<?> sessionHandler) {
        this.sessionHandler = sessionHandler;
    }


    public void disableAll() {

    }

}
