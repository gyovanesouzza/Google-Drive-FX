package controller.controller_auxiliares;


import animatefx.animation.Bounce;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;


public class SplashScreenController implements Initializable {
    @FXML
    Circle circule1;
    @FXML
    Circle circule2;
    @FXML
    Circle circule3;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Bounce(circule1).setCycleDuration(4).setCycleCount(8).setDelay(Duration.valueOf("500ms")).play();
        new Bounce(circule2).setCycleDuration(4).setCycleCount(8).setDelay(Duration.valueOf("1000ms")).play();
        new Bounce(circule3).setCycleDuration(4).setCycleCount(8).setDelay(Duration.valueOf("1100ms")).play();
    }
}



