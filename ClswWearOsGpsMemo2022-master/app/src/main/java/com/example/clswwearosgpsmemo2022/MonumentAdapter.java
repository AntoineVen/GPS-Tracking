package com.example.clswwearosgpsmemo2022;

import android.content.Context;
import android.location.Location;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.wear.widget.WearableRecyclerView;

import com.example.clswwearosgpsmemo2022.databinding.ItemMonumentBinding;

import java.util.ArrayList;
import java.util.Date;

public class MonumentAdapter extends WearableRecyclerView.Adapter<MonumentAdapter.MonumentViewHolder> {
    private static final String DMYHMS_DATE_FORMAT = "dd/MM/yyyy-HH':'mm':'ss";

    // Data model
    String image;
    ArrayList<Location> locations;
    Context context;

    public MonumentAdapter(ArrayList<Location> locations, Context context) {
        this.locations = locations;
        this.context = context;
        this.image = image;
    }

    @NonNull
    @Override
    public MonumentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemMonumentBinding binding = ItemMonumentBinding.inflate(layoutInflater, parent, false);
        return new MonumentViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull MonumentViewHolder holder, int position) {
        Location curItem = locations.get(position);
        Date date = new Date(curItem.getTime());
        holder.binding.timeStampTv.setText(context.getString(R.string.timestamp_format, DateFormat.format(DMYHMS_DATE_FORMAT, date).toString()));
        holder.binding.latitudeTv.setText(context.getString(R.string.latitude_format, curItem.getLatitude()));
        holder.binding.longitudeTv.setText(context.getString(R.string.longitude_format, curItem.getLongitude()));
        //holder.binding.imageView.setImageURI(image);
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public static class MonumentViewHolder extends WearableRecyclerView.ViewHolder {
        ItemMonumentBinding binding;
        public MonumentViewHolder(@NonNull ItemMonumentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
