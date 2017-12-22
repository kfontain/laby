package views;

import controllers.ingame.GameManager;
import controllers.ingame.Inputs;
import controllers.ingame.SpriteManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.drawable.Entity;
import models.drawable.SpriteType;

import java.util.LinkedList;
import java.util.Vector;


public class ViewFrame {

    private static ViewFrame vf;

    static final int SPAN = 4; // Pixels for a unit
    static final int WALL = 2; // thickness of the walls (in units)
    static final int CELL = 9; // size of the cells (in units)
    public static final Color WALL_COLOR = Color.BURLYWOOD;
    private static Pane pane = new Pane();
    private static Vector<Node> drawnEntities;
    private static Timeline animator;
    private static Scene scene;

    public static void setIsAnimating(boolean isAnimating) {
        ViewFrame.isAnimating = isAnimating;
        animator.play();
    }

    private static boolean isAnimating = false;

    private ViewFrame() {
        drawnEntities = new Vector<>();
        animator = new Timeline(new KeyFrame(Duration.millis(16), event -> ViewFrame.drawEntities()));
        animator.setCycleCount(5);
    }

    public static void clear(){
        pane.getChildren().clear();
    }

    public static void drawFrame(Stage stage, int nbrX, int nbrY) {
        
        scene = new Scene(pane, ((WALL + CELL) * nbrX + WALL) * SPAN, ((WALL + CELL) * nbrY + WALL) * SPAN);
        scene.setFill(Color.BEIGE);
        Rectangle square;
        stage.setScene(scene);

        //TODO:  A BOUGER
        scene.addEventFilter(KeyEvent.KEY_PRESSED, new Inputs());
        //

        square = new Rectangle (0, 0, SPAN * (nbrX * (CELL + WALL) +WALL), WALL * SPAN);
        square.setFill(WALL_COLOR);
        pane.getChildren().add(square);
        square = new Rectangle(0, SPAN * (nbrY * (CELL + WALL)), SPAN * (nbrX * (CELL + WALL) + WALL), WALL * SPAN);
        square.setFill(WALL_COLOR);
        pane.getChildren().add(square);
        square = new Rectangle(0, 0, WALL * SPAN, SPAN * (nbrY * (CELL+WALL) + WALL));
        square.setFill(WALL_COLOR);
        pane.getChildren().add(square);
        square = new Rectangle(SPAN * (nbrX * (CELL + WALL)), 0, WALL * SPAN, SPAN * (nbrY * (CELL + WALL) + WALL));
        square.setFill(WALL_COLOR);
        pane.getChildren().add(square);
        for (int x = 0 ; x < nbrX-1 ; ++x) {
            int offsetX = ((WALL + CELL) + (WALL + CELL) * x) * SPAN;
            for (int y = 0 ; y < nbrY-1 ; ++y) {
                int offsetY = ((WALL + CELL) + (WALL + CELL) * y) * SPAN;
                square = new Rectangle(offsetX, offsetY, WALL * SPAN, WALL * SPAN);
                square.setFill(WALL_COLOR);
                pane.getChildren().add(square);
            }
        }

        stage.show();
    }

    public static void drawWall(int xs, int ys, int xt, int yt, Paint color) {
        int x = 0, y = 0, xspan = 0, yspan = 0;
        //Pane pane = new Pane();
        if (ys==yt) {
            x = ((WALL + CELL) + (WALL + CELL) * ((int)(xs+xt) / 2)) * SPAN;
            y = (WALL + ys * (WALL + CELL)) * SPAN;
            xspan = WALL * SPAN;
            yspan = CELL * SPAN;
            Rectangle square = new Rectangle (x, y, xspan, yspan);
            square.setFill(color);
            pane.getChildren().add(square);
        }
        else if (xs==xt) {
            x = (WALL + xs * (WALL + CELL)) * SPAN;
            y = ((WALL + CELL) + (WALL + CELL) * ((int)(ys+yt) / 2)) * SPAN;
            xspan = CELL * SPAN;
            yspan = WALL * SPAN;
            Rectangle square = new Rectangle(x, y, xspan, yspan);
            square.setFill(color);
            pane.getChildren().add(square);
        }
    }

    public static ViewFrame getInstance() {
        if (vf == null) vf = new ViewFrame();
        return vf;
    }

    public static void drawSprite(double x, double y, Image sprite){
        double xf = WALL * (x + 1) + CELL * x;
        double yf = WALL * (y + 1) + CELL * y;
        Canvas canvas = new Canvas(CELL * SPAN, CELL * SPAN);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(sprite, 0, 0);
        canvas.setLayoutX(xf * SPAN);
        canvas.setLayoutY(yf * SPAN);
        drawnEntities.add(canvas);
    }

    public static void drawEntities(){
        if (!isAnimating){
            return;
        }
        LinkedList<Entity> entities = GameManager.getEntities();
        cleanEntities();
        for (Entity e : entities){
            isAnimating = e.updateFloatingValues(true) || isAnimating;
            SpriteType t = e.getSpriteType();
            drawSprite(e.getDoubleX(), e.getDoubleY(), SpriteManager.getSprite(t));
        }

        renderEntities();
    }

    public static void cleanEntities(){
        for (Node n : drawnEntities)
            pane.getChildren().remove(n);

        drawnEntities = new Vector<>();
    }

    public static void renderEntities(){
        for (Node n : drawnEntities)
            pane.getChildren().add(n);
    }
}
