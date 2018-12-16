package kr.co.supp0rtyoo.boostcourse;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import kr.co.supp0rtyoo.boostcourse.databinding.MovieContentsBinding;
import kr.co.supp0rtyoo.boostcourse.movieInfo.MovieItem;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    Context context;
    ArrayList<MovieItem> movieItems = new ArrayList<>();

    OnItemClickListener listener;

    public MovieAdapter(Context context) {
        this.context = context;
    }

    public interface OnItemClickListener {
        void onItemClick(MovieViewHolder holder, View view, int position);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        MovieContentsBinding binding = MovieContentsBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);

        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int position) {
        MovieItem movieItem = movieItems.get(position);
        movieViewHolder.bind(movieItem);
        movieViewHolder.setImage(movieItem);
        movieViewHolder.setOnItemClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return movieItems.size();
    }

    public void setMovies(ArrayList<MovieItem> _movieInfos) {
        if(_movieInfos == null) { return; }
        this.movieItems = _movieInfos;
        notifyDataSetChanged();
    }

    public MovieItem getMovie(int position) {
        return movieItems.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    static public class MovieViewHolder extends RecyclerView.ViewHolder {
        MovieContentsBinding binding;
        OnItemClickListener listener;

        public MovieViewHolder(@NonNull MovieContentsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if(listener != null) {
                        listener.onItemClick(MovieViewHolder.this, view, position);
                    }
                }
            });
        }

        public void bind(MovieItem movieItem) {
            binding.setMovieItem(movieItem);
            binding.movieTitle.setText(Html.fromHtml(movieItem.getTitle()));
            setImage(movieItem);
        }

        public void setImage(MovieItem movieItem) {
            ImageLoadTask imageLoadTask = new ImageLoadTask(movieItem.getImage(), binding.movieImage);
            imageLoadTask.execute();
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }
    }
}
