package controllers;

import java.util.ArrayList;

import controllers.ingame.GameManager;
import controllers.ingame.SpriteManager;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.drawable.Character;
import models.drawable.Entity;
import models.game.maze.Maze;
import views.ViewFrame;

public class Master {

    private static Master mst;
    private ViewFrame vf;
    private Maze maze;
    private ArrayList<int[]> walls;

    private Master() {
        vf = ViewFrame.getInstance();
        maze = Maze.getInstance();
        walls = maze.getWalls();
        
    }

    public static Master getInstance() {
        if (mst == null) mst = new Master();
        return mst;
    }

    public void start(Stage primaryStage) {
        GameManager.initialize();
        SpriteManager.initialize(System.getProperty("user.dir"));

        ViewFrame.getInstance();
        ViewFrame.drawFrame(primaryStage, 10, 10);
        for(int[] wall : walls) {
            ViewFrame.drawWall(wall[0], wall[1], wall[2], wall[3], Color.RED);
        }
        GameManager.addEntity(new Character(2, 2, true));
        ViewFrame.drawEntities();
    }

}
