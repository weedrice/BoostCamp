package kr.co.supp0rtyoo.boostcourse;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import kr.co.supp0rtyoo.boostcourse.databinding.ActivityMainBinding;
import kr.co.supp0rtyoo.boostcourse.movieInfo.MovieInfo;

public class MainActivity extends AppCompatActivity implements Movie.View{
    private ActivityMainBinding binding;
    MovieInfo movieInfo = new MovieInfo();

    Movie.Presenter presenter;
    MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
        binding.movieRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.movieRecyclerView.setAdapter(movieAdapter);
        setOnCLickListener();

    }

    private void init() {
        presenter = new MoviePresenter(this);
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        movieAdapter = new MovieAdapter(getApplicationContext());
        binding.setMovieInfo(movieInfo);
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
                presenter.setRecyclerView(binding.searchText.getText().toString());
            }
        });

        binding.searchText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(keyCode == KeyEvent.KEYCODE_ENTER) {
                    presenter.setRecyclerView(binding.searchText.getText().toString());
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void startWebActivity(String url) {
        Intent intent = new Intent(getApplicationContext(), WebActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }

    @Override
    public void showToastMessage(String message) {
        //Toast toast = Toast.makeText(this, "'"+binding.searchText.getText().toString()+"' 검색결과는 없습니다.",Toast.LENGTH_LONG);
        View toastView = View.inflate(MainActivity.this, R.layout.toast_layout, null);
        Toast toast = new Toast(MainActivity.this);
        toast.setView(toastView);
        toast.setDuration(Toast.LENGTH_LONG);

        toast.show();
    }

    @Override
    public void startBinding(MovieInfo movieInfo) {
        bindItem(binding.movieRecyclerView, movieInfo);
    }


    @Override
    public void setRecyclerListener() {
        movieAdapter.setOnItemClickListener(new MovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MovieAdapter.MovieViewHolder holder, View view, int position) {
                presenter.setWebActivity(movieAdapter.getMovie(position));
            }
        });
    }
}
