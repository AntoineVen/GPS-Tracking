package com.example.clswwearosgpsmemo2022;

import android.content.Context;
import android.location.Location;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.wear.widget.WearableRecyclerView;

import com.example.clswwearosgpsmemo2022.databinding.ItemGpsLocationBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class GpsLocationAdapter extends WearableRecyclerView.Adapter<GpsLocationAdapter.GpsLocationViewHolder> {
    private static final String DMYHMS_DATE_FORMAT = "dd/MM/yyyy-HH':'mm':'ss";

    // Data model
    ArrayList<Location> locations;
    Context context;

    public GpsLocationAdapter(ArrayList<Location> locations, Context context) {
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
        Picasso.with(this.context)
                .load("https://upload.wikimedia.org/wikipedia/commons/thumb/2/25/Tour_eiffel_-_vue_du_trocad%C3%A9ro.jpg/1200px-Tour_eiffel_-_vue_du_trocad%C3%A9ro.jpg")//ApiClient.IMAGE_BASE_URL + curItem.getProfilePath())
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(holder.binding.monument);
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
}
