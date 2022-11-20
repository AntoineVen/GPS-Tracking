package com.example.clswwearosgpsmemo2022;

import static android.content.ContentValues.TAG;

import static androidx.wear.activity.ConfirmationActivity.EXTRA_MESSAGE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.wear.activity.ConfirmationActivity;
import androidx.wear.widget.WearableLinearLayoutManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.clswwearosgpsmemo2022.WikiLovesMonument.ApiClientMonuments;
import com.example.clswwearosgpsmemo2022.WikiLovesMonument.Monument;
import com.example.clswwearosgpsmemo2022.WikiLovesMonument.WikiLovesMonumentsAPI;
import com.example.clswwearosgpsmemo2022.databinding.ActivityMainActivity2MonumentPictureBinding;
import com.example.clswwearosgpsmemo2022.databinding.ActivityMainBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Activity2_monument_picture extends Activity {
    public WikiLovesMonumentsAPI tmdbApi = null;
    List<Monument> monumentsList = new ArrayList<>();

    private final Context mContext = this;
    private ImageView imageView;
    private ActivityMainActivity2MonumentPictureBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: get in activity 2");
        setContentView(R.layout.activity_main_activity2_monument_picture);

        // get intent from HomeActivity

        Intent intent = getIntent();

        // get extra message
        String url = intent.getStringExtra(ConfirmationActivity.EXTRA_MESSAGE);

        //render in textview
        ImageView imageView = (ImageView) findViewById(R.id.picture);
        imageView.setImageURI(Uri.parse(url));

        Bundle extras = getIntent().getExtras();

        //byte[] byteArray = extras.getByteArray("picture");


        /*Picasso.get()
                .load(url)
                .fit()
                .centerCrop()
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.ic_menu_help)
                .into(binding.monuPicture);*/
    }
}