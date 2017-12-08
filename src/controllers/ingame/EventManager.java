package controllers.ingame;

import models.drawable.Character;
import models.drawable.Entity;
import models.drawable.EntityType;

import java.util.LinkedList;

public class EventManager {

    public static void manageCollision(){
        Character player = GameManager.getPlayer();
        LinkedList<Entity> entities = GameManager.getEntities();
        LinkedList<Entity> entitiesCol = new LinkedList<>();
        for (Entity e : entities) {
            if(e != player && player.ifCollision(e)){
                entitiesCol.add(e);
            }
        }
        for(Entity e : entitiesCol){
        	if(e.getType() != EntityType.NPC)
        		e.eventCollision();
        	else {
        		((Character)e).eventCollision();
        	}
        		
        }
    }
}
