package models.drawable;

import controllers.ingame.GameManager;

public class Door extends Entity{
    public Door(int x, int y) {
        super(x, y, EntityType.DOOR);
        this.setSpriteType(SpriteType.EXIT);
    }

    public Door() {
        super(EntityType.DOOR);
        this.setSpriteType(SpriteType.EXIT);
    }

    @Override
    public void eventCollision() {
        System.out.println("Sortie");
    }
}


