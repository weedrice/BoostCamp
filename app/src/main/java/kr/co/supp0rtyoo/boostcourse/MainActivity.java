package kr.co.supp0rtyoo.boostcourse;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import kr.co.supp0rtyoo.boostcourse.movieInfo.MovieInfo;
import kr.co.supp0rtyoo.boostcourse.movieInfo.MovieItem;

public class MainActivity extends AppCompatActivity {
    private Button searchBtn;
    private EditText searchText;
    private RecyclerView recyclerView;

    private MovieInfo movieInfo;
    private NetworkTask networkTask;

    LinearLayoutManager recyclerViewManager;
    MovieAdapter movieAdapter;

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
                networkTask = new NetworkTask();
                try {
                    movieInfo = networkTask.execute("*" + text + "*").get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                setRecyclerView();
            }
        });
    }

    private void showUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse(url);
        intent.setData(uri);
        startActivity(intent);
    }

    private void setRecyclerView() {
        recyclerViewManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(recyclerViewManager);
        movieAdapter = new MovieAdapter(getApplicationContext());

        Log.d("setRecyclerView: ", String.valueOf(movieInfo.getItems().size()));
        movieAdapter.addMovies(movieInfo.getItems());

        recyclerView.setAdapter(movieAdapter);
        setRecyclerListener();
    }

    private void setRecyclerListener() {
        movieAdapter.setOnItemClickListener(new MovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MovieAdapter.MovieViewHolder holder, View view, int position) {
                MovieItem movieItem = movieAdapter.getMovie(position);
                String url = movieItem.getLink();
                showUrl(url);
            }
        });
    }

    private void init() {
        searchBtn = (Button)findViewById(R.id.searchBtn);
        searchText = (EditText)findViewById(R.id.searchText);
        recyclerView = (RecyclerView)findViewById(R.id.movieRecyclerView);
    }
}
