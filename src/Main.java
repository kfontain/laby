import models.game.maze.graph.Graph;

public class Main {

    private static boolean debugModeEnabled = true;

    public static void main(String[] args){
        Graph g = new Graph();
        g.setSizeX(10);
        g.setSizeY(10);
        g.createVertexArray();
        g.generateMazeGraph();
        g.drawGraphOnConsole();
    }

    public static boolean isDebugModeEnabled() {
        return debugModeEnabled;
    }
}
