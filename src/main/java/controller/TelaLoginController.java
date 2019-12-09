package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
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
import java.util.ResourceBundle;

public class TelaLoginController implements Initializable {

    @FXML
    private HBox acBarraTitulo;
    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXPasswordField txtSenha;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXButton btnLoginGoogle;

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            HBox barra = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Fxml_Auxiliares/AbaFechaeMinimizar.fxml"));
            acBarraTitulo.getChildren().setAll(barra);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void mouseClickedLogin(MouseEvent evt) {
        if (evt.getSource() == btnLogin) {
            if (txtEmail.getText().equals("root") && txtSenha.getText().equals("root")) {
                Node node = (Node) evt.getSource();
                try {
                    entrar(node);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informação");
                alert.setHeaderText(null);
                alert.setContentText("Usuario Errado");
                alert.show();
            }
        }

    }

    private void entrar(Node node) throws IOException {
        final Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/main.fxml"));

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
