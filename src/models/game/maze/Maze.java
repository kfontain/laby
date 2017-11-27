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
        g = new Graph();
        g.setSizeX(10);
        g.setSizeY(10);
        g.createVertexArray();
        g.generateMazeGraph();
        g.drawMazeOnConsole();
	}
	
	public static Maze getInstance() {
		if(maze == null) maze = new Maze();
		return maze;
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
					if (coords[2] >= 0 && coords[3] >= 0)
					    walls.add(coords);
				}
			}
		}
		
		return walls;
		
	}
	
}
