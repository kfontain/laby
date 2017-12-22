package models.drawable;

import controllers.ingame.GameManager;

import java.util.Random;

public class Bonus extends Entity{
    public Bonus(int x, int y) {
        super(x, y, EntityType.BONUS);
        randomizeSprite();
    }

    public Bonus() {
        super(EntityType.BONUS);
        randomizeSprite();
    }

    public void randomizeSprite(){
        Random random = GameManager.getRandom();
        SpriteType[] types = {SpriteType.CANDY1, SpriteType.CANDY2, SpriteType.CANDY3, SpriteType.CANDY4};
        setSpriteType(types[random.nextInt(4)]);
    }

    @Override
    public void eventCollision(Entity collider) {
        switch (collider.getType()) {
            case PLAYER:
                GameManager.addNbBonus();
                GameManager.removeEntity(this);
                break;
            case NPC:
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









