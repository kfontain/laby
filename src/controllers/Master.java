package controllers;

import javafx.stage.Stage;
import views.ViewFrame;

public class Master {

    private static Master mst;
    private ViewFrame vf;

    private Master() {
        vf = ViewFrame.getInstance();
    }

    public static Master getInstance() {
        if (mst == null) mst = new Master();
        return mst;
    }

    public void start(Stage primaryStage) {
        ViewFrame.getInstance().drawFrame(primaryStage, 10, 10);
    }

}
