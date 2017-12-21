package controllers.ingame;

import java.util.LinkedList;

import controllers.Master;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import models.drawable.Entity;
import models.drawable.EntityType;
import models.game.Direction;
import views.ViewFrame;


public class Inputs implements EventHandler<KeyEvent>{

    @Override
    // TODO A BOUGER !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public void handle(KeyEvent event) {
    	boolean hasMoved;
        switch (event.getCode()){
            case UP:
            	hasMoved = GameManager.tryMoveCharacter(GameManager.getPlayer(), Direction.NORTH);
                break;
            case DOWN:
            	hasMoved = GameManager.tryMoveCharacter(GameManager.getPlayer(), Direction.SOUTH);
                break;
            case LEFT:
            	hasMoved = GameManager.tryMoveCharacter(GameManager.getPlayer(), Direction.WEST);
                break;
            case RIGHT:
            	hasMoved = GameManager.tryMoveCharacter(GameManager.getPlayer(), Direction.EAST);
                break;
            default:
            	hasMoved = false;
                break;
        }
        if(hasMoved) {
        	GameManager.tryMoveNPCs();
        }

        GameManager.callNextTurn();
    }

}
