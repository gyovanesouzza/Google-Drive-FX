package api;

import beans.DriveBean;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.About;
import com.google.api.services.drive.model.FileList;
import com.google.gson.Gson;

import java.io.*;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

public class GoogleDrive {

    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);
    private static final URL CREDENTIALS_FILE_PATH = GoogleDrive.class.getClassLoader().getResource("auth/credentials.json");
    private static final String APPLICATION_NAME = "Google Drive API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final Gson gson = new Gson();


    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        InputStream in = CREDENTIALS_FILE_PATH.openStream();

        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .setApprovalPrompt("force")
                .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();

        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    public static void downloadOfFile(String fileId, String path) throws GeneralSecurityException, IOException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        Drive driveService = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

        OutputStream outputStream = new FileOutputStream(path);
        driveService.files().get(fileId)
                .executeMediaAndDownloadTo(outputStream);

        outputStream.flush();
        outputStream.close();
    }

    public static DriveBean filesOfMyDrive() throws GeneralSecurityException, IOException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        String pageToken = null;
        DriveBean files = new DriveBean();

        Drive driveService = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

        do {
            FileList result = driveService.files().list()
                    .setQ("'root' in parents and mimeType != 'application/vnd.google-apps.folder' and trashed = false")
                    .setSpaces("drive")
                    .setFields("nextPageToken,files")
                    .setPageToken(pageToken)
                    .execute();

            try {
                files = gson.fromJson(result.toString(), DriveBean.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            pageToken = result.getNextPageToken();
        } while (pageToken != null);

        return files;
    }


    public static beans.About aboutOfMe() throws GeneralSecurityException, IOException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        beans.About about = new beans.About();

        Drive driveService = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
        About result = driveService.about()
                .get()
                .setFields("user,storageQuota").execute();
        System.out.println(result.toString());
        try {
            about = gson.fromJson(result.toString(), beans.About.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return about;
    }


}