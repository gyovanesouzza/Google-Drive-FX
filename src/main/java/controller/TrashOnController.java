package controller;

import api.GoogleDrive;
import beans.DriveBean;
import beans.Table.FileTableBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import service.DriveService;
import util.FileDownload;

import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class TrashOnController implements Initializable {

    public static DriveBean files = null;


    @FXML
    TableView<FileTableBean> tblOfTrashOn = new TableView<FileTableBean>();

    @FXML
    private TableColumn<FileTableBean, String> colName;
    @FXML
    private TableColumn<FileTableBean, Date> colModified;
    @FXML
    private TableColumn<FileTableBean, String> colSize;

    //Pane's
    @FXML
    AnchorPane anchorCenter;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        files = listFiles();
        fillTableTrash();

        tblOfTrashOn.setOnMouseClicked(event -> {

            // Make sure the user clicked on a populated item
            if (tblOfTrashOn.getSelectionModel().getSelectedItem() != null && event.getClickCount() == 2) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    String id = tblOfTrashOn.getSelectionModel().getSelectedItem().getLink();
                    String name = tblOfTrashOn.getSelectionModel().getSelectedItem().getFileName();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "restoring " + name + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        try {
                            com.google.api.services.drive.model.File file = GoogleDrive.untrashFile(tblOfTrashOn.getSelectionModel().getSelectedItem().getLink());
                        } catch (IOException | GeneralSecurityException e) {
                            e.printStackTrace();
                        }
                        System.out.println("You restoring on " + id + " -  2 " + name);

                    }
                }
                if (event.getButton() == MouseButton.SECONDARY) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "deleting forever " + tblOfTrashOn.getSelectionModel().getSelectedItem().getFileName() + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        System.out.println("You deleting forever  on " + tblOfTrashOn.getSelectionModel().getSelectedItem().getFileName());
                        try {
                            String fileId = tblOfTrashOn.getSelectionModel().getSelectedItem().getLink();
                            GoogleDrive.deleteFile(fileId);

                        } catch (GeneralSecurityException | IOException e) {
                            System.out.println("An error occurred: " + e);
                        }
                    }
                }
                if (tblOfTrashOn.getItems().size() <= 1) {

                    try {
                        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/TrashOffScreen.fxml"));
                        anchorCenter.getChildren().setAll(pane);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                } else {
                    tblOfTrashOn.getItems().removeAll();
                    fillTableTrash();
                }
            }
        });


    }


    private DriveBean listFiles() {
        return DriveService.listFilesOfTrash();
    }

    private void fillTableTrash() {
        colName.setCellValueFactory(new PropertyValueFactory<FileTableBean, String>("fileName"));
        colModified.setCellFactory(new Callback<TableColumn<FileTableBean, Date>, TableCell<FileTableBean, Date>>() {
            @Override
            public TableCell<FileTableBean, Date> call(TableColumn<FileTableBean, Date> column) {
                TableCell<FileTableBean, Date> cell = new TableCell<FileTableBean, Date>() {
                    private SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                    private String data = null;

                    @Override
                    protected void updateItem(Date item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            this.setText(format.format(item));

                        }
                    }
                };

                return cell;
            }
        });

        colModified.setCellValueFactory(new PropertyValueFactory<FileTableBean, Date>("lastModified"));

        colSize.setCellValueFactory(new PropertyValueFactory<FileTableBean, String>("size"));
        tblOfTrashOn.setItems(listFilesTableBeanTrash());
        tblOfTrashOn.refresh();

    }

    private ObservableList<FileTableBean> listFilesTableBeanTrash() {
        return FXCollections.observableArrayList(DriveService.filesOfMyDriveForTableTrash());
    }
}
