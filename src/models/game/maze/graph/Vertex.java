package models.game.maze.graph;

import models.game.Direction;
import models.game.WallType;

import java.util.*;

public class Vertex {
    private int x;
    private int y;
    private Graph graph;
    private int dist;
    private boolean isMarked;
    private Random random;

    public HashMap<Direction, Edge> getEdges() {
        return edges;
    }

    private HashMap<Direction, Edge> edges;

    public Vertex(Graph graph) {
        this.graph = graph;
        edges = new HashMap<>();
        dist = -1;
        isMarked = false;
    }
    
    public boolean isMarked() {
    	return isMarked;
    }
    
    public void setMark(boolean mark) {
    	isMarked = mark;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void randomizePaths(Random random){
        Vector<Direction> d = new Vector<>();
        for (Direction dir : Direction.values())
            d.add(dir);

        Vector<Direction> deck = new Vector<>();
        for (int i = 0; i < 4; i++){
            int n = random.nextInt(d.size());
            deck.add(d.get(n));
            d.remove(n);
        }

        for (int i = 0; i < 4; i++){
            Direction currentDirection = deck.get(i);
            if (!edges.containsKey(currentDirection)){

                int xt = 0;
                int yt = 0;
                Edge ne = new Edge();
                ne.addVertex(this);
                ne.setWallType(WallType.PATH);
                switch (currentDirection) {
                    case EAST:
                        xt = 1;
                        break;
                    case SOUTH:
                        yt = 1;
                        break;
                    case WEST:
                        xt = -1;
                        break;
                    case NORTH:
                        yt = -1;
                        break;
                }

                Vertex v = new Vertex(graph);
                v.setX(x + xt);
                v.setY(y + yt);
                boolean hw = !isPossibleToMove(currentDirection);
                if (!hw && graph.getVertex(v.getX(), v.getY()) == null){
                    graph.addVertex(v);
                    this.addEdge(currentDirection, ne);
                    ne.addVertex(v);
                    v.addEdge(currentDirection.getOppositeDirection(), ne);
                    v.randomizePaths(random);
                }else{
                        ne.setWallType((hw ? WallType.HARD_WALL : WallType.WALL));
                        this.addEdge(currentDirection, ne);
                        if (!hw){
                            v = graph.getVertex(v.getX(), v.getY());
                            ne.addVertex(v);
                            v.edges.put(currentDirection.getOppositeDirection(), ne);
                        }
                }
            }
        }
    }

    public boolean isPossibleToMove(Direction d){
        switch (d) {
            case EAST:
                return x < graph.getSizeX() - 1;
            case SOUTH:
                return y < graph.getSizeY() - 1;
            case WEST:
                return  x > 0;
            case NORTH:
                return y > 0;
        }

        return false;
    }

    public boolean isLinkedTo(Direction d){
        return edges.containsKey(d) && edges.get(d).getWallType() == WallType.PATH || edges.get(d).getWallType() == WallType.OPENED_DOOR;
    }
    
    public Direction getDirectionTo(Vertex dest) {
    	for(Direction d : edges.keySet()) {
    		if(isLinkedTo(d)) {
				switch(d) {
				case EAST:
					if(graph.getVertex(x + 1, y) == dest)
						return Direction.EAST;
					break;
				case WEST:
					if(graph.getVertex(x - 1, y) == dest)
						return Direction.WEST;
					break;
				case NORTH:
					if(graph.getVertex(x, y - 1) == dest)
						return Direction.NORTH;
					break;
				case SOUTH:
					if(graph.getVertex(x, y + 1) == dest)
						return Direction.SOUTH;
					break;
				}
    			
    		}
    	}
    	return null;
    }
    
    public Vector<Vertex> getNeighbours(){
    	Vector<Vertex> neigh = new Vector<>();
    	
    	for(Direction d : edges.keySet()) {
    		if(isLinkedTo(d)) {
				switch(d) {
				case EAST:
					neigh.add(graph.getVertex(x + 1, y));
					break;
				case WEST:
					neigh.add(graph.getVertex(x - 1, y));
					break;
				case NORTH:
					neigh.add(graph.getVertex(x, y - 1));
					break;
				case SOUTH:
					neigh.add(graph.getVertex(x, y + 1));
					break;
				}
    		}
    	}
    	
    	return neigh;
    }

    public void addEdge(Direction direction, Edge e){
        edges.put(direction, e);
    }

    public Vector<Direction> getWalls(){
        /*Vector<Direction> res = new Vector<>();
        for (Direction d : Direction.values()){
            res.add(d);
        }
        for (Edge e : edges){
            res.remove(e.getDirection());
        }

        return res;*/

        Vector<Direction> res = new Vector<>();
        for (Map.Entry<Direction, Edge> e : edges.entrySet()){
            if (e.getValue().getWallType() == WallType.WALL || e.getValue().getWallType() == WallType.HARD_WALL)
                res.add(e.getKey());
        }

        return res;
    }

	public int getDist() {
		return dist;
	}

    public Vector<Direction> getDoors(boolean isClosed){
        Vector<Direction> res = new Vector<>();
        for (Map.Entry<Direction, Edge> e : edges.entrySet()){
            if (e.getValue().getWallType() == (isClosed ? WallType.CLOSED_DOOR : WallType.OPENED_DOOR))
                res.add(e.getKey());
        }

        return res;
    }



	public void setDist(int dist) {
		this.dist = dist;
	}

    public void removeEdge(Edge edge) {
        for (Map.Entry<Direction, Edge> e : edges.entrySet())
            if (e.getValue() == edge){
                edges.remove(e.getKey());
                return;
            }
    }
}
