package kr.co.supp0rtyoo.boostcourse;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import kr.co.supp0rtyoo.boostcourse.movieInfo.MovieInfo;
import kr.co.supp0rtyoo.boostcourse.movieInfo.MovieItem;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    Context context;
    ArrayList<MovieItem> movieItems = new ArrayList<>();

    OnItemClickListener listener;

    public MovieAdapter(Context context) {
        this.context = context;
    }

    public interface OnItemClickListener {
        public void onItemClick(MovieViewHolder holder, View view, int position);
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View movieView = layoutInflater.inflate(R.layout.movie_contents, viewGroup, false);

        return new MovieViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder movieViewHolder, int i) {
        MovieItem movieItem = movieItems.get(i);
        movieViewHolder.setMovie(movieItem);

        movieViewHolder.setOnItemClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return movieItems.size();
    }

    public void addMovie(MovieItem movieInfo) {
        movieItems.add(movieInfo);
    }

    public void addMovies(ArrayList<MovieItem> _movieInfos) {
        this.movieItems = _movieInfos;
    }

    public MovieItem getMovie(int position) {
        return movieItems.get(position);
    }

    public ArrayList<MovieItem> getMovies() {
        return this.movieItems;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    static public class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView movieImage;
        TextView movieTitle;
        RatingBar movieRating;
        TextView movieYear;
        TextView movieDirector;
        TextView movieActor;

        OnItemClickListener listener;

        public MovieViewHolder(@NonNull View movieView) {
            super(movieView);
            setViews(movieView);

            movieView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if(listener != null) {
                        listener.onItemClick(MovieViewHolder.this, view, position);
                    }
                }
            });
        }

        private void setViews(View itemView) {
            this.movieImage = itemView.findViewById(R.id.movieImage);
            this.movieTitle = itemView.findViewById(R.id.movieTitle);
            this.movieRating = itemView.findViewById(R.id.movieRatingBar);
            this.movieYear = itemView.findViewById(R.id.movieYear);
            this.movieDirector = itemView.findViewById(R.id.movieDirector);
            this.movieActor = itemView.findViewById(R.id.movieActors);
        }

        public void setMovie(MovieItem movieItem) {
            movieTitle.setText(Html.fromHtml(movieItem.getTitle()));
            movieRating.setRating(movieItem.getUserRating());
            //movieYear.setText(movieItem.getDate());
            Log.d("movieAdatper: ", String.valueOf(movieItem.getDate()));
            movieDirector.setText(movieItem.getDirector());
            movieActor.setText(movieItem.getActor());

            ImageLoadTask imageLoadTask = new ImageLoadTask(movieItem.getImage(), movieImage);
            imageLoadTask.execute();
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }
    }
}
