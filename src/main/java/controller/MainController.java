package controller;

import beans.About;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.ConvertSize;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        about = LoginController.about;
        linkBuy = new Hyperlink("https://one.google.com/u/3/storage?i=m&utm_source=drive&utm_medium=web&utm_campaign=manage");

        labelStorage.setText(ConvertSize.convertToStringRepresentation(about.getStorageQuota().getUsage()) + " Of " + ConvertSize.convertToStringRepresentation(about.getStorageQuota().getLimit()) + " used");
        progressBarStorage.setProgress(Double.parseDouble(about.getStorageQuota().getUsage()) / Double.parseDouble(about.getStorageQuota().getLimit()));
        Image image = new Image(about.getUser().getPhotoLink());
        imageUser.setImage(image);


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
            alert.show();        }
    }

}
