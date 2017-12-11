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
        GameManager.getMaze().initializeGraph(10, 10);
        SpriteManager.initialize("res");

        ViewFrame.drawFrame(primaryStage, 10, 10);

        EntitySpawner.spawnPlayerAtRandomPosition();
        Entity player = GameManager.getPlayer();
        
        GameManager.getMaze().updateDistFromPlayer(player.getX(), player.getY());
        
        EntitySpawner.spawnNpcAtRandomPosition();

        EntitySpawner.spawnOnOffAtRandomPosition();

        EntitySpawner.spawnCandyAtRandomPosition();

    }

    public void render(){
        for(int[] wall : GameManager.getMaze().getWalls()) {
            ViewFrame.drawWall(wall[0], wall[1], wall[2], wall[3], Color.BURLYWOOD);
        }
        for(int[] door : GameManager.getMaze().getDoors(true)) {
            ViewFrame.drawWall(door[0], door[1], door[2], door[3], Color.GREEN);
        }

        for(int[] door : GameManager.getMaze().getDoors(false)) {
            ViewFrame.drawWall(door[0], door[1], door[2], door[3], Color.RED);
        }

        ViewFrame.drawEntities();
    }
    
    public void stop() {
    	mst = null;
    }

}
