package models.drawable;

import controllers.ingame.GameManager;

import java.util.Random;

/**
 * Bonus est la classe des entités représentants les bonbons à ramasser dans le labyrinthe.
 * @see Entity
 */
public class Bonus extends Entity{
    public Bonus(int x, int y) {
        super(x, y, EntityType.BONUS);
        randomizeSprite();
    }

    public Bonus() {
        super(EntityType.BONUS);
        randomizeSprite();
    }

    /**
     * randomizeSprite permet d'affecter à une instance de Bonus, une image de bonbon aléatoire.
     */
    public void randomizeSprite(){
        Random random = GameManager.getRandom();
        SpriteType[] types = {SpriteType.CANDY1, SpriteType.CANDY2, SpriteType.CANDY3, SpriteType.CANDY4};
        setSpriteType(types[random.nextInt(4)]);
    }


    /**
     * eventCollision dans le cas d'un Bonus, supprime le Bonus en question lorsque le joueur rentre en collision avec.
     * @param collider l'entité venant d'entrer en collision avec le Bonus.
     */
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









