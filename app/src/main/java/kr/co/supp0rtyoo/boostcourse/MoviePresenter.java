package kr.co.supp0rtyoo.boostcourse;


import kr.co.supp0rtyoo.boostcourse.movieInfo.MovieItem;

public class MoviePresenter implements Movie.Presenter {
    Movie.View movieView;
    MovieModel movieModel;

    public MoviePresenter(Movie.View movieView) {
        this.movieView = movieView;
        this.movieModel = new MovieModel();
    }

    @Override
    public void setRecyclerView(String text) {
        if(text.length() == 0) {
            movieView.showToastMessage("검색어를 입력해주세요.");
        }
        else {
            if (movieModel.getMovieInfo(text).getItems().size() == 0) {
                String message = "'" + text + "' 검색결과는 없습니다..";
                movieView.showToastMessage(message);
            } else {
                movieView.startBinding(movieModel.getMovieInfo(text));
                movieView.setRecyclerListener();
            }
        }
    }

    @Override
    public void setWebActivity(MovieItem movieItem) {
        String url = movieItem.getLink();
        movieView.startWebActivity(url);
    }
}
