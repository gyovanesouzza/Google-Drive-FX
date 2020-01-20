package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class TelaMeuDriveController implements Initializable {

    @FXML
    ContextMenu cmMeuDrive;

    @FXML
    Button btnMeuDrive;

    @FXML
    Label lblDataModficicao1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        lblDataModficicao1.setText(new SimpleDateFormat("dd 'de' MMM 'de' yyyy ").format(new Date()));
    }

    @FXML
    private void mouseClicked(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            cmMeuDrive.show(btnMeuDrive, event.getScreenX(), event.getScreenY());
        }

    }

}
