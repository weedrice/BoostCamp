package kr.co.supp0rtyoo.boostcourse.movieInfo;

import java.util.ArrayList;

public class MovieInfo {
    private String rss;
    private String channel;
    private String lastBuildDate;
    private ArrayList<MovieItem> items;

    public String getRss() {
        return rss;
    }

    public String getChannel() {
        return channel;
    }

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public ArrayList<MovieItem> getItems() {
        return items;
    }
}
