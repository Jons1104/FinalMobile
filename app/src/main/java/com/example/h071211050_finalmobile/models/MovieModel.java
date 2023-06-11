package com.example.h071211050_finalmobile.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class MovieModel implements Parcelable {
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("release_date")
    private String release_date;
    @SerializedName("overview")
    private String overview;
    @SerializedName("poster_path")
    private String poster_path;
    @SerializedName("backdrop_path")
    private String backdrop_path;
    @SerializedName("vote_average")
    private String vote_average;

    protected MovieModel(Parcel in) {
        id = in.readInt();
        title = in.readString();
        release_date = in.readString();
        overview = in.readString();
        poster_path = in.readString();
        backdrop_path = in.readString();
        vote_average = in.readString();
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getReleaseDate() {
        return release_date;
    }
    public String getOverview() {
        return overview;
    }
    public String getPosterPath() {
        return poster_path;
    }
    public String getBackdropPath() {
        return backdrop_path;
    }
    public String getVoteAverage() {
        return vote_average;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel del, int flags) {
        del.writeInt(id);
        del.writeString(title);
        del.writeString(release_date);
        del.writeString(overview);
        del.writeString(poster_path);
        del.writeString(backdrop_path);
        del.writeString(vote_average);
    }
}
