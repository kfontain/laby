package models.drawable;

import controllers.ingame.GameManager;

public class Character extends Entity{

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
}
