package kr.co.supp0rtyoo.boostcourse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class MainActivity extends AppCompatActivity {
    private RatingBar movieRatingBar;
    private Button searchBtn;
    private EditText searchText;
    private RecyclerView recyclerView;

    private NetworkThread networkThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setOnCLickListener();
    }

    private void setOnCLickListener() {
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = searchText.getText().toString();
                networkThread = new NetworkThread();
                networkThread.run(text);
            }
        });
    }

    private void init() {
        movieRatingBar = (RatingBar)findViewById(R.id.movieRatingBar);
        searchBtn = (Button)findViewById(R.id.searchBtn);
        searchText = (EditText)findViewById(R.id.searchText);
        recyclerView = (RecyclerView)findViewById(R.id.movieRecyclerView);
    }
}
