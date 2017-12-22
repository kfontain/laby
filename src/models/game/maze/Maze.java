package models.game.maze;

import models.game.Direction;
import models.game.WallType;
import models.game.maze.graph.Edge;
import models.game.maze.graph.Graph;
import models.game.maze.graph.Vertex;

import java.util.*;

public class Maze {
	private Graph g;
	private static Maze maze;
	private Random random;
	
	public Maze() {
        this.random = new Random();
	}
	
	public static Maze getInstance() {
		if(maze == null) maze = new Maze();
		return maze;
	}

	public void loadCustomLevel(CustomLevel level){
        g = new Graph();
        g.setSizeX(13);
        g.setSizeY(13);
        g.createVertexArray();

        WallType type = WallType.WALL;
        LinkedList<Edge> coords = new LinkedList<>();
        switch (level) {
            case GAMEOVER:
                type = WallType.CLOSED_DOOR;
                //g
                createEdge(coords,0, 1, 1, 1);
                createEdge(coords,1, 0, 1, 1);
                createEdge(coords,2, 0, 2, 1);
                createEdge(coords,0, 2, 1, 2);
                createEdge(coords,1, 2, 1, 3);
                createEdge(coords,2, 2, 2, 3);
                createEdge(coords,2, 2, 3, 2);
                createEdge(coords,2, 2, 2, 1);

                //a
                createEdge(coords,4, 0, 4, 1);
                createEdge(coords,4, 1, 4, 2);
                createEdge(coords,5, 0, 5, 1);
                createEdge(coords,5, 1, 5, 2);
                createEdge(coords,4, 1, 3, 1);
                createEdge(coords,5, 1, 6, 1);
                createEdge(coords,4, 2, 3, 2);
                createEdge(coords,5, 2, 6, 2);

                //m
                createEdge(coords,7, 0, 7, 1);
                createEdge(coords,7, 1, 6, 1);
                createEdge(coords,7, 2, 6, 2);
                createEdge(coords,8, 0, 8, 1);
                createEdge(coords,8, 1, 9, 1);
                createEdge(coords,8, 2, 9, 2);
                createEdge(coords,7, 1, 8, 1);

                //e
                createEdge(coords,9, 1, 10, 1);
                createEdge(coords,10, 0, 10, 1);
                createEdge(coords,11, 0, 11, 1);
                createEdge(coords,9, 2, 10, 2);
                createEdge(coords,10, 2, 10, 3);
                createEdge(coords,11, 2, 11, 3);
                createEdge(coords,10, 2, 10, 1);
                createEdge(coords,11, 2, 11, 1);

                //o
                createEdge(coords,0, 10, 1, 10);
                createEdge(coords,0, 11, 1, 11);
                createEdge(coords,1, 10, 1, 9);
                createEdge(coords,2, 10, 2, 9);
                createEdge(coords,2, 10, 3, 10);
                createEdge(coords,2, 11, 3, 11);
                createEdge(coords,2, 11, 2, 12);
                createEdge(coords,1, 11, 1, 12);

                //v
                createEdge(coords,3, 10, 4, 10);
                createEdge(coords,3, 11, 4, 11);
                createEdge(coords,5, 10, 6, 10);
                createEdge(coords,5, 10, 5, 11);
                createEdge(coords,4, 11, 5, 11);
                createEdge(coords,4, 11, 4, 12);

                //e
                createEdge(coords,6, 10, 7, 10);
                createEdge(coords,7, 9, 7, 10);
                createEdge(coords,8, 9, 8, 10);
                createEdge(coords,6, 11, 7, 11);
                createEdge(coords,7, 11, 7, 12);
                createEdge(coords,8, 11, 8, 12);
                createEdge(coords,7, 11, 7, 10);
                createEdge(coords,8, 11, 8, 10);

                //r
                createEdge(coords,10, 9, 10, 10);
                createEdge(coords,10, 10, 10, 11);
                createEdge(coords,11, 9, 11, 10);
                createEdge(coords,11, 10, 11, 11);
                createEdge(coords,10, 10, 9, 10);
                createEdge(coords,11, 10, 12, 10);
                createEdge(coords,10, 11, 9, 11);
                createEdge(coords,10, 11, 11, 11);
                break;
            case CLEARED:
                //y
                createEdge(coords,1, 1, 2, 1);
                createEdge(coords,1, 2, 2, 2);
                createEdge(coords,2, 2, 2, 3);
                createEdge(coords,2, 3, 3, 3);
                createEdge(coords,3, 3, 3, 2);
                createEdge(coords,3, 2, 4, 2);
                createEdge(coords,3, 1, 4, 1);

                //o
                createEdge(coords,5, 0, 5, 1);
                createEdge(coords,6, 0, 6, 1);
                createEdge(coords,5, 1, 4, 1);
                createEdge(coords,5, 2, 4, 2);
                createEdge(coords,5, 3, 4, 3);
                createEdge(coords,5, 3, 5, 4);
                createEdge(coords,6, 3, 6, 4);
                createEdge(coords,6, 3, 7, 3);
                createEdge(coords,6, 2, 7, 2);
                createEdge(coords,6, 1, 7, 1);

                //u
                createEdge(coords,8, 1, 7, 1);
                createEdge(coords,8, 2, 7, 2);
                createEdge(coords,8, 3, 7, 3);
                createEdge(coords,8, 3, 8, 4);
                createEdge(coords,9, 3, 9, 4);
                createEdge(coords,9, 3, 10, 3);
                createEdge(coords,9, 2, 10, 2);
                createEdge(coords,9, 1, 10, 1);

                //w
                createEdge(coords,2, 9, 1, 9);
                createEdge(coords,2, 10, 1, 10);
                createEdge(coords,2, 11, 1, 11);
                createEdge(coords,2, 11, 2, 12);
                createEdge(coords,3, 11, 3, 12);
                createEdge(coords,3, 11, 4, 11);
                createEdge(coords,3, 10, 4, 10);
                createEdge(coords,3, 9, 4, 9);
                break;
        }

        for (Edge e : coords)
            e.setWallType(type);
    }

