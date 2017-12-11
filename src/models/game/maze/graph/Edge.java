package models.game.maze.graph;

import models.game.Direction;
import models.game.WallType;

public class Edge {
    private WallType wallType;

    public WallType getWallType() {
        return wallType;
    }

    public void setWallType(WallType wallType) {
        this.wallType = wallType;
    }

}
