package app;

import controller.controller_auxiliares.SplashScreenController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.awt.*;



public class Main extends Application {

    private double xOffset = 0;
    private double yOffset = 0;
    private int cont = 10;

    private final Stage splashStage = new Stage(StageStyle.TRANSPARENT);


    @Override
    public void start(final Stage stage) throws Exception {

        Parent splashPane = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/fxml_Auxiliares/splashScreen.fxml"));
        Scene scene = new Scene(splashPane);
        scene.setFill(null);
        splashStage.setScene(scene);

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        splashStage.show();
        splashStage.setX(d.width / 2 - (splashStage.getWidth() / 2));
        splashStage.setY(d.height / 2 - (splashStage.getHeight() / 2));

        Service<Boolean> splashService = new Service<Boolean>() {
            @Override
            public void start() {
                super.start();
                splashStage.show();
            }

            @Override
            protected Task<Boolean> createTask() {
                return new Task<Boolean>() {
                    @Override
                    protected Boolean call() throws Exception {
                        Thread.sleep(cont);
                        return true;
                    }
                };
            }

            @Override
            protected void succeeded() {
                splashStage.close();
                try {
                    chamarTelaLogin(stage);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }

        };
        splashService.start();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void chamarTelaLogin(final Stage primaryStage) throws Exception {
        Scene s = new Scene((Parent) FXMLLoader.load(getClass().getClassLoader().getResource("fxml/main.fxml")));
        s.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override

            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        s.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });
        s.setFill(null);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(s);
        primaryStage.show();
    }


}
