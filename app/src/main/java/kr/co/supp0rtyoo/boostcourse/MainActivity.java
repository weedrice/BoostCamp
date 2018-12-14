package kr.co.supp0rtyoo.boostcourse;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        Intent intent = new Intent(getApplicationContext(), WebActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }

    private void setRecyclerView() {
        if(movieInfo.getItems().size() == 0) {
            Toast toast = Toast.makeText(this, "'"+searchText.getText().toString()+"' 검색결과는 없습니다.",Toast.LENGTH_LONG);
            View viewToast = toast.getView();

            viewToast.setBackgroundResource(R.color.colorPrimary);

            TextView textToast = (TextView)viewToast.findViewById(android.R.id.message);
            textToast.setTextColor(Color.WHITE);

            toast.show();
        } else {
            movieAdapter.addMovies(movieInfo.getItems());
            setRecyclerListener();

            recyclerView.setAdapter(movieAdapter);
        }

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
        recyclerViewManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(recyclerViewManager);
        movieAdapter = new MovieAdapter(getApplicationContext());
        recyclerView.setAdapter(movieAdapter);
    }
}
