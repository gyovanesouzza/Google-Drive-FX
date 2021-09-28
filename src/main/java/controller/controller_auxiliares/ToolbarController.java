package controller.controller_auxiliares;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ToolbarController implements Initializable {

    @FXML
    private JFXButton btnMinimize;

    @FXML
    private JFXButton btnCloser;

    @FXML
    void actionCloser(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void actionMinimize(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
