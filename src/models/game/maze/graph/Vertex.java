package models.game.maze.graph;

import models.game.Direction;
import models.game.WallType;

import java.util.Random;
import java.util.Vector;

public class Vertex {
    private int x;
    private int y;
    private Graph graph;
    private int distFromPlayer;
    private boolean isMarked;
    private Random random;

    public Vector<Edge> getEdges() {
        return edges;
    }

    private Vector<Edge> edges;

    public Vertex(Graph graph) {
        this.graph = graph;
        edges = new Vector<>();
        setDistFromPlayer(-1);
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
            int xt = 0;
            int yt = 0;
            Edge ne = new Edge();
            Edge nv = new Edge();
            ne.setWallType(WallType.PATH);
            nv.setWallType(WallType.PATH);
            switch (currentDirection) {
                case EAST:
                    xt = 1;
                    ne.setDirection(Direction.EAST);
                    nv.setDirection(Direction.WEST);
                    break;
                case SOUTH:
                    yt = 1;
                    ne.setDirection(Direction.SOUTH);
                    nv.setDirection(Direction.NORTH);
                    break;
                case WEST:
                    xt = -1;
                    ne.setDirection(Direction.WEST);
                    nv.setDirection(Direction.EAST);
                    break;
                case NORTH:
                    yt = -1;
                    ne.setDirection(Direction.NORTH);
                    nv.setDirection(Direction.SOUTH);
                    break;
            }

            Vertex v = new Vertex(graph);
            v.setX(x + xt);
            v.setY(y + yt);
            if (isPossibleToMove(currentDirection) && graph.getVertex(v.getX(), v.getY()) == null){
                graph.addVertex(v);
                this.addEdge(ne);
                v.addEdge(nv);
                v.randomizePaths(random);
            }else{
                boolean ae = false;
                for (Edge ee : edges)
                    if (ee.getDirection() == currentDirection)
                        ae = true;

                if (!ae){
                    ne.setWallType(WallType.WALL);
                    this.addEdge(ne);
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
        for (Edge e : edges)
            if (e.getDirection() == d && e.getWallType() != WallType.WALL && e.getWallType() != WallType.CLOSED_DOOR)
                return true;

        return false;
    }
    
    public Direction getDirectionTo(Vertex dest) {
    	for(Direction d : Direction.values()) {
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
    	
    	for(Direction d : Direction.values()) {
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

    public void addEdge(Edge e){
        edges.add(e);
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
        for (Edge e : edges){
            if (e.getWallType() == WallType.WALL)
                res.add(e.getDirection());
        }

        return res;
    }

    public Vector<Direction> getDoors(boolean isClosed){
        Vector<Direction> res = new Vector<>();
        for (Edge e : edges){
            if (e.getWallType() == (isClosed ? WallType.CLOSED_DOOR : WallType.OPENED_DOOR))
                res.add(e.getDirection());
        }
        return res;
    }

	public int getDistFromPlayer() {
		return distFromPlayer;
	}

	public void setDistFromPlayer(int distFromPlayer) {
		this.distFromPlayer = distFromPlayer;
	}
}
