package kr.co.supp0rtyoo.boostcourse.movieInfo;

import java.util.Date;

public class MovieItem {
    private String title;
    private String link;
    private String image;
    private String subtitle;
    private String pubDate;
    private String director;
    private String actor;
    private float userRating;

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getImage() {
        return image;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getDate() {
        return pubDate;
    }

    public String getDirector() {
        return director;
    }

    public String getActor() {
        return actor;
    }

    public float getUserRating() {
        return userRating;
    }
}
