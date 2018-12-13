package kr.co.supp0rtyoo.boostcourse;

import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import kr.co.supp0rtyoo.boostcourse.movieInfo.MovieInfo;

public class NetworkThread extends Thread {
    private final String clientID = "4hK9Ahk3YKTZed2kOjX0";
    private final String clientSecret = "900mS013QH";
    private final String apiUrl = "https://openapi.naver.com/v1/search/movie.json?query=";

    public void run(String text) {
        requestGET(text);
    }

    private void requestGET(String text) {
        URL url;
        try {
            String encodedText = URLEncoder.encode(text, "UTF-8");
            url = new URL(apiUrl + encodedText);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-Naver-Client-Id", clientID);
            connection.setRequestProperty("X-Naver-Client-Secret", clientSecret);

            int responseCode = connection.getResponseCode();

            switch(responseCode) {
                case 400:
                    break;
                case 404:
                    break;
                case 500:
                    break;
                case 200:
                    Gson gson = new Gson();
                    MovieInfo movieInfo = gson.fromJson(new BufferedReader(new InputStreamReader(connection.getInputStream())), MovieInfo.class);
                    break;
            }

            connection.connect();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
