package models.game.maze.graph;

import models.game.Direction;
import models.game.WallType;

public class Edge {
    private WallType wallType;
    private Direction direction;

    public WallType getWallType() {
        return wallType;
    }

    public void setWallType(WallType wallType) {
        this.wallType = wallType;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
