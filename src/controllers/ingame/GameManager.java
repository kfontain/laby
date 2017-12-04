package controllers.ingame;

import models.drawable.Entity;
import sun.awt.image.ImageWatched;

import javax.xml.stream.events.EndElement;
import java.util.LinkedList;

public class GameManager {

    private static LinkedList<Entity> entities;

    public static void initialize(){
        entities = new LinkedList<>();
    }

    public static void addEntity(Entity entity){
        entities.push(entity);
    }

    public static void removeEntity(Entity entity){
        entities.remove(entity);
    }

    public static LinkedList<Entity> getEntities(){
        return entities;
    }
}
