package com.example.h071211050_finalmobile.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class TvResponse implements Parcelable {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("date")
    private String date;
    @SerializedName("overview")
    private String overview;
    @SerializedName("poster")
    private String poster;
    @SerializedName("backdrop")
    private String backdrop;
    @SerializedName("vote_average")
    private String vote_average;

    protected TvResponse(Parcel in) {
        id = in.readInt();
        name = in.readString();
        date = in.readString();
        overview = in.readString();
        poster = in.readString();
        backdrop = in.readString();
        vote_average = in.readString();
    }

    public static final Creator<TvResponse> CREATOR = new Creator<TvResponse>() {
        @Override
        public TvResponse createFromParcel(Parcel in) {
            return new TvResponse(in);
        }

        @Override
        public TvResponse[] newArray(int size) {
            return new TvResponse[size];
        }
    };

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDate() {
        return date;
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
        dest.writeString(name);
        dest.writeString(date);
        dest.writeString(overview);
        dest.writeString(poster);
        dest.writeString(backdrop);
        dest.writeString(vote_average);
    }
}
