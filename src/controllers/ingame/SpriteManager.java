package controllers.ingame;

import javafx.scene.image.Image;
import models.drawable.SpriteType;

import java.util.HashMap;

public class SpriteManager {
    private static HashMap<SpriteType, String> spriteTable;

    public static void initialize(String directoryPath){
        String path = directoryPath;
        spriteTable = new HashMap<>();
        spriteTable.put(SpriteType.PLAYER, path + "/player.png");
        spriteTable.put(SpriteType.NPC, path + "/bad.png");
        spriteTable.put(SpriteType.CANDY1, path + "/candy-1.png");
        spriteTable.put(SpriteType.CANDY2, path + "/candy-2.png");
        spriteTable.put(SpriteType.CANDY3, path + "/candy-3.png");
        spriteTable.put(SpriteType.CANDY4, path + "/candy-4.png");
        spriteTable.put(SpriteType.SWITCH_OFF, path + "/button_close.png");
        spriteTable.put(SpriteType.SWITCH_ON, path + "/button_open.png");
        spriteTable.put(SpriteType.EXIT, path + "/door_open.png");
    }

    public static Image getSprite(SpriteType type){
        String path = spriteTable.get(type);
        Image i = new Image("file:" + path);
        return i;
    }
}
