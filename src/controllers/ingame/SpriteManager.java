package controllers.ingame;

import javafx.scene.image.Image;
import models.drawable.SpriteType;

import java.util.HashMap;

public class SpriteManager {
    private static HashMap<SpriteType, String> spriteTable;

    public static void initialize(String directoryPath){
        spriteTable = new HashMap<>();
        spriteTable.put(SpriteType.PLAYER,  "res/player.png");
    }

    public static Image getSprite(SpriteType type){
        String path = spriteTable.get(type);
        Image i = new Image("file:" + path);
        return i;
    }
}
