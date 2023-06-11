package com.example.h071211050_finalmobile.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import android.os.Looper;

import com.example.h071211050_finalmobile.models.FavoriteModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class DataAsynchronous {
    private final WeakReference<Context> contextWeakReference;
    private final WeakReference<DataCallback> weakCallback;

    public DataAsynchronous(Context contextWeakReference, DataCallback weakCallback) {
        this.contextWeakReference = new WeakReference<>(contextWeakReference);
        this.weakCallback = new WeakReference<>(weakCallback);
    }

    public void execute() {
        Executor executors = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executors.execute(() -> {
            Context context = contextWeakReference.get();
            FavoriteHelper favoriteHelper = FavoriteHelper.getInstance(context);
            favoriteHelper.open();
            Cursor cursor = favoriteHelper.queryShowAll();
            ArrayList<FavoriteModel> favoriteModels = MappingHelper.cursorToArraylist(cursor);
            favoriteHelper.close();
            handler.post(() -> weakCallback.get().postExecute(favoriteModels));
        });
    }
}

