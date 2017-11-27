import javafx.stage.Stage;
import models.game.maze.graph.Graph;

import static javafx.application.Application.launch;

public class Main {

    private static boolean debugModeEnabled = true;

    public static void testGraph(String[] args){
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

    public static void main(String[] args){
        launch();
    }

    public void start(Stage stage){

    }

    public void stop(){

    }
}