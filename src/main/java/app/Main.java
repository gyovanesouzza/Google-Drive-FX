package app;

import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.File;


public class Main extends Application {

    public static double xOffset = 0;
    public static double yOffset = 0;
    private int cont = 10000;
    private final Stage splashStage = new Stage(StageStyle.TRANSPARENT);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent splashPane = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/fxml_Assistants/SplashScreen.fxml"));

        Scene scene = new Scene(splashPane);
        scene.setFill(null);
        splashStage.setScene(scene);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        splashStage.show();
        splashStage.setX(dimension.width / 2 - (splashStage.getWidth() / 2));
        splashStage.setY(dimension.height / 2 - (splashStage.getHeight() / 2));

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
                    callToLoginScreen(stage);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }

        };
        splashService.start();
    }


    public void callToLoginScreen(final Stage Stage) throws Exception {
        Scene mainScreen = null;

        if (new File(System.getenv("LOCALAPPDATA") + "\\googleDriveFX\\config\\login.cfg").exists()) {
            mainScreen = new Scene((Parent) FXMLLoader.load(getClass().getClassLoader().getResource("fxml/LoginVerificateScreen.fxml")));

        } else {
            mainScreen = new Scene((Parent) FXMLLoader.load(getClass().getClassLoader().getResource("fxml/LoginScreen.fxml")));

        }

        mainScreen.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override

            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        mainScreen.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage.setX(event.getScreenX() - xOffset);
                Stage.setY(event.getScreenY() - yOffset);
            }
        });
        mainScreen.setFill(null);
        Stage.initStyle(StageStyle.TRANSPARENT);
        Stage.setScene(mainScreen);
        Stage.show();
    }


}
