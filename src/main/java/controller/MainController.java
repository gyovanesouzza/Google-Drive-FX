package controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    //Pane's
    @FXML
    AnchorPane paneCentral;

    //Label's


    //Button's
    @FXML
    Button btnMeuDrive;
    @FXML
    Button btnComputador;
    @FXML
    Button btnShare;
    @FXML
    Button btnRecente;
    @FXML
    Button btnLixeira;

    @FXML
    ProgressBar progressBarCapacidade;

    @FXML
    Hyperlink linkCompra;


    @FXML
    private void show(MouseEvent evt) {
        try {
            if (evt.getSource() == btnMeuDrive) {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/TelaMeuDrive.fxml"));
                paneCentral.getChildren().setAll(pane);
            } else if (evt.getSource() == btnComputador) {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/X.fxml"));
                paneCentral.getChildren().setAll(pane);
            } else if (evt.getSource() == btnShare) {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/TelaShare.fxml"));
                paneCentral.getChildren().setAll(pane);

            } else if (evt.getSource() == btnRecente) {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/X.fxml"));
                paneCentral.getChildren().setAll(pane);
            } else if (evt.getSource() == btnLixeira) {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/TelaLixeir.fxml"));
                paneCentral.getChildren().setAll(pane);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage()+ "Não foi possível mudar de  tela");
        }

//         else if (evt.getSource() == btnMeuDrive) {
//
//        } else if (evt.getSource() == btnMeuDrive) {
//
//        }


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        linkCompra = new Hyperlink("https://one.google.com/u/3/storage?i=m&utm_source=drive&utm_medium=web&utm_campaign=manage");

    }


}
