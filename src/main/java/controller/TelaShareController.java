package controller;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TelaShareController implements Initializable {


    @FXML
    Button btnGradeouList;

    @FXML
    ImageView imgViewGradeouList;

    @FXML
    Pane paneCentral;
    @FXML
    Pane paneGrade;
    @FXML
    AnchorPane paneList;


    boolean opcImgButton = true;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    @FXML
    private void show(MouseEvent event) {
        loadShowAndIcon();

    }

    @FXML
    private void mouseGradeList(ActionEvent event) {
        imgViewGradeouList.setImage(loadShowAndIcon());
    }

    private Image loadShowAndIcon() {
        Image image;
        Pane pane;
        try {
            if (opcImgButton) {
                opcImgButton = false;
                image = new Image(getClass().getResource("/icons/categoria_lista.png").openStream());
                pane = FXMLLoader.load(getClass().getResource("/fxml/TelaShareGrade.fxml"));
                paneCentral.getChildren().setAll(pane);
            } else {
                opcImgButton = true;
                image = new Image(getClass().getResource("/icons/categoria_grade.png").openStream());
                pane = FXMLLoader.load(getClass().getResource("/fxml/TelaShareList.fxml"));
                paneCentral.getChildren().setAll(pane);
            }
        } catch (IOException e) {
            return imgViewGradeouList.getImage();
        }

        return image;
    }
}
