package controllers.ingame;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import models.game.Direction;


/**
 * Inputs permet de détecter les entrées claviers de l'utilisateurs et d'envoyer des signaux au joueur.
 */
public class Inputs implements EventHandler<KeyEvent>{

    private boolean lock = false;

    @Override
    // TODO A BOUGER !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public void handle(KeyEvent event) {
    	if (lock)
    	    return;

        boolean hasMoved;
    	if (GameManager.getPlayer() != null && GameManager.getPlayer().isMoving() || lock)
    	    return;
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
            case R:
                GameManager.restart();
            default:
            	hasMoved = false;
                break;
        }

        GameManager.callNextTurn(hasMoved);
        lock = false;
    }

}
