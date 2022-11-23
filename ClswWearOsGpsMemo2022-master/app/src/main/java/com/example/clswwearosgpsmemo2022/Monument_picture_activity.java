package com.example.clswwearosgpsmemo2022;

import static android.content.ContentValues.TAG;

import static androidx.wear.activity.ConfirmationActivity.EXTRA_MESSAGE;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.clswwearosgpsmemo2022.databinding.MonumentPictureActivity2Binding;
import com.squareup.picasso.Picasso;

public class Monument_picture_activity extends Activity {

    private @NonNull MonumentPictureActivity2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: get in activity 2");
        setContentView(R.layout.monument_picture_activity2);
        // get intent from HomeActivity
        binding = MonumentPictureActivity2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();

        // get extra message
        String url = intent.getStringExtra(EXTRA_MESSAGE);
        Bundle extras = getIntent().getExtras();


        Picasso.get()
                .load(url)
                .fit()
                .centerCrop()
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.ic_menu_help)
                .into(binding.picture);
    }
}