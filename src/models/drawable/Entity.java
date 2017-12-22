package models.drawable;

import models.game.Direction;

/**
 * Entity est une classe abstraite dont toutes les entités du labyrinthe héritent. Elle définie les fonctionnements
 * généraux d'une entité.
 */
public abstract class Entity {
    private final static double ANIMATION_SPEED = 0.2;

    private double x;
    private double y;
    private int toX;
    private int toY;
    private EntityType type;
    private SpriteType spriteType;
    private Direction movingToward;
    private boolean isMoving;

    public Entity (int x, int y, EntityType type){
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public Entity (EntityType type){
        this.type = type;
    }

    public EntityType getType() {
        return type;
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public double getDoubleX(){ return x;}

    public double getDoubleY(){ return y;}

    public Direction getMovingToward() {
        return movingToward;
    }

    public void setMovingToward(Direction movingToward) {
        this.movingToward = movingToward;
        switch (movingToward) {
            case NORTH:
                toY = getY() - 1;
                toX = getX();
                break;
            case SOUTH:
                toY = getY() + 1;
                toX = getX();
                break;
            case EAST:
                toX = getX() + 1;
                toY = getY();
                break;
            case WEST:
                toX = getX() - 1;
                toY = getY();
                break;
        }
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public boolean updateFloatingValues(boolean lerp) {
        if (!isMoving)
            return false;

        double vx = (lerp ? 0.2 : 1 - (getX() - getDoubleX()));
        double vy = (lerp ? 0.2 : 1 - (getY() - getDoubleY()));
        switch (movingToward) {
            case NORTH:
                y -= vy;
                break;
            case SOUTH:
                y += vy;
                break;
            case EAST:
                x += vx;
                break;
            case WEST:
                x -= vx;
                break;
        }

        if (Math.abs(getDoubleX() - toX) <= ANIMATION_SPEED / 2 && Math.abs(getDoubleY() - toY) <= ANIMATION_SPEED / 2)
        {
            setMoving(false);
            x = toX;
            y = toY;
        }

        return true;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getGoalX() {
        return toX;
    }

    public int getGoalY() {
        return toY;
    }

    public void setSpriteType(SpriteType spriteType) {
        this.spriteType = spriteType;
    }

    public SpriteType getSpriteType() {
        return spriteType;
    }

    public boolean ifCollision(Entity e){
        return (this.toX == e.toX && this.toY == e.toY);
    }

    public abstract void eventCollision(Entity collider);
}
