package controllers.ingame;

import models.drawable.Character;
import models.drawable.Entity;
import models.drawable.EntityType;
import models.game.Direction;
import models.game.maze.Maze;

import java.util.LinkedList;
import java.util.Random;

public class GameManager {

    private static LinkedList<Entity> entities;
    private static Random random;

    private static Maze maze;

    private static Character player = null;
    private static LinkedList<Character>  NPCs = null;
    private static int nbBonus;

    public static void initialize() {
        entities = new LinkedList<>();
        NPCs = new LinkedList<>();
        random = new Random();
        nbBonus = 0;
        maze = maze.getInstance();
    }

    public static void addEntity(Entity entity){
        if (entity.getType() == EntityType.PLAYER)
            setPlayer((Character) entity);
        else if(entity.getType() == EntityType.NPC)
        	NPCs.push((Character)entity);

        entities.push(entity);
    }

    public static void setPlayer(Character player) {
        GameManager.player = player;
    }

    public static Character getPlayer(){
        return player;
    }

    public static void removeEntity(Entity entity){
        entities.remove(entity);
    }

    public static LinkedList<Entity> getEntities(){
        return entities;
    }

    public static Random getRandom() {
        return random;
    }

    public static Maze getMaze() {
        return maze;
    }

    public static void addNbBonus(){
        nbBonus += 1;
    }
    
    public static void tryMoveNPCs() {
        for(Character npc : NPCs) {
        	Direction dir = maze.getDirectionForNPC(npc.getX(), npc.getY());
        	boolean hasMoved = tryMoveCharacter(npc, dir);
        	EventManager.manageCollision();
        }
    }

    public static boolean tryMoveCharacter(Character character, Direction direction){
        int x = character.getX();
        int y = character.getY();
        boolean able = !maze.getWallsAt(x, y).contains(direction);
        if (able) {
        	//character.move(direction);
            switch (direction) {
                case EAST:
                    character.moveRight();
                    break;
                case SOUTH:
                    character.moveDown();
                    break;
                case WEST:
                    character.moveLeft();
                    break;
                case NORTH:
                    character.moveUp();
                    break;
            }
            EventManager.manageCollision();
            maze.updateDistFromPlayer(getPlayer().getX(), GameManager.getPlayer().getY());
            /*
            for(Character npc : NPCs) {
            	Direction dir = maze.getDirectionForNPC(npc.getX(), npc.getY());
            	npc.move(dir);
            }
            */
            EventManager.manageCollision();

            
        }
        return able;
    }
}
