package me.cg360.nsapi.ngapi.stage;

public enum StageType {

    INIT(0), // No players should be in at all. Prepare the game.
    PREPARE(5000), // Players have been added if a stage implements that.
    GAMEPLAY(10000), // Game is in session. Really this should only the the GameBehaviour's main block. Could make parts of a game reusable?
    POST(15000); // Gameplay has concluded. Players may still be around.

    private int order;

    StageType(int orderStart) {
           this.order = orderStart;
    }

    public int getStartingOrderIndex() {
        return order;
    }
}
