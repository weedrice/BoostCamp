package kr.co.supp0rtyoo.boostcourse;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import kr.co.supp0rtyoo.boostcourse.databinding.ActivityMainBinding;
import kr.co.supp0rtyoo.boostcourse.movieInfo.MovieInfo;
import kr.co.supp0rtyoo.boostcourse.movieInfo.MovieItem;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    private MovieInfo movieInfo;
    private NetworkTask networkTask;

    LinearLayoutManager recyclerViewManager;
    MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        movieAdapter = new MovieAdapter(getApplicationContext());
        movieInfo = new MovieInfo();
        recyclerViewManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        binding.movieRecyclerView.setLayoutManager(recyclerViewManager);
        binding.movieRecyclerView.setAdapter(movieAdapter);
        binding.setMovieInfo(movieInfo);
        setOnCLickListener();

    }

    @BindingAdapter("bind:item")
    public static void bindItem(RecyclerView recyclerView, MovieInfo movieInfo) {
        MovieAdapter adapter = (MovieAdapter)recyclerView.getAdapter();
        if(adapter != null) {
            adapter.setMovies(movieInfo.getItems());
        }
    }

    private void setOnCLickListener() {
        binding.searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = binding.searchText.getText().toString();
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
            Toast toast = Toast.makeText(this, "'"+binding.searchText.getText().toString()+"' 검색결과는 없습니다.",Toast.LENGTH_LONG);
            View viewToast = toast.getView();

            viewToast.setBackgroundResource(R.color.colorPrimary);

            TextView textToast = (TextView)viewToast.findViewById(android.R.id.message);
            textToast.setTextColor(Color.WHITE);

            toast.show();
        } else {
            movieAdapter.setMovies(movieInfo.getItems());
            setRecyclerListener();

            binding.movieRecyclerView.setAdapter(movieAdapter);
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
}
