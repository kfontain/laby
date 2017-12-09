package controllers.ingame;

import models.drawable.Bonus;
import models.drawable.Character;
import models.drawable.Entity;
import models.drawable.OnOff;

import java.util.Random;

public class EntitySpawner {

    public static void spawnAtRandomPosition(Entity e){
        Random r = GameManager.getRandom();
        int x = r.nextInt(GameManager.getMaze().getSizeX());
        int y = r.nextInt(GameManager.getMaze().getSizeY());
        spawnEntityAtPosition(e, x, y);
    }

    public static void spawnEntityAtPosition(Entity e, int x, int y){
        e.setX(x);
        e.setY(y);
        GameManager.addEntity(e);
    }

    public static void spawnPlayerAtRandomPosition(){
        Entity e = new Character(true);
        spawnAtRandomPosition(e);
    }

    public static void spawnCandyAtRandomPosition(){
        Entity e = new Bonus();
        spawnAtRandomPosition(e);
    }
    
    public static void spawnNpcAtRandomPosition() {
    	Entity e = new Character(false);
    	spawnAtRandomPosition(e);
    }

    public static void spawnOnOffAtRandomPosition() {
        Entity e = new OnOff();
        spawnAtRandomPosition(e);
    }
}
