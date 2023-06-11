package com.example.h071211050_finalmobile.fragment;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.h071211050_finalmobile.MainActivity;
import com.example.h071211050_finalmobile.R;
import com.example.h071211050_finalmobile.data.TvData;
import com.example.h071211050_finalmobile.adapter.TvAdapter;
import com.example.h071211050_finalmobile.api.ApiConfig;
import com.example.h071211050_finalmobile.models.TvModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvFragment extends Fragment {

    private RecyclerView ListTv;
    private ProgressBar TvProgressbar;
    private TextView Error;
    private TvAdapter TVadapter;
    public static List<TvModel> dataTv = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListTv = view.findViewById(R.id.listTv);
        TvProgressbar = view.findViewById(R.id.tv_progressbar);
        Error = view.findViewById(R.id.error);

        if (MainActivity.actionBar != null) {
            Log.d(TAG, "join require Action bar");
            MainActivity.actionBar.setTitle("Tv Show");
        }

        ListTv.setHasFixedSize(true);
        ListTv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        setDataTv();

    }

    private void setDataTv() {
        Call<TvData> tvClient = ApiConfig.getApiService().getTv();
        tvClient.enqueue(new Callback<TvData>() {
            @Override
            public void onResponse(Call<TvData> call, Response<TvData> response) {
                if(response.isSuccessful()){
                    if (response.body().getData() != null) {
                        TvProgressbar.setVisibility(View.GONE);
                        dataTv = response.body().getData();
                        Log.d("MainActivity", "tv list");
                        for (TvModel tvDataResponse : dataTv) {
                            Log.d("MainActivity", ": " + tvDataResponse.getName());
                        }
                        TVadapter = new TvAdapter(dataTv);
                        ListTv.setAdapter(TVadapter);
                    } else {
                        Log.e("MainActivity", "data body is null");
                        TvProgressbar.setVisibility(View.GONE);
                        Error.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<TvData> call, Throwable t) {
                Log.d("MainActivity", "onFailure: " + t.getMessage());
                Error.setVisibility(View.VISIBLE);
            }
        });
    }
}