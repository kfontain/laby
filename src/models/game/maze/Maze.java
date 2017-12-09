package models.game.maze;

import java.util.ArrayList;
import java.util.Vector;

import models.game.Direction;
import models.game.maze.graph.Graph;
import models.game.maze.graph.Vertex;

public class Maze {
	private Graph g;
	private static Maze maze;
	
	public Maze() {
		//initializeGraph(10, 10);
	}
	
	public static Maze getInstance() {
		if(maze == null) maze = new Maze();
		return maze;
	}

	public void initializeGraph(int x, int y){
		g = new Graph();
		g.setSizeX(10);
		g.setSizeY(10);
		g.createVertexArray();
		g.generateMazeGraph();
		//g.drawMazeOnConsole();
	}
	
	public ArrayList<int[]> getWalls(){
		ArrayList<int[]> walls = new ArrayList<int[]>();
		
		for(int x = 0; x < g.getSizeX(); x++) {
			for(int y = 0; y < g.getSizeY(); y++) {
				Vertex v = g.getVertex(x, y);
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
	
	public void updateDistFromPlayer(int x, int y) {
		g.updateDistanceFromPlayer(x, y);
		//g.drawGraphOnConsole();
		g.drawGraphWithValuesOnConsole(); // origin distance starts at 10 just for the display.
		//g.drawMazeOnConsole();
	}
	
	public Direction getDirectionForNPC(int x, int y) {
		Vertex npc = g.getVertex(x, y);
		Vector<Vertex> neigh = npc.getNeighbours();
		int dist = npc.getDistFromPlayer();
		for(Vertex u : neigh) {
			if(u.getDistFromPlayer() < dist) {
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
	
}
