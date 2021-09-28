package util;

import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;
import javafx.util.Duration;


public class Notification {

    public static void show(String title, String body, Notifications type){

        TrayNotification notification = new TrayNotification(title,body, type);
        notification.showAndDismiss(Duration.millis(3500));
    }

}
