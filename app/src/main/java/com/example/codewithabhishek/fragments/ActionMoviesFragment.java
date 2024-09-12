package com.example.codewithabhishek.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codewithabhishek.ShowMoviesList;
import com.example.codewithabhishek.R;

public class ActionMoviesFragment extends Fragment {
    private RecyclerView recyclerView;
    private ShowMoviesList adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_action_movies, container, false);
        return view;


    }
}

