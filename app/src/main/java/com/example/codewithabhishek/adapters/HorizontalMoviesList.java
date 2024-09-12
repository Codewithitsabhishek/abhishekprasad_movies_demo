package com.example.codewithabhishek.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.codewithabhishek.R;
import com.example.codewithabhishek.databinding.MoreItemBuisnessBinding;
import com.example.codewithabhishek.model.Movie;

import java.util.List;

public class HorizontalMoviesList extends RecyclerView.Adapter<HorizontalMoviesList.HorizontalBusinessViewHolder> {

    private List<Movie> movieList;

    public HorizontalMoviesList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public HorizontalBusinessViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MoreItemBuisnessBinding binding = MoreItemBuisnessBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new HorizontalBusinessViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalBusinessViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class HorizontalBusinessViewHolder extends RecyclerView.ViewHolder {

        private MoreItemBuisnessBinding binding;

        public HorizontalBusinessViewHolder(MoreItemBuisnessBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Movie movie) {
            binding.title.setText(movie.getTitle());
            binding.views.setText(movie.getYear());
            Glide.with(binding.getRoot().getContext())
                    .load(movie.getPosterUrl())
                    .apply(RequestOptions.placeholderOf(R.drawable.persontea))
                    .into(binding.verticalImages);
        }
    }

    public void updateList(List<Movie> updatedList) {
        movieList.clear();
        movieList.addAll(updatedList);
        notifyDataSetChanged();
    }
}
