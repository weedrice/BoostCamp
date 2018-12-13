package kr.co.supp0rtyoo.boostcourse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;

public class MainActivity extends AppCompatActivity {
    RatingBar movieRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieRatingBar.setStepSize(0.25f);
        RatingBar.OnRatingBarChangeListener listener = movieRatingBar.getOnRatingBarChangeListener();

    }

    private void findID() {
        movieRatingBar = (RatingBar)findViewById(R.id.movieRatingBar);
    }
}
