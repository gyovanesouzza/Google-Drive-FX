package util;

import beans.User;
import com.google.gson.Gson;

import java.io.*;

public class ConfigInformation {
    private final static String LOCAL = System.getenv("LOCALAPPDATA") + "\\googleDriveFX\\config";
    private static final String PATH_FILE_CONFIG_LOGIN = LOCAL + "\\login.cfg";


   public static User showConfigInformation() throws IOException, ClassNotFoundException {
        File config = new File(PATH_FILE_CONFIG_LOGIN);
        FileReader fis = new FileReader(config);
        BufferedReader brs = new BufferedReader(fis);
        String conteudo = "";
        while (brs.ready()) {
            conteudo += brs.readLine() + "\n";
        }
        User user = new Gson().fromJson(conteudo, User.class);
        return user;
    }


    public static void configInformation(User user) throws IOException {
        File config = new File(PATH_FILE_CONFIG_LOGIN);
        if(!config.exists()){
            FileOutputStream fos = new FileOutputStream(config);
            fos.write(new Gson().toJson(user).getBytes());
            fos.close();
        }
    }}