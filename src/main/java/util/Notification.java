package util;

import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class Notification {

    public static void show(String title, String body, NotificationType type){

        TrayNotification notification = new TrayNotification(title,body, type);
        notification.showAndDismiss(Duration.millis(3500));
    }

}
