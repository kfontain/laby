package models.drawable;

/// Acceder aux edges
public class OnOff extends Entity {

    private boolean isOpen;

    public OnOff() {
        super(EntityType.ONOFF);
        isOpen = false;
        setSpriteType(SpriteType.SWITCH_OFF);
    }

    public OnOff(int x, int y) {
        super(x, y, EntityType.ONOFF);
        isOpen = false;
        setSpriteType(SpriteType.SWITCH_OFF);
    }

    @Override
    public void eventCollision() {
        isOpen = !isOpen;
        setSpriteType(isOpen ? SpriteType.SWITCH_ON : SpriteType.SWITCH_OFF);
    }

}