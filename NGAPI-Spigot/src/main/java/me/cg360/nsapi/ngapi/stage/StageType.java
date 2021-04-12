package me.cg360.nsapi.ngapi.stage;

public enum StageType {

    INIT, // No players should be in at all. Prepare the game.
    PREPARE, // Players have been added if a stage implements that.
    GAMEPLAY, // Game is in session. Really this should only the the GameBehaviour's main block. Could make parts of a game reusable?
    POST // Gameplay has concluded. Players may still be around.

}
