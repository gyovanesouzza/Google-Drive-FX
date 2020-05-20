package controller;

import api.GoogleDrive;
import beans.About;
import beans.User;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import util.ConfigInformation;

import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ResourceBundle;

public class LoginVerificateController implements Initializable {
    static About about;
    @FXML
    private HBox toolBar;
    @FXML
    private JFXButton btnLoginGoogle;
    @FXML
    private Circle circleImagemUser;
    @FXML
    private Label labelUser;

    private double xOffset = 0;
    private double yOffset = 0;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            User user = ConfigInformation.showConfigInformation();
            circleImagemUser.setStroke(Color.SEAGREEN);
            circleImagemUser.setFill(new ImagePattern(new Image(user.getPhotoLink(),false)));
            labelUser.setText(user.getDisplayName());
            toolBar.getChildren().setAll((HBox) FXMLLoader.load(getClass().getClassLoader().getResource("fxml/fxml_Assistants/ToolbarScreen.fxml")));
        } catch (IOException | ClassNotFoundException e) {
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
            e.printStackTrace();
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

        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
