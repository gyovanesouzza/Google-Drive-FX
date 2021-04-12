package controller;

import api.GoogleDrive;
import beans.DriveBean;
import beans.File;
import beans.Table.FileTableBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.PopupWindow;
import javafx.util.Callback;
import service.DriveService;
import util.FileDownload;

import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MyDriveController implements Initializable {

    public static DriveBean files = null;

    @FXML
    ContextMenu contextMenuMyDrive;


    @FXML
    Button btnMyDrive;

    //Quick Access
    //Button's
    @FXML
    Button btnQAccess1;
    @FXML
    Button btnQAccess2;
    @FXML
    Button btnQAccess3;
    @FXML
    Button btnQAccess4;
    @FXML
    Button btnQAccess5;
    @FXML
    Button btnQAccess6;
    //ImagemView's
    @FXML
    ImageView imagemQAccess1;
    @FXML
    ImageView imagemQAccess2;
    @FXML
    ImageView imagemQAccess3;
    @FXML
    ImageView imagemQAccess4;
    @FXML
    ImageView imagemQAccess5;
    @FXML
    ImageView imagemQAccess6;
    //Finish Quick Access

    @FXML
    TableView<FileTableBean> tblOfFile = new TableView<FileTableBean>();

    @FXML
    private TableColumn<FileTableBean, String> colName;
    @FXML
    private TableColumn<FileTableBean, Date> colModified;
    @FXML
    private TableColumn<FileTableBean, String> colSize;

    private static final String SQUARE_BUBBLE =
            "M24 1h-24v16.981h4v5.019l7-5.019h13z";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        files = listFiles();
        FiilQAccess(files);
        fillTable();



        tblOfFile.setOnMouseClicked(event -> {

            // Make sure the user clicked on a populated item
            if (tblOfFile.getSelectionModel().getSelectedItem() != null && event.getClickCount() == 2) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Donwload " + tblOfFile.getSelectionModel().getSelectedItem().getFileName() + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait();
                if(alert.getResult() == ButtonType.YES){
                    try {
                        FileDownload.download(tblOfFile.getSelectionModel().getSelectedItem().getLink(), tblOfFile.getSelectionModel().getSelectedItem().getFileName());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    System.out.println("You donwload on " + tblOfFile.getSelectionModel().getSelectedItem().getLink()+" -  2 "+ tblOfFile.getSelectionModel().getSelectedItem().getFileName());

                }}
                if (event.getButton() == MouseButton.SECONDARY) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + tblOfFile.getSelectionModel().getSelectedItem().getFileName() + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                    alert.showAndWait();
                    if(alert.getResult() == ButtonType.YES) {
                        System.out.println("You delete on "+ tblOfFile.getSelectionModel().getSelectedItem().getFileName());
                        try {
                            String fileId =tblOfFile.getSelectionModel().getSelectedItem().getLink();
                            GoogleDrive.trashFile(tblOfFile.getSelectionModel().getSelectedItem().getLink());
                            tblOfFile.getItems().removeAll();
                            fillTable();
                        } catch (GeneralSecurityException e) {
                            System.out.println("An error occurred: " + e);
                        } catch (IOException e) {
                            System.out.println("An error occurred: " + e);
                        }




                }
            }}
        });


    }


    private ObservableList<FileTableBean> listFilesTableBean() {
        return FXCollections.observableArrayList(DriveService.filesOfMyDriveForTable());
    }


    private DriveBean listFiles() {
        return DriveService.listFilesOfMyDrive();
    }

    private void FiilQAccess(DriveBean files) {
        List<Image> images = new ArrayList<Image>();
        List<String> nameOfDownloads = new ArrayList<String>();

            for (File file : files.getFiles()) {
                if (images.size() <= 7) {
                    if (file.getThumbnailLink() == null) {
                        images.add(new Image(file.getIconLink()));
                    } else {
                        images.add(new Image(file.getThumbnailLink()));
                    }
                    nameOfDownloads.add(file.getName());
                } else {
                    break;
                }
            }

        if (!images.isEmpty()) {

            if (files.getFiles().size() > 1) {
                imagemQAccess1.setImage(images.get(1));
                btnQAccess1.setText(files.getFiles().get(1).getName());
                Tooltip.install(btnQAccess1, makeBubble(new Tooltip(files.getFiles().get(1).getName())));
                btnQAccess1.setOnAction((event) -> {
                    try {
                        FileDownload.download(files.getFiles().get(1).getId(), nameOfDownloads.get(1));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                if (files.getFiles().size() > 2) {
                    imagemQAccess2.setImage(images.get(2));
                    btnQAccess2.setText(files.getFiles().get(2).getName());
                    Tooltip.install(btnQAccess2, makeBubble(new Tooltip(files.getFiles().get(2).getName())));
                    btnQAccess2.setOnAction((event) -> {
                        try {
                            FileDownload.download(files.getFiles().get(2).getId(), nameOfDownloads.get(2));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    if (files.getFiles().size() > 3) {
                        imagemQAccess3.setImage(images.get(3));
                        btnQAccess3.setText(files.getFiles().get(3).getName());
                        Tooltip.install(btnQAccess3, makeBubble(new Tooltip(files.getFiles().get(3).getName())));
                        btnQAccess3.setOnAction((event) -> {
                            try {
                                FileDownload.download(files.getFiles().get(3).getId(), nameOfDownloads.get(3));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                        if (files.getFiles().size() > 4) {
                            imagemQAccess4.setImage(images.get(4));
                            btnQAccess4.setText(files.getFiles().get(4).getName());
                            Tooltip.install(btnQAccess4, makeBubble(new Tooltip(files.getFiles().get(4).getName())));
                            btnQAccess4.setOnAction((event) -> {
                                try {
                                    FileDownload.download(files.getFiles().get(4).getId(), nameOfDownloads.get(4));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                            if (files.getFiles().size() >5) {
                                imagemQAccess5.setImage(images.get(5));
                                btnQAccess5.setText(files.getFiles().get(5).getName());
                                Tooltip.install(btnQAccess5, makeBubble(new Tooltip(files.getFiles().get(5).getName())));
                                btnQAccess5.setOnAction((event) -> {
                                    try {
                                        FileDownload.download(files.getFiles().get(5).getId(), nameOfDownloads.get(5));
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                });
                                if (files.getFiles().size() >6) {
                                    imagemQAccess6.setImage(images.get(6));
                                    btnQAccess6.setText(files.getFiles().get(6).getName());
                                    Tooltip.install(btnQAccess6, makeBubble(new Tooltip(files.getFiles().get(6).getName())));
                                    btnQAccess6.setOnAction((event) -> {
                                        try {
                                            FileDownload.download(files.getFiles().get(6).getId(), nameOfDownloads.get(6));
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    });
                                }
                            }
                        }
                    }
                }
            }
        }
          }

    private void fillTable() {
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
        tblOfFile.setItems(listFilesTableBean());
    }

    //Event's FXML
    @FXML
    private void mouseClicked(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            contextMenuMyDrive.show(btnMyDrive, event.getScreenX(), event.getScreenY());
        }

    }

    @FXML
    private void mouseClickedForDownload(MouseEvent event, String link, String name) {



    }
    //finish Event's FXML

    private Tooltip makeBubble(Tooltip tooltip) {
        tooltip.setStyle("-fx-font-size: 16px; -fx-shape: \"" + SQUARE_BUBBLE + "\";");
        tooltip.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_BOTTOM_LEFT);
        return tooltip;
    }



}
