package controllers;

import controllers.ingame.GameManager;
import controllers.ingame.SpriteManager;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
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
        ViewFrame.setStage(primaryStage);
        GameManager.initialize();
        SpriteManager.initialize("res");
        GameManager.generateSampleLevel();
        ViewFrame.drawFrame(GameManager.getMaze().getSizeX(), GameManager.getMaze().getSizeY());

        render(true);
    }

    public void render(boolean redraw){
        if (redraw){
            ViewFrame.clear();
            ViewFrame.drawFrame(GameManager.getMaze().getSizeX(), GameManager.getMaze().getSizeY());
        }

        for(int[] wall : GameManager.getMaze().getWalls()) {
             ViewFrame.drawWall(wall[0], wall[1], wall[2], wall[3], Color.BURLYWOOD);
        }
        for(int[] door : GameManager.getMaze().getDoors(true)) {
            ViewFrame.drawWall(door[0], door[1], door[2], door[3], Color.RED);
        }

        for(int[] door : GameManager.getMaze().getDoors(false)) {
            ViewFrame.drawWall(door[0], door[1], door[2], door[3], Color.GREEN);
        }

        ViewFrame.setIsAnimating(true);
    }
    
    public void stop() {
    	mst = null;
    }

}
