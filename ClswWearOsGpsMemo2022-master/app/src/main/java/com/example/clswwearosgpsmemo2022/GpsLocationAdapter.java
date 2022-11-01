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
    MonumentImages monumentImage;
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
        MonumentImages currentMonumentImage = monumentImage;
        Date date = new Date(curItem.getTime());
        holder.binding.timeStampTv.setText(context.getString(R.string.timestamp_format, DateFormat.format(DMYHMS_DATE_FORMAT, date).toString()));
        holder.binding.latitudeTv.setText(context.getString(R.string.latitude_format, curItem.getLatitude()));
        holder.binding.longitudeTv.setText(context.getString(R.string.longitude_format, curItem.getLongitude()));
        Picasso.with(this.context)
                .load(ApiImageClient.MONUMENT_IMAGE_API_BASE_URL + currentMonumentImage.getUrl())
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
