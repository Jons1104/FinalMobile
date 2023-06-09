package com.example.h071211050_finalmobile.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class MovieResponse implements Parcelable {
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("release_date")
    private String release_date;
    @SerializedName("overview")
    private String overview;
    @SerializedName("poster")
    private String poster;
    @SerializedName("backdrop")
    private String backdrop;
    @SerializedName("vote_average")
    private String vote_average;

    protected MovieResponse(Parcel in) {
        id = in.readInt();
        title = in.readString();
        release_date = in.readString();
        overview = in.readString();
        poster = in.readString();
        backdrop = in.readString();
        vote_average = in.readString();
    }

    public static final Creator<MovieResponse> CREATOR = new Creator<MovieResponse>() {
        @Override
        public MovieResponse createFromParcel(Parcel in) {
            return new MovieResponse(in);
        }

        @Override
        public MovieResponse[] newArray(int size) {
            return new MovieResponse[size];
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
    public String getPoster() {
        return poster;
    }
    public String getBackdrop() {
        return backdrop;
    }
    public String getVoteAverage() {
        return vote_average;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(release_date);
        dest.writeString(overview);
        dest.writeString(poster);
        dest.writeString(backdrop);
        dest.writeString(vote_average);
    }
}
