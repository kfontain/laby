package models.drawable;

import controllers.ingame.GameManager;
import models.game.Direction;

/**
 * Character est la classe représentant les entités mobiles dans la labyrinthe, le joueur et les ennemis.
 * @see Entity
 */
public class Character extends Entity{

    /**
     * Constructeur de la classe Character.
     * @param x position en x du personnage.
     * @param y position en y du personnage.
     * @param isPlayer le personnage est joueur si vrai, ennemi si faux.
     */
    public Character(int x, int y, boolean isPlayer){
        super(x, y, (isPlayer ? EntityType.PLAYER : EntityType.NPC));
        setSpriteType((isPlayer ? SpriteType.PLAYER : SpriteType.NPC));
    }

    public Character(boolean isPlayer){
        super((isPlayer ? EntityType.PLAYER : EntityType.NPC));
        setSpriteType((isPlayer ? SpriteType.PLAYER : SpriteType.NPC));
    }

    public void moveUp(){
        setY(getY() - 1);
    }

    public void moveDown(){
        setY(getY() + 1);
    }

    public void moveRight(){
        setX(getX() + 1);
    }

    public void moveLeft(){
        setX(getX() - 1);
    }

    public void moveLerp(Direction direction){
        setMovingToward(direction);
        setMoving(true);
    }
    
    public void move(Direction d) {
    	switch (d) {
        case EAST:
            moveRight();
            break;
        case SOUTH:
            moveDown();
            break;
        case WEST:
            moveLeft();
            break;
        case NORTH:
            moveUp();
            break;
    	default:
    		break;
    	}
    	
    }

    @Override
    public void eventCollision(Entity collider) {
        switch (collider.getType()) {
            case PLAYER:
                break;
            case NPC:
                if (getType() == EntityType.PLAYER)
                    GameManager.callGameOver();
                break;
            case DOOR:
                break;
            case ONOFF:
                break;
            case BONUS:
                break;
        }
    }
}
