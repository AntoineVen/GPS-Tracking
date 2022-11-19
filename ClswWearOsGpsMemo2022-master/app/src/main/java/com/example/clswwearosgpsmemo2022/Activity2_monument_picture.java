package com.example.clswwearosgpsmemo2022;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class Activity2_monument_picture extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: get in activity 2");
        setContentView(R.layout.activity_main_activity2_monument_picture);
    }
}