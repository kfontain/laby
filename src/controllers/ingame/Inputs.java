package controllers.ingame;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import models.game.Direction;
import views.ViewFrame;


public class Inputs implements EventHandler<KeyEvent>{

    @Override
    public void handle(KeyEvent event) {
        switch (event.getCode()){
            case UP:
                GameManager.tryMoveCharacter(GameManager.getPlayer(), Direction.NORTH);
                ViewFrame.drawEntities();
                break;
            case DOWN:
                GameManager.tryMoveCharacter(GameManager.getPlayer(), Direction.SOUTH);
                ViewFrame.drawEntities();
                break;
            case LEFT:
                GameManager.tryMoveCharacter(GameManager.getPlayer(), Direction.WEST);
                ViewFrame.drawEntities();
                break;
            case RIGHT:
                GameManager.tryMoveCharacter(GameManager.getPlayer(), Direction.EAST);
                ViewFrame.drawEntities();
                break;
            default:
                return;
        }
    }

}
