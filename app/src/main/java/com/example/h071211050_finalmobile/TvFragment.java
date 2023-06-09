package com.example.h071211050_finalmobile;

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

import com.example.h071211050_finalmobile.R;
import com.example.h071211050_finalmobile.adapter.TvAdapter;
import com.example.h071211050_finalmobile.Api.ApiConfig;
import com.example.h071211050_finalmobile.Api.TvData;
import com.example.h071211050_finalmobile.TvResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvFragment extends Fragment {

    private RecyclerView listTv;
    private ProgressBar progressbar;
    private TextView error;
    public static List<TvResponse> datatv = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressbar = view.findViewById(R.id.progressbar);
        error = view.findViewById(R.id.error);
        listTv = view.findViewById(R.id.listtv);

        listTv.setHasFixedSize(true);
        listTv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        setDataTv();

    }

    private void setDataTv() {
        Call<TvData> tvClient = ApiConfig.getApiService().getTvSeries();
        tvClient.enqueue(new Callback<TvData>() {
            @Override
            public void onResponse(Call<TvData> call, Response<TvData> response) {
                if(response.isSuccessful()){
                    if (response.body().getData() != null) {
                        progressbar.setVisibility(View.GONE);
                        datatv = response.body().getData();
                        Log.d("MainActivity", "tv list");
                        for (TvResponse tvDataResponse : datatv) {
                            Log.d("MainActivity", "" + tvDataResponse.getName());
                        }
                        TvAdapter tvAdapter = new TvAdapter(datatv);
                        listTv.setAdapter(tvAdapter);
                    } else {
                        Log.e("MainActivity", "data body is null");
                        progressbar.setVisibility(View.GONE);
                        error.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<TvData> call, Throwable t) {
                Log.d("MainActivity", "" + t.getMessage());
            }
        });
    }
}