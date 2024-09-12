package com.example.codewithabhishek;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.codewithabhishek.fragments.OldMoviesFragment;
import com.example.codewithabhishek.fragments.ExploreMoviesFragment;
import com.example.codewithabhishek.fragments.WriteReviewFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private ExploreMoviesFragment exploreMoviesFragment;
    private OldMoviesFragment oldMoviesFragment;
    private WriteReviewFragment writeReviewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        exploreMoviesFragment = new ExploreMoviesFragment();
        oldMoviesFragment = new OldMoviesFragment();
        writeReviewFragment = new WriteReviewFragment();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, exploreMoviesFragment).commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;

        if (item.getItemId() == R.id.tv_explore) {
            selectedFragment = exploreMoviesFragment;
        } else if (item.getItemId() == R.id.tv_chat) {
            selectedFragment = oldMoviesFragment;
        } else if (item.getItemId() == R.id.tv_groups) {
            selectedFragment = writeReviewFragment;
        }
        if (selectedFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, selectedFragment).commit();
            return true;
        }

        return false;
    }
}
