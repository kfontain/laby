package controllers;

import java.util.ArrayList;

import controllers.ingame.EntitySpawner;
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

    private Master() {
        vf = ViewFrame.getInstance();
    }

    public static Master getInstance() {
        if (mst == null) mst = new Master();
        return mst;
    }

    public void start(Stage primaryStage) {
        GameManager.initialize();
        GameManager.randomizeLevel();
        SpriteManager.initialize("res");

        ViewFrame.drawFrame(primaryStage, 10, 10);
        for(int[] wall : GameManager.getMaze().getWalls()) {
            ViewFrame.drawWall(wall[0], wall[1], wall[2], wall[3], Color.BURLYWOOD);
        }

        EntitySpawner.spawnPlayerAtRandomPosition();
        EntitySpawner.spawnCandyAtRandomPosition();
        EntitySpawner.spawnCandyAtRandomPosition();
        EntitySpawner.spawnCandyAtRandomPosition();
        EntitySpawner.spawnCandyAtRandomPosition();
        EntitySpawner.spawnCandyAtRandomPosition();
        ViewFrame.drawEntities();


    }

}
