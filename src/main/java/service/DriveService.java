package service;

import api.GoogleDrive;
import beans.DriveBean;
import beans.File;
import beans.Table.FileTableBean;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DriveService {

    public static DriveBean listFilesOfMyDrive() {

        try {
            return GoogleDrive.filesOfMyDrive();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<FileTableBean> filesOfMyDriveForTable() {

        List<FileTableBean> retorno = new ArrayList<FileTableBean>();
        try {
            DriveBean driveBean = listFilesOfMyDrive();
            System.out.println(driveBean.getFiles().size());
            for (int i = 0; i <= driveBean.getFiles().size(); i++) {
                retorno.add(test(driveBean.getFiles().get(i)));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        return retorno;
    }


    private static FileTableBean test(File file) {

        FileTableBean retorno = null;
        try {

            retorno = new FileTableBean(file.getName(), file.getModifiedTime(), file.getSize(),file.getId());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return retorno;

    }
}
