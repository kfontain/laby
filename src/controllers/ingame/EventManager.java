package controllers.ingame;

import models.drawable.Character;
import models.drawable.Entity;
import models.drawable.EntityType;

import java.util.LinkedList;

/**
 * EventManager permet de détecter si il y a collision et d'envoyer un signal aux entités concernées.
 */
public class EventManager {

    public static void manageCollision(){
        Entity player = GameManager.getPlayer();
        LinkedList<Entity> entities = GameManager.getEntities();
        //LinkedList<Entity> entitiesCol = new LinkedList<>();
        for (Entity e : entities) {
            if(e != player && player.ifCollision(e)){
                //entitiesCol.add(player);
                player.eventCollision(e);
                e.eventCollision(player);
            }
        }
       /* for(Entity e : entitiesCol){
        	if(e.getType() != EntityType.NPC)
        		e.eventCollision();
        	else {
        		((Character)e).eventCollision();
        	}
        		
        }*/
    }
}
