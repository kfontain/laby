package models.drawable;

import models.game.WallType;
import models.game.maze.Maze;
import models.game.maze.graph.Edge;

public class OnOff extends Entity {

    private boolean isOpen;
    private Edge edge;

    public OnOff() {
        super(EntityType.ONOFF);
        Maze maze = Maze.getInstance();
        isOpen = false;
        this.edge = maze.getRandomEdge();
        this.edge.setWallType(WallType.CLOSED_DOOR);
        setSpriteType(SpriteType.SWITCH_OFF);
    }

    public OnOff(int x, int y) {
        super(x, y, EntityType.ONOFF);
        isOpen = false;
        setSpriteType(SpriteType.SWITCH_OFF);
    }

    @Override
    public void eventCollision() {
        isOpen = !isOpen;
        this.edge.setWallType(isOpen ? WallType.OPENED_DOOR : WallType.CLOSED_DOOR);
        setSpriteType(isOpen ? SpriteType.SWITCH_ON : SpriteType.SWITCH_OFF);
    }

}