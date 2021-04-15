package me.cg360.nsapi.ngapi.stage;

import me.cg360.nsapi.ngapi.exception.BrokenComponentException;
import me.cg360.nsapi.ngapi.exception.MissingComponentException;
import me.cg360.nsapi.ngapi.game.SessionHandler;
import me.cg360.nsapi.ngapi.registry.StageRegistry;
import net.cg360.nsapi.commons.Check;
import net.cg360.nsapi.commons.data.Settings;
import net.cg360.nsapi.commons.data.keyvalue.IdentityKey;
import net.cg360.nsapi.commons.id.Identifier;

import java.util.ArrayList;
import java.util.Optional;

public class StageManager {

    protected SessionHandler<?> sessionHandler;

    protected StageContainer<?> activeStageContainer;
    protected Stage activeStage;

    protected ArrayList<StageContainer<? extends Stage>> queueContainers; // Only used to check for duplicates. Retains it's original order.
    protected ArrayList<Stage> stageQueue;


    public StageManager(SessionHandler<?> sessionHandler) {
        Check.nullParam(sessionHandler, "sessionHandler");

        this.activeStageContainer = null;
        this.activeStage = null;

        this.queueContainers = new ArrayList<>();
        this.stageQueue = new ArrayList<>();
    }



    protected boolean includeStage(IdentityKey<? extends Stage> identifier) { return includeStage(null, identifier.get()); }
    protected boolean includeStage(Identifier identifier) { return includeStage(null, identifier); }


    protected boolean includeStage(Settings initSettings, IdentityKey<? extends Stage> identifier) {
        return includeStage(initSettings, identifier.get());
    }


    /** @return false if it was already included. */
    protected boolean includeStage(Settings initSettings, Identifier identifier) {
        Optional<StageContainer<Stage>> optStageType = StageRegistry.get().getStageType(identifier);

        if(!optStageType.isPresent()) throw new MissingComponentException("stage", identifier);
        StageContainer<Stage> container = optStageType.get();

        if(!queueContainers.contains(container)) {
            Optional<Stage> stage = container.initializeInstance(initSettings, this);

            if(!stage.isPresent()) throw new BrokenComponentException("stage", identifier);

            int size = stageQueue.size();
            for(int i = 0; i < size; i++) {
                Stage queueStage = stageQueue.get(i);
                if(queueStage.getStageContainer().getOrderIndex() > container.getOrderIndex()) {
                    stageQueue.add(i, stage.get()); // Insert it
                    queueContainers.add(container);
                    return true; // Return true early as it was successfully added.
                }
            }

            stageQueue.add(stage.get()); // If not added, append it.
            queueContainers.add(container);
            return true;
        }
        return false;
    }


    public SessionHandler<?> getSessionHandler() { return sessionHandler; }
    public StageContainer<?> getActiveStageContainer() { return activeStageContainer; }
    public Stage getActiveStage() { return activeStage; }
}
