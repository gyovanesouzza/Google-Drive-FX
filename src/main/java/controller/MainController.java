package controller;

import beans.About;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import util.ConfigInformation;
import util.ConvertSize;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private static About about;


    //Pane's
    @FXML
    AnchorPane AnchorCenter;


    //Button's of menu
    @FXML
    Button btnMyDrive;
    @FXML
    Button btnComputer;
    @FXML
    Button btnShare;
    @FXML
    Button btnRecent;
    @FXML
    Button btnTrash;

    @FXML
    ProgressBar progressBarStorage;

    @FXML
    Hyperlink linkBuy;

    @FXML
    Label labelStorage;

    @FXML
    ImageView imageUser;
    @FXML
    private Circle circleImagemUser;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (new File(System.getenv("LOCALAPPDATA") + "\\googleDriveFX\\config\\login.cfg").exists()) {
            about = LoginVerificateController.about;
        } else {
            about = LoginController.about;
        }
        try {
            ConfigInformation.configInformation(about.getUser());
        } catch (IOException e) {
            e.printStackTrace();
        }
        linkBuy = new Hyperlink("https://one.google.com/u/3/storage?i=m&utm_source=drive&utm_medium=web&utm_campaign=manage");
        labelStorage.setText(ConvertSize.convertToStringRepresentation(about.getStorageQuota().getUsage()) + " Of " + ConvertSize.convertToStringRepresentation(about.getStorageQuota().getLimit()) + " used");
        progressBarStorage.setProgress(Double.parseDouble(about.getStorageQuota().getUsage()) / Double.parseDouble(about.getStorageQuota().getLimit()));
        circleImagemUser.setStroke(Color.SEAGREEN);
        circleImagemUser.setFill(new ImagePattern(new Image(about.getUser().getPhotoLink(), false)));
        ContextMenu cm = new ContextMenu();
        MenuItem a = new MenuItem("A");
        MenuItem b = new MenuItem("B");
        MenuItem c = new MenuItem("C");
        cm.getItems().addAll(a, b, c);
        circleImagemUser.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                cm.show(circleImagemUser, t.getScreenX(), t.getSceneY());
            }
        });
    }

    @FXML
    private void show(MouseEvent evt) {
        try {
            if (evt.getSource() == btnMyDrive) {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/MyDriveScreen.fxml"));
                AnchorCenter.getChildren().setAll(pane);
            } else if (evt.getSource() == btnComputer) {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/X.fxml"));
                AnchorCenter.getChildren().setAll(pane);
            } else if (evt.getSource() == btnShare) {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/x.fxml"));
                //AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/ShareScreen.fxml")); In development and translation
                AnchorCenter.getChildren().setAll(pane);

            } else if (evt.getSource() == btnRecent) {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/X.fxml"));
                AnchorCenter.getChildren().setAll(pane);
            } else if (evt.getSource() == btnTrash) {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/TrashScreen.fxml"));
                AnchorCenter.getChildren().setAll(pane);
            }
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

}
