package com.example.h071211050_finalmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;

import android.content.ContentValues;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.h071211050_finalmobile.models.FavoriteModel;
import com.example.h071211050_finalmobile.networking.Database;
import com.example.h071211050_finalmobile.networking.FavoriteHelper;

public class DetailMovieTvActivity extends AppCompatActivity {
    public static final String EXTRA_ITEM = "item_extra";
    public static final String EXTRA_TYPE = "type_extra";
    public static final int TYPE_MOVIE = 1;
    public static final int TYPE_TV = 2;
    private boolean isFavorite = false;
    private ImageView backdrop_img, poster_img, type_icon, favorite_icon;
    private CardView back_button, favorite_button;
    private TextView title3, release_date, rating, synopsis;
    private FavoriteHelper favoriteHelper;
    private FavoriteModel favoriteModel = new FavoriteModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie_tv);

        backdrop_img = findViewById(R.id.backdrop_img);
        poster_img = findViewById(R.id.poster_img);
        back_button = findViewById(R.id.back_button);
        favorite_button = findViewById(R.id.favorite_button);
        title3 = findViewById(R.id.title3);
        release_date = findViewById(R.id.release_date);
        rating = findViewById(R.id.rating_tv);
        synopsis = findViewById(R.id.synopsis);
        type_icon = findViewById(R.id.type_icon );
        favorite_icon = findViewById(R.id.favorite_icon);

        favoriteHelper = FavoriteHelper.getInstance(getApplicationContext());
        favoriteHelper.open();

        back_button.setOnClickListener(v -> {
            setResult(RESULT_OK, getIntent());
            finish();
        });

        favoriteModel = getIntent().getParcelableExtra(EXTRA_ITEM);
        displayData(favoriteModel);

        isFavorite = favoriteHelper.getFavorite(String.valueOf(favoriteModel.getId())) > 0;

        if (isFavorite) favorite_icon.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.favorite, null));
        else favorite_icon.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.favorite_border, null));

        favorite_button.setOnClickListener(v -> {
            isFavorite = !isFavorite;
            if (isFavorite) {
                favorite_icon.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.favorite, null));
                insertDataToFavouriteTable(favoriteModel);
            } else {
                favorite_icon.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.favorite_border, null));
                deleteDataToFavouriteTable(favoriteModel.getId());
            }
        });
    }

    private void deleteDataToFavouriteTable(int id) {
        long result = favoriteHelper.deleteData(String.valueOf(id));

        String message = result > 0 ? "Berhasil menghapus " + favoriteModel.getTitle() : "Gagal menghapus data" + favoriteModel.getTitle();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void insertDataToFavouriteTable(FavoriteModel favouriteModel) {
        ContentValues cv  = new ContentValues();
        cv.put(Database.ItemColumns._ID, favouriteModel.getId());
        cv.put(Database.ItemColumns.TITLE, favouriteModel.getTitle());
        cv.put(Database.ItemColumns.DATE, favouriteModel.getDate());
        cv.put(Database.ItemColumns.VOTE_AVERAGE, favouriteModel.getVote_average());
        cv.put(Database.ItemColumns.OVERVIEW, favouriteModel.getOverview());
        cv.put(Database.ItemColumns.POSTER_PATH, favouriteModel.getPoster_path());
        cv.put(Database.ItemColumns.BACKDROP_PATH, favouriteModel.getBackdrop_path());
        cv.put(Database.ItemColumns.TYPE, favouriteModel.getType());
        long result = favoriteHelper.insertData(cv);

        String message = result > 0 ? "Berhasil menambahkan " + favoriteModel.getTitle() : "Gagal menambahkan " + favoriteModel.getTitle();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void displayData(FavoriteModel favoriteModel) {
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500" + favoriteModel.getBackdrop_path())
                .centerCrop()
                .into(backdrop_img);
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500" + favoriteModel.getPoster_path())
                .centerCrop()
                .into(poster_img);
        title3.setText(favoriteModel.getTitle());
        release_date.setText(favoriteModel.getDate());
        rating.setText(String.valueOf(favoriteModel.getVote_average()));
        synopsis.setText(favoriteModel.getOverview());

        if (favoriteModel.getTitle().equals("") || favoriteModel.getTitle() == null)
            title3.setText("-");
        else
            title3.setText(favoriteModel.getTitle());

        if (favoriteModel.getDate().equals("") || favoriteModel.getDate() == null)
            release_date.setText("-");
        else
            release_date.setText(favoriteModel.getDate());

        if (favoriteModel.getVote_average().equals("0") || favoriteModel.getVote_average() == null)
            rating.setText("-");
        else
            rating.setText(String.valueOf(favoriteModel.getVote_average()));

        if (favoriteModel.getOverview().equals("") || favoriteModel.getOverview() == null)
            synopsis.setText("-");
        else
            synopsis.setText(favoriteModel.getOverview());

        if (favoriteModel.getType() == TYPE_MOVIE) {
            type_icon.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.movie, null));
        } else {
            type_icon.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.tv, null));
        }
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK, getIntent());
        finish();
        super.onBackPressed();
    }
}