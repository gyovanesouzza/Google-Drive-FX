package beans.Table;

import util.ConvertSize;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileTableBean {

    private SimpleStringProperty fileName;
    private SimpleObjectProperty<Date> lastModified = null;
    private SimpleStringProperty size;
    private Button btnDowloand;
    private Button btnMore;
    private String link;

    public FileTableBean(String fileName, Date lastModified, String size, String link) throws ParseException {
        this.fileName = new SimpleStringProperty(fileName);
        this.lastModified = new SimpleObjectProperty<Date>(lastModified);
        this.size = new SimpleStringProperty(ConvertSize.convertToStringRepresentation(size));
        this.btnDowloand = new Button("download");
        this.link = link;
        this.btnMore = new Button("...");

    }

    public String getFileName() {
        return fileName.get();
    }

    public StringProperty fileNameProperty() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName.set(fileName);
    }

    public Date getLastModified() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            System.out.println(df.parse(lastModified.get().toString()));
            return df.parse(lastModified.get().toString());
        } catch (Exception e) {
            return null;
        }

    }

    public ObjectProperty<Date> lastModifiedProperty() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified.set(lastModified);
    }

    public String getSize() {
        return size.get();
    }

    public StringProperty sizeProperty() {
        return size;
    }

    public void setSize(String size) {
        this.size.set(size);
    }

    public Button getBtnDowloand() {


        return btnDowloand;
    }

    public void setBtnDowloand(Button btnDowloand) {
        this.btnDowloand = btnDowloand;
    }

    public Button getMore() {
        return btnMore;
    }

    public void setMore(Button btnMore) {
        this.btnMore = btnMore;
    }

    public String getLink() {


        return link;
    }

}
