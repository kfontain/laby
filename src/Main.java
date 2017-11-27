import javafx.application.Application;
import javafx.stage.Stage;
import models.game.maze.graph.Graph;

import static javafx.application.Application.launch;

public class Main extends Application {

    private static boolean debugModeEnabled = true;

    public static void testGraph(){
        Graph g = new Graph();
        g.setSizeX(10);
        g.setSizeY(10);
        g.createVertexArray();
        g.generateMazeGraph();
        g.drawGraphOnConsole();
    }

    public static void testMaze(){
        Graph g = new Graph();
        g.setSizeX(10);
        g.setSizeY(10);
        g.createVertexArray();
        g.generateMazeGraph();
        g.drawMazeOnConsole();
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