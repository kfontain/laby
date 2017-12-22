package models.game.maze.graph;

import models.game.Direction;
import models.game.WallType;

import java.util.LinkedList;

public class Edge {
    private WallType wallType;

    private LinkedList<Vertex> vertexes;

    public Edge(){
        this.vertexes = new LinkedList<>();
    }

    public WallType getWallType() {
        return wallType;
    }

    public void setWallType(WallType wallType) {
        this.wallType = wallType;
    }

    public LinkedList<Vertex> getVertexes() {
        return vertexes;
    }

    public void removeVertex(Vertex v) {
        vertexes.remove(v);
    }

    public void addVertex(Vertex v) {
        this.vertexes.add(v);
    }

}
