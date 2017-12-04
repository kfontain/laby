package controllers.ingame;

import models.drawable.Character;
import models.drawable.Entity;

import java.util.Random;

public class EntitySpawner {

    public static void spawnPlayerAtRandomPosition(){
        Random r = GameManager.getRandom();
        int x = r.nextInt(GameManager.getMaze().getSizeX());
        int y = r.nextInt(GameManager.getMaze().getSizeY());
        spawnPlayer(x, y);
    }

    public static void spawnPlayer(int x, int y){
        Entity e = new Character(x, y, true);
        GameManager.addEntity(e);
    }
}
