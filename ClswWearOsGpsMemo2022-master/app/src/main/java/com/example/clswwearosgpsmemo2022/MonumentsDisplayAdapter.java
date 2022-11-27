package com.example.clswwearosgpsmemo2022;
import static android.content.Intent.EXTRA_TEXT;

import static androidx.wear.activity.ConfirmationActivity.EXTRA_MESSAGE;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.net.Uri;
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
import com.example.clswwearosgpsmemo2022.firebase.MonumentDAO;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.HashMap;
import java.util.List;

public class MonumentsDisplayAdapter extends WearableRecyclerView.Adapter<MonumentsDisplayAdapter.MonumentViewHolder> {
    private final List<Monument> monuments;
    private MonumentDAO dao;

    public MonumentsDisplayAdapter(List<Monument> results, String userID) {
        dao = new MonumentDAO(userID);
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
        String name = curItem.getParsedName();
        if (name != null) {
            holder.binding.monumentName.setText(name);
        }

        Double dist = curItem.getDist();
        if (dist != null) holder.binding.monumentDistance.setText(String.format("%.0f", dist) + "m");

        Picasso.get()
                .load(curItem.getImageURL())
                .fit()
                .centerCrop()
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.ic_menu_help)
                .into(holder.binding.monumentImage);

        //change activity by clicking on the image
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                //add the clicked monument to the database using its id as the key, and adding the date of the click
                dao.add(curItem.id, curItem);
                dao.update(curItem.id, new HashMap<String, Object>(){{put("time", new java.util.Date().toString());}});
                dao.isInDB(curItem.id, holder.binding);
                Toast.makeText(holder.itemView.getContext(), "Item Clicked", Toast.LENGTH_LONG).show();
                Intent myIntent = new Intent(holder.itemView.getContext(), Monument_picture_activity.class);
                myIntent.putExtra(EXTRA_MESSAGE, curItem.getImageURL());
                holder.itemView.getContext().startActivity(myIntent);
            }
        });

        dao.isInDB(curItem.id, holder.binding);

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
