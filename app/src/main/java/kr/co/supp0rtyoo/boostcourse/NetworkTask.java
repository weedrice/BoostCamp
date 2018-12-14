package kr.co.supp0rtyoo.boostcourse;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import kr.co.supp0rtyoo.boostcourse.movieInfo.MovieInfo;

public class NetworkTask extends AsyncTask<String, Integer, MovieInfo> {
    private final String clientID = "4hK9Ahk3YKTZed2kOjX0";
    private final String clientSecret = "900mS013QH";
    private final String apiUrl = "https://openapi.naver.com/v1/search/movie.json?query=";
    private final String displayUrl = "&display=100";

    private final String TAG = "NetworkTask: ";

    @Override
    protected MovieInfo doInBackground(String... text) {
        URL url;
        MovieInfo movieInfo = new MovieInfo();
        try {
            String encodedText = URLEncoder.encode(text[0], "UTF-8");
            url = new URL(apiUrl + encodedText + displayUrl);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-Naver-Client-Id", clientID);
            connection.setRequestProperty("X-Naver-Client-Secret", clientSecret);

            int responseCode = connection.getResponseCode();
            BufferedReader bufferedReader;

            switch(responseCode) {
                case 400:
                    Log.d(TAG, "400");
                    bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                    Log.d(TAG, bufferedReader.toString());
                    break;
                case 404:
                    Log.d(TAG, "404");
                    bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                    Log.d(TAG, bufferedReader.toString());
                    break;
                case 500:
                    Log.d(TAG, "500");
                    bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                    Log.d(TAG, bufferedReader.toString());
                    break;
                case 200:
                    Log.d(TAG, "200");
                    Gson gson = new Gson();
                    movieInfo = gson.fromJson(new BufferedReader(new InputStreamReader(connection.getInputStream())), MovieInfo.class);
                    Log.d(TAG, movieInfo.getLastBuildDate());
                    break;
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return movieInfo;
    }

    @Override
    protected void onPostExecute(MovieInfo movieInfo) {
        super.onPostExecute(movieInfo);

        Log.d(TAG, "검색 시간: " + movieInfo.getLastBuildDate());
    }
}
