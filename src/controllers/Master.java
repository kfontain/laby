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
        GameManager.getMaze().initializeGraph(20, 20);
        SpriteManager.initialize("res");

        ViewFrame.drawFrame(primaryStage, 20, 20);
        for(int[] wall : GameManager.getMaze().getWalls()) {
            ViewFrame.drawWall(wall[0], wall[1], wall[2], wall[3], Color.BURLYWOOD);
    }

        EntitySpawner.spawnPlayerAtRandomPosition();
        Entity player = GameManager.getPlayer();
        
        GameManager.getMaze().updateDistFromPlayer(player.getX(), player.getY());
        
        EntitySpawner.spawnNpcAtRandomPosition();

        EntitySpawner.spawnOnOffAtRandomPosition();

        EntitySpawner.spawnCandyAtRandomPosition();

        ViewFrame.drawEntities();
    }
    
    public void stop() {
    	mst = null;
    }

}
