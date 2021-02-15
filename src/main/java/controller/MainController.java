package controller;

import api.GoogleDrive;
import app.Main;
import beans.About;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import util.ConfigInformation;
import util.ConvertSize;
import util.Notification;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private static About about;


    //Pane's
    @FXML
    AnchorPane AnchorCenter;


    //Button's of menu
    @FXML
    Button btnUpload;

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
        MenuItem a = new MenuItem("Disconnect");

        cm.getItems().addAll(a);



        /*circleImagemUser.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {

                System.out.println((Main.xOffset)+"    -     " +(Main.yOffset));

                System.out.println((t.getX() - Main.xOffset)+"    -     " +( t.getY()-Main.yOffset));
                cm.show(circleImagemUser, (t.getX() - Main.xOffset),( t.getY()-Main.yOffset));
            }
        });*/


        a.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                ConfigInformation.DeleteConfig();
                FileChooser fileChooser = new FileChooser();
                Stage stage = (Stage) ((MenuItem) actionEvent.getTarget()).getParentPopup().getScene().getWindow();
                fileChooser.showSaveDialog(((MenuItem) actionEvent.getTarget()).getParentPopup().getScene().getWindow());

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


    @FXML
    private void uploadFiles(ActionEvent evt) throws GeneralSecurityException, IOException {
        FileChooser fileChooser = new FileChooser();
        List<File> files = fileChooser.showOpenMultipleDialog(null);
        if (files != null) {
            com.google.api.services.drive.model.File fileMetadata = new com.google.api.services.drive.model.File();

            for (File f : files) {
                fileMetadata.setName(f.getName());
                FileContent mediaContent = new FileContent("image/jpeg", f);
                com.google.api.services.drive.model.File file = GoogleDrive.uploadOfFiles(fileMetadata, mediaContent);

                Notification.show("Upload file",f.getName() + " file successfully uploaded\n", NotificationType.SUCCESS);

            }
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/MyDriveScreen.fxml"));
            AnchorCenter.getChildren().setAll(pane);
        }

    }

}
