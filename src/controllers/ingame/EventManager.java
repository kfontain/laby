package controllers.ingame;

import models.drawable.Character;
import models.drawable.Entity;

import java.util.LinkedList;

public class EventManager {

    public static void manageCollision(){
        Character player = GameManager.getPlayer();
        LinkedList<Entity> entities = GameManager.getEntities();
        for (Entity e : entities) {
            if(e != player && player.ifCollision(e)){
                System.out.println("collision");
            }
        }
    }
}
