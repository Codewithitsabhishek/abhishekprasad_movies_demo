package com.example.codewithabhishek;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.codewithabhishek.databinding.ItemBusinessBinding;
import com.example.codewithabhishek.model.Movie;

import java.util.List;

public class ShowMoviesList extends RecyclerView.Adapter<ShowMoviesList.BusinessViewHolder> {

    private List<Movie> movieList;

    public ShowMoviesList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public BusinessViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBusinessBinding binding = ItemBusinessBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BusinessViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BusinessViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class BusinessViewHolder extends RecyclerView.ViewHolder {

        private ItemBusinessBinding binding;

        public BusinessViewHolder(ItemBusinessBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Movie movie) {
            // Bind movie data to views
            binding.tvTitle.setText(movie.getTitle());
            binding.tvViews.setText(movie.getYear());
            Glide.with(binding.getRoot().getContext())
                    .load(movie.getPosterUrl())
                    .apply(RequestOptions.placeholderOf(R.drawable.persontea))
                    .into(binding.ivVerticalImages);
        }
    }

    public void updateList(List<Movie> updatedList) {
        movieList.clear();
        movieList.addAll(updatedList);
        notifyDataSetChanged();
    }
}
