package util;

import api.GoogleDrive;
import com.github.plushaze.traynotification.notification.Notifications;
import javafx.scene.control.Alert;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class FileDownload {
    private final static String LOCAL = System.getenv("LOCALAPPDATA");
    private final static String PASTA = "\\googleDriveFX\\fils";

    public static void download(String fileID, String nameOfFile) throws IOException {
        File fileForDownload = new File(LOCAL + PASTA + "\\" + nameOfFile);

        File pasta = new File(LOCAL + PASTA);

        if (!pasta.exists()) {
            pasta.mkdirs();
        }
        try {
            GoogleDrive.downloadOfFile(fileID, fileForDownload.getPath());
        } catch (GeneralSecurityException e) {
            Notification.show("Download file",nameOfFile + " file download error", Notifications.ERROR);
        }
        Notification.show("Download file",nameOfFile + " file successfully download", Notifications.SUCCESS);



    }
}
