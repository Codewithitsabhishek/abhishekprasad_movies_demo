package com.example.codewithabhishek.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.codewithabhishek.ShowMoviesList;
import com.example.codewithabhishek.R;
import com.example.codewithabhishek.adapters.HorizontalMoviesList;
import com.example.codewithabhishek.api.PersonalViewModel;
import com.example.codewithabhishek.databinding.FragmentLatestMoviesBinding;

import java.util.ArrayList;

public class LatestMoviesFragment extends Fragment {

    private FragmentLatestMoviesBinding binding;  // Data Binding class
    private ShowMoviesList adapter;
    private HorizontalMoviesList horizontalAdapter;
    private PersonalViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLatestMoviesBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(PersonalViewModel.class);

        binding.recyclerViewHorizontal.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        binding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2)); // 2 columns

        adapter = new ShowMoviesList(new ArrayList<>());
        binding.recyclerView.setAdapter(adapter);

        horizontalAdapter = new HorizontalMoviesList(new ArrayList<>());
        binding.recyclerViewHorizontal.setAdapter(horizontalAdapter);

        observeViewModel();
        viewModel.fetchMovies("Marvel");
        binding.searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                viewModel.fetchMovies(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return binding.getRoot();
    }

    private void observeViewModel() {
        viewModel.getBusinessLiveData().observe(getViewLifecycleOwner(), businessList -> {
            if (businessList == null || businessList.isEmpty()) {
                Toast.makeText(getContext(), "No results found", Toast.LENGTH_SHORT).show();
                adapter.updateList(new ArrayList<>());
            } else {
                adapter.updateList(businessList);
            }
        });

        viewModel.getHorizontalBusinessLiveData().observe(getViewLifecycleOwner(), horizontalBusinessList -> {
            if (horizontalBusinessList != null && !horizontalBusinessList.isEmpty()) {
                horizontalAdapter.updateList(horizontalBusinessList);
            }
        });
    }
}
