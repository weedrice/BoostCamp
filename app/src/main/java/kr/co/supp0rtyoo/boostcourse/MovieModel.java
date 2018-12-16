package kr.co.supp0rtyoo.boostcourse;

import kr.co.supp0rtyoo.boostcourse.movieInfo.MovieInfo;

public class MovieModel {
    MovieInfo movieInfo;
    private NetworkTask networkTask;

    public MovieModel() {
        this.movieInfo = new MovieInfo();
    }

    public void setMovieInfo(MovieInfo movieInfo) {
        this.movieInfo = movieInfo;
    }

    public MovieInfo getMovieInfo(String text) {
        MovieInfo movieInfo = new MovieInfo();
        networkTask = new NetworkTask();
        try {
            movieInfo = networkTask.execute("*" + text + "*").get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movieInfo;
    }

}
