package me.cg360.nsapi.ngapi.registry;

import me.cg360.nsapi.ngapi.stage.Stage;
import me.cg360.nsapi.ngapi.stage.StageContainer;
import net.cg360.nsapi.commons.data.keyvalue.Key;
import net.cg360.nsapi.commons.id.Identifier;

import java.util.HashMap;
import java.util.Optional;

public class StageRegistry {

    private static StageRegistry registryInstance;

    private HashMap<String, StageContainer<? extends Stage>> stageTypes;

    public StageRegistry(){
        this.stageTypes = new HashMap<>();
    }

    /**
     * Makes the registry the result provided from StageRegistry#get() and
     * finalizes the instance to an extent.
     */
    public void setAsPrimaryRegistry(){
        if(registryInstance == null) registryInstance = this;
    }

    /**
     * Registers a stage type. Does not accept already registered stage types.
     * @return true if stage type was registered.
     */
    public boolean registerStageType(StageContainer<? extends Stage> stage) {
        Identifier id = stage.getIdentifier();

        if(!stageTypes.containsKey(id.getID())){
            stageTypes.put(id.getID(), stage);
            return true;
        }
        return false;
    }

    /**
     * Registers a stage type. Does not accept already registered  stage types.
     * @return self for chaining.
     */
    public StageRegistry register(StageContainer<? extends Stage> stage) {
        registerStageType(stage);
        return this;
    }


    /** @return the primary instance of the Registry. */
    public static StageRegistry get(){
        return registryInstance;
    }

    @SuppressWarnings("unchecked") // Should be caught in the try-catch statement
    public <T extends Stage> Optional<StageContainer<T>> getStageType(Key<T> key) {
        StageContainer<? extends Stage> m = stageTypes.get(key.get());

        if(m == null) { // Not Found
            return Optional.empty();

        } else { // Found but could be the wrong type.

            try { // This works I guess
                return Optional.of((StageContainer<T>) m);

            } catch (ClassCastException err) {
                return Optional.empty();
            }
        }
    }

    /** @return the stage type. */
    public Optional<StageContainer<Stage>> getStageType(Identifier key) {
        return getStageType(new Key<>(key.getID()));
    }
}