    public Edge createEdge(int x1, int y1, int x2, int y2){
        Edge result = new Edge();
        Vertex v1 = g.getVertex(x1, y1, true);
        Vertex v2 = g.getVertex(x2, y2, true);
        Direction d = Direction.NORTH;
        if (x1 > x2)
            d = Direction.WEST;
        if (x1 < x2)
            d = Direction.EAST;
        if (y1 > y2)
            d = Direction.NORTH;
        if (y1 < y2)
            d = Direction.SOUTH;

        result.addVertex(v1);
        result.addVertex(v2);
        v1.addEdge(d, result);
        v2.addEdge(d.getOppositeDirection(), result);

        return result;
    }

    public Edge createEdge(List<Edge> list, int x1, int y1, int x2, int y2){
        Edge result = createEdge(x1, y1, x2, y2);
        list.add(result);
        return result;
    }

	public void initializeGraph(int x, int y, int difficulty){
		g = new Graph();
		g.setSizeX(x);
		g.setSizeY(y);
		g.createVertexArray();
		g.generateMazeGraph();
		for (int i = 0; i < difficulty; i++)
		    getRandomEdge().setWallType(WallType.PATH);

		g.drawMazeOnConsole();
	}

    public ArrayList<int[]> getWalls(){
        ArrayList<int[]> walls = new ArrayList<int[]>();

        for(int x = 0; x < g.getSizeX(); x++) {
            for(int y = 0; y < g.getSizeY(); y++) {
                Vertex v = g.getVertex(x, y, true);
                Vector<Direction> dir = v.getWalls();
                for(Direction d : dir) {
                    int[] coords = new int[4];
                    switch(d) {
                        case EAST:
                            coords[0] = x;
                            coords[1] = y;
                            coords[2] = x + 1;
                            coords[3] = y;
                            break;
                        case WEST:
                            coords[0] = x;
                            coords[1] = y;
                            coords[2] = x - 1;
                            coords[3] = y;
                            break;
                        case NORTH:
                            coords[0] = x;
                            coords[1] = y;
                            coords[2] = x ;
                            coords[3] = y - 1;
                            break;
                        case SOUTH:
                            coords[0] = x;
                            coords[1] = y;
                            coords[2] = x;
                            coords[3] = y + 1;
                            break;
                    }
                    if (coords[2] >= 0 && coords[3] >= 0 && coords[2] < g.getSizeX() && coords[3] < g.getSizeY())
                        walls.add(coords);
                }
            }
        }
        return walls;
    }

    public ArrayList<int[]> getDoors(boolean isClosed){
        ArrayList<int[]> walls = new ArrayList<int[]>();

        for(int x = 0; x < g.getSizeX(); x++) {
            for(int y = 0; y < g.getSizeY(); y++) {
                Vertex v = g.getVertex(x, y);
                Vector<Direction> dir = v.getDoors(isClosed);
                for(Direction d : dir) {
                    int[] coords = new int[4];
                    switch(d) {
                        case EAST:
                            coords[0] = x;
                            coords[1] = y;
                            coords[2] = x + 1;
                            coords[3] = y;
                            break;
                        case WEST:
                            coords[0] = x;
                            coords[1] = y;
                            coords[2] = x - 1;
                            coords[3] = y;
                            break;
                        case NORTH:
                            coords[0] = x;
                            coords[1] = y;
                            coords[2] = x ;
                            coords[3] = y - 1;
                            break;
                        case SOUTH:
                            coords[0] = x;
                            coords[1] = y;
                            coords[2] = x;
                            coords[3] = y + 1;
                            break;
                    }
                    if (coords[2] >= 0 && coords[3] >= 0 && coords[2] < g.getSizeX() && coords[3] < g.getSizeY())
                        walls.add(coords);
                }
            }
        }
        return walls;
    }

	public void updateDistFromPlayer(int x, int y) {
		g.updateDistanceFromVertex(x, y);
		//g.drawGraphOnConsole();
		g.drawGraphWithValuesOnConsole(); // origin distance starts at 10 just for the display.
		//g.drawMazeOnConsole();
	}
	
	public Direction getDirectionForNPC(int x, int y) {
		Vertex npc = g.getVertex(x, y);
		Vector<Vertex> neigh = npc.getNeighbours();
		int dist = npc.getDist();
		for(Vertex u : neigh) {
			if(u.getDist() < dist) {
				return npc.getDirectionTo(u);
			}
		}
		return null;
	}

	public int getSizeX(){
		return g.getSizeX();
	}

	public int getSizeY(){
		return g.getSizeY();
	}

	public Vector<Direction> getWallsAt(int x, int y){
		return g.getVertex(x, y).getWalls();
	}

	public Edge getRandomEdge() {
	    while(true){
            int x = random.nextInt(g.getSizeX());
            int y = random.nextInt(g.getSizeY());
            Collection<Edge> edges = this.g.getVertex(x,y).getEdges().values();
            for (Edge e : edges) {
                if (e.getWallType() == WallType.WALL) {
                    return e;
                }
            }
        }
	}

    public Vector<Direction> getDoorsAt(int x, int y, boolean isClosed) {
        return g.getVertex(x, y).getDoors(isClosed);
    }
}
