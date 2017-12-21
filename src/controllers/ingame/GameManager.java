package controllers.ingame;

import controllers.Master;
import models.drawable.Character;
import models.drawable.Entity;
import models.drawable.EntityType;
import models.game.Direction;
import models.game.maze.Maze;

import java.beans.EventHandler;
import java.util.LinkedList;
import java.util.Random;

public class GameManager {

    private static LinkedList<Entity> entities;
    private static Random random;

    private static Maze maze;

    private static Character player = null;
    private static LinkedList<Character>  npcs = null;
    private static int nbBonus;
    private static LinkedList<Entity> toBeCleared;

    public static void initialize() {
        entities = new LinkedList<>();
        npcs = new LinkedList<>();
        random = new Random();
        nbBonus = 0;
        maze = maze.getInstance();
        toBeCleared = new LinkedList<>();
    }

    public static void addEntity(Entity entity){
        if (entity.getType() == EntityType.PLAYER)
            setPlayer((Character) entity);
        else if(entity.getType() == EntityType.NPC)
        	npcs.push((Character)entity);

        entities.push(entity);
    }

    public static void setPlayer(Character player) {
        GameManager.player = player;
    }

    public static Character getPlayer(){
        return player;
    }

    public static void removeEntity(Entity entity){
        toBeCleared.add(entity);
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
    
    private static boolean isNPC(int x, int y, Direction d) {
    	int newX = x;
    	int newY = y;
    	switch(d) {
    		case EAST:
    			newX = x + 1;
    			break;
    		case WEST:
    			newX = x - 1;
    			break;
    		case NORTH:
    			newY = y - 1;
    			break;
    		case SOUTH:
    			newY = y + 1;
    			break;
    		default:
    			return false;
    	}
    	for(Character npc : npcs) {
    		if(npc.getX() == newX && npc.getY() == newY)
    			return true;
    	}
    	return false;
    }
    
    public static void tryMoveNPCs() {
    	LinkedList<Character> stackChar = new LinkedList<>();
    	int[] bitmap = new int[npcs.size()];
    	int i = 0;
    	for(Character npc: npcs) {
    		stackChar.add(npc);
    		bitmap[i] = 0;
    		i++;
    	}
    	
    	boolean hasMoved = true;
    	while(hasMoved) {
    		hasMoved = false;
	        for(Character npc : stackChar) {
	        	if(bitmap[stackChar.indexOf(npc)] == 1)
	        		continue;
	        	Direction dir = maze.getDirectionForNPC(npc.getX(), npc.getY());
	        	//boolean canMove = isNPC(x, y, dir);
	        	if(dir != null) {
	        		boolean isNPC = isNPC(npc.getX(), npc.getY(), dir);
	        		if(!isNPC) {
	        			hasMoved = tryMoveCharacter(npc, dir);
	        			if(hasMoved)
	        				bitmap[stackChar.indexOf(npc)] = 1;
	        				//stackChar.remove(npc);
	        		}
	        	}
	        }
    	}
    }

    public static boolean tryMoveCharacter(Character character, Direction direction){
        int x = character.getX();
        int y = character.getY();
        boolean able = !maze.getWallsAt(x, y).contains(direction) && !maze.getDoorsAt(x, y, false).contains(direction);
        if (able) {
        	//character.move(direction);
            character.updateFloatingValues(true);
            character.moveLerp(direction);
        	if(character.getType() == EntityType.PLAYER)
        		maze.updateDistFromPlayer(getPlayer().getX(), GameManager.getPlayer().getY());

            
        }
        return able;
    }

    public static void callNextTurn(boolean success){
        if (success){
            tryMoveNPCs();
            EventManager.manageCollision();
        }

        cleanTurn();
        Master.getInstance().render();
    }

    private static void cleanTurn(){
        for (Entity e : toBeCleared)
            entities.remove(e);

        toBeCleared.clear();
    }
}
