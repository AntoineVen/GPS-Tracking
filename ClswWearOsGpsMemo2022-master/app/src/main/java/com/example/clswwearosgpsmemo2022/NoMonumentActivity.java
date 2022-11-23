package com.example.clswwearosgpsmemo2022;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.clswwearosgpsmemo2022.databinding.NoMonumentLayoutBinding;

public class NoMonumentActivity extends Activity {
    private @NonNull
    NoMonumentLayoutBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: get in activity no monument");
        setContentView(R.layout.no_monument_layout);
        // get intent from HomeActivity
        NoMonumentLayoutBinding binding = NoMonumentLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
