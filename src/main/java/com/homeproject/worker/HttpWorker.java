package com.homeproject.worker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpWorker {

    private final static String URL = "https://randomuser.me/api/?exc=login,email,registered,phone,id,picture";
    private final static int TIMEOUT=10000;

    public StringBuffer getResponse() {

        StringBuffer response = new StringBuffer();

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(URL).openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(TIMEOUT);
            connection.setReadTimeout(TIMEOUT);

            try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {

                String inputLine;
                while ((inputLine = bufferedReader.readLine()) != null) {
                    response.append(inputLine);
                }
            }
            return response;

        } catch (IOException e) {
            System.err.println("Соединение с интенетом прервано, данные формируются из локальной БД...");
        }
        return null;
    }
}
