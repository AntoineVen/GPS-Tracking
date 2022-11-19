package com.example.clswwearosgpsmemo2022;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.wear.widget.WearableRecyclerView;

import com.example.clswwearosgpsmemo2022.WikiLovesMonument.Monument;
import com.example.clswwearosgpsmemo2022.databinding.MonumentItemBinding;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

public class MonumentsDisplayAdapter extends WearableRecyclerView.Adapter<MonumentsDisplayAdapter.MonumentViewHolder> {
    private final List<Monument> monuments;

    public MonumentsDisplayAdapter(List<Monument> results) {
        this.monuments = results;
    }

    @NonNull
    @Override
    public MonumentsDisplayAdapter.MonumentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        MonumentItemBinding binding = MonumentItemBinding.inflate(layoutInflater, parent, false);

        return new MonumentViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MonumentsDisplayAdapter.MonumentViewHolder holder, int position) {
        Monument curItem = monuments.get(position);
        String name = curItem.getName();
        if (name != null) {
            name=name.replace("[", "");
            name=name.replace("]", "");
            name = name.split("\\u007C")[0];
            if(name.length()>80) name = name.substring(0, 80);
            holder.binding.monumentName.setText(name);
        }

        Double dist = curItem.getDist();
        if (dist != null) holder.binding.monumentDistance.setText(String.format("%.0f", dist) + "m");

        Picasso.get()
                .load(curItem.getImageURL())
                .transform(new BlurTransformation(holder.binding.monumentImage.getContext(),8) ) // bluring the image
                .fit()
                .centerCrop()
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.ic_menu_help)
                .into(holder.binding.monumentImage);

        //change activity by clicking on the image
        holder.binding.eyeToSee.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Toast.makeText(holder.binding.eyeToSee.getContext(), "Item Clicked", Toast.LENGTH_LONG).show();
                Intent myIntent = new Intent(holder.binding.eyeToSee.getContext(), Activity2_monument_picture.class);
                holder.binding.eyeToSee.getContext().startActivity(myIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return monuments.size();
    }

    public static class MonumentViewHolder extends RecyclerView.ViewHolder{

        MonumentItemBinding binding;

        public MonumentViewHolder(@NonNull MonumentItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }


    }
}
/*
    private static final String DMYHMS_DATE_FORMAT = "dd/MM/yyyy-HH':'mm':'ss";

    // Data model
    ArrayList<Location> locations;
    Context context;

    public MonumentsDisplayAdapter(ArrayList<Location> locations, Context context) {
        this.locations = locations;
        this.context = context;
    }

    @NonNull
    @Override
    public GpsLocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemGpsLocationBinding binding = ItemGpsLocationBinding.inflate(layoutInflater, parent, false);
        return new GpsLocationViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull GpsLocationViewHolder holder, int position) {
        Location curItem = locations.get(position);
        Date date = new Date(curItem.getTime());
        holder.binding.timeStampTv.setText(context.getString(R.string.timestamp_format, DateFormat.format(DMYHMS_DATE_FORMAT, date).toString()));
        holder.binding.latitudeTv.setText(context.getString(R.string.latitude_format, curItem.getLatitude()));
        holder.binding.longitudeTv.setText(context.getString(R.string.longitude_format, curItem.getLongitude()));
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public static class GpsLocationViewHolder extends WearableRecyclerView.ViewHolder {
        ItemGpsLocationBinding binding;
        public GpsLocationViewHolder(@NonNull ItemGpsLocationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}*/
