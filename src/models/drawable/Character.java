package models.drawable;

public class Character extends Entity{

    public Character(int x, int y, boolean isPlayer){
        super(x, y, (isPlayer ? EntityType.PLAYER : EntityType.NPC));
        setSpriteType((isPlayer ? SpriteType.PLAYER : SpriteType.NPC));
    }
}
