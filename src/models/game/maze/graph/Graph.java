package models.game.maze.graph;

import models.game.Direction;

import java.io.Console;
import java.util.Random;
import java.util.Vector;

public class Graph {
    private int sizeX;
    private int sizeY;
    private int startNodeIndex;
    private int exitNodeIndex;
    private Vertex[][] vertexes;
    private Random random;

    void addVertex(Vertex v){
        vertexes[v.getX()][v.getY()] = v;
    }

    public Vertex getVertex(int x, int y){
        return vertexes[x][y];
    }

    public Graph(){
        random = new Random();
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
}