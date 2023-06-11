package com.example.h071211050_finalmobile;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.h071211050_finalmobile.fragment.FavoriteFragment;
import com.example.h071211050_finalmobile.fragment.MovieFragment;
import com.example.h071211050_finalmobile.fragment.TvFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.elevation.SurfaceColors;

public class MainActivity extends AppCompatActivity {
    public static BottomNavigationView bottom_nav;
    public static ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();
        getWindow().setStatusBarColor(SurfaceColors.SURFACE_2.getColor(this));

        FragmentManager Fm = getSupportFragmentManager();
        displayMovieFragment(Fm);

        bottom_nav = findViewById(R.id.bottom_navbar);
        bottom_nav.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.movieMenu) {
                displayMovieFragment(Fm);
            }
            else if (item.getItemId() == R.id.tvShowMenu) {
                displayTvFragment(Fm);
            }
            else if (item.getItemId() == R.id.favoriteMenu) {
                displayFavoriteFragment(Fm);
            }
            return true;
        });

    }

    private void displayMovieFragment(FragmentManager fm) {
        MovieFragment movieFragment = new MovieFragment();
        Fragment fragment = fm.findFragmentByTag(MovieFragment.class.getSimpleName());
        if (!(fragment instanceof MovieFragment)) {
            fm.beginTransaction().replace(R.id.frame_container, movieFragment, MovieFragment.class.getSimpleName()).commit();
        }
    }

    private void displayTvFragment(FragmentManager fm) {
        TvFragment tvFragment = new TvFragment();
        Fragment fragment = fm.findFragmentByTag(TvFragment.class.getSimpleName());
        if (!(fragment instanceof TvFragment)) {
            fm.beginTransaction().replace(R.id.frame_container, tvFragment, TvFragment.class.getSimpleName()).commit();
        }
    }

    private void displayFavoriteFragment(FragmentManager fm) {
        FavoriteFragment favoriteFragment = new FavoriteFragment();
        Fragment fragment = fm.findFragmentByTag(FavoriteFragment.class.getSimpleName());
        if (!(fragment instanceof FavoriteFragment)) {
            fm.beginTransaction().replace(R.id.frame_container, favoriteFragment, FavoriteFragment.class.getSimpleName()).commit();
        }
    }
}