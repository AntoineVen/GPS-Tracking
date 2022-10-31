package com.example.clswwearosgpsmemo2022;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.wear.widget.WearableRecyclerView;

import com.example.clswwearosgpsmemo2022.databinding.ItemMonumentBinding;

public class MonumentAdapter2 extends WearableRecyclerView.Adapter<MonumentAdapter2.MonumentViewHolder>{
    @NonNull
    @Override
    public MonumentAdapter2.MonumentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MonumentAdapter2.MonumentViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class MonumentViewHolder extends WearableRecyclerView.ViewHolder {
        ItemMonumentBinding binding;
        public MonumentViewHolder(@NonNull ItemMonumentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
