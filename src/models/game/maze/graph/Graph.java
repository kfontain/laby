package models.game.maze.graph;

import models.game.Direction;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

/**
 * Graph est la classe représentant la labyrinthe sous forme d'un graphe en modèle.
 * Si il y a une Edge entre deux Vertex, cela représente un mur dans la labyrinthe.
 */
public class Graph {
    private int sizeX;
    private int sizeY;
    private Vertex[][] vertexes;
    private Random random;

    public Graph(){
        random = new Random();
    }

    public void addVertex(Vertex v){
        vertexes[v.getX()][v.getY()] = v;
    }

    public Vertex getVertex(int x, int y){
        return vertexes[x][y];
    }

    public Vertex getVertex(int x, int y, boolean safe){
        if (!safe)
            return getVertex(x, y);
        else{
            Vertex v = getVertex(x, y);
            if (v == null){
                v = new Vertex(this);
                v.setX(x);
                v.setY(y);
                addVertex(v);
            }

            return v;
        }

    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public void createVertexArray(){
        vertexes = new Vertex[sizeX][sizeY];
    }

    public void generateMazeGraph(){
        int startX = random.nextInt(sizeX);
        int startY = random.nextInt(sizeY);

        System.out.println("Generate graph starting at (" + startX + ", " + startY + ")...");
        Vertex v = new Vertex(this);
        v.setX(startX);
        v.setY(startY);
        addVertex(v);
        v.randomizePaths(random);
    }
    
    private void unmarkVertex() {
    	for(int j = 0; j < sizeY; j++) {
    		for(int i = 0; i < sizeX; i++) {
    			vertexes[i][j].setMark(false);
    		}
    	}
    }

    //
    private void markAccessVertex(int i, int j) {
    	Vertex v = vertexes[i][j];
    	ArrayList<Vertex> queue = new ArrayList<>();
    	queue.add(v);
    	v.setDist(0);

    	while(!queue.isEmpty()){
    	    v = queue.get(0);
            v.setMark(true);
            int value = v.getDist();

            Vector<Vertex> neigh = v.getNeighbours();
            queue.remove(v);
            for(Vertex n : neigh)
                if(!n.isMarked()) {
                    n.setDist(value + 1);
                    queue.add(n);
                }
        }
    }

    /**
     * Permet d'obtenir les distances entre un sommet et le reste des sommets.
     * @param x coordonnée en x du sommet à partir duquel on veut connaitre les distances.
     * @param y coordonnée en y du sommet à partir duquel on veut connaitre les distances.
     */
    public void updateDistanceFromVertex(int x, int y) {
    	unmarkVertex();
    	markAccessVertex(x, y);
    }
    
    // =========================================================================
    //Some methods to display the graph ...
    public void drawMazeOnConsole(){
        for (int i = 0; i < sizeX; i++)
            System.out.print(" _");

        System.out.println("");
        for (int j = 0; j < sizeY; j++){
            System.out.print(("|"));
            for (int i = 0; i < sizeX; i++){
                Vector<Direction> walls = getVertex(i, j).getWalls();
                if (walls.contains(Direction.SOUTH))
                    System.out.print("_");
                else
                    System.out.print(" ");

                if (walls.contains(Direction.EAST))
                    System.out.print("|");
                else
                    System.out.print(" ");
            }

            System.out.println("");
        }
    }
    
    public void drawGraphOnConsole(){
        for (int j = 0; j < sizeY; j++){
            for (int i = 0; i < sizeX; i++){
                System.out.print("o");
                if (getVertex(i, j).isLinkedTo(Direction.EAST))
                    System.out.print("--");
                else
                    System.out.print("  ");
            }
            System.out.println("");
            for (int i = 0; i < sizeX; i++){
                if (getVertex(i, j).isLinkedTo(Direction.SOUTH))
                    System.out.print("|");
                else
                    System.out.print(" ");

                System.out.print("  ");
            }
            System.out.println("");
        }
    }

    
    public void drawGraphWithValuesOnConsole(){
        for (int j = 0; j < sizeY; j++){
            for (int i = 0; i < sizeX; i++){
                int d = getVertex(i, j).getDist();
                System.out.print( (d < 10? "0" : "") + d);
                if (getVertex(i, j).isLinkedTo(Direction.EAST))
                    System.out.print("--");
                else
                    System.out.print("  ");
            }
            System.out.println("");
            for (int i = 0; i < sizeX; i++){
                if (getVertex(i, j).isLinkedTo(Direction.SOUTH))
                    System.out.print("| ");
                else
                    System.out.print("  ");

                System.out.print("  ");
            }

            System.out.println("");
        }
    }

    public void removeEdge(Edge edge) {
        //edge.setWallType(WallType.OPENED_DOOR);
        for (Vertex v : edge.getVertexes())
            v.removeEdge(edge);
    }
}
