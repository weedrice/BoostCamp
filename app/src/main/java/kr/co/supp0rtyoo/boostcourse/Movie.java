package kr.co.supp0rtyoo.boostcourse;

import kr.co.supp0rtyoo.boostcourse.movieInfo.MovieInfo;
import kr.co.supp0rtyoo.boostcourse.movieInfo.MovieItem;

public interface Movie {

    interface View {
        void startWebActivity(String url);
        void showToastMessage(String message);
        void startBinding(MovieInfo movieInfo);
        void setRecyclerListener();
    }

    interface Presenter {
        void setRecyclerView(String text);
        void setWebActivity(MovieItem movieItem);
    }
}
