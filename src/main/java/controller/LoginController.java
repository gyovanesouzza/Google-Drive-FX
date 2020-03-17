package controller;

import api.GoogleDrive;
import beans.About;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    static About about;
    @FXML
    private HBox toolBar;
    @FXML
    private JFXButton btnLoginGoogle;
    private double xOffset = 0;
    private double yOffset = 0;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            toolBar.getChildren().setAll((HBox) FXMLLoader.load(getClass().getClassLoader().getResource("fxml/fxml_Assistants/ToolbarScreen.fxml")));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    @FXML
    private void signIn(ActionEvent event) {
        try {
            about = GoogleDrive.aboutOfMe();
            signIn((Node) event.getSource());
        } catch (GeneralSecurityException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.show();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Please accept all permissions requested by DriveFX to access the software");
            alert.show();
        }
    }


    private void signIn(Node node) throws IOException {
        final Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/HomeScreen.fxml"));

        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
