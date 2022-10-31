package com.antoinev.tmdb;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.antoinev.tmdb.databinding.PersonItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PersonListAdapter extends RecyclerView.Adapter<PersonListAdapter.PersonViewHolder> {
    private final List<PersonData> persons;

    public PersonListAdapter(List<PersonData> results) {
        this.persons = results;

    }

    @NonNull
    @Override
    public PersonListAdapter.PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        PersonItemBinding binding = PersonItemBinding.inflate(layoutInflater, parent, false);

        return new PersonViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonListAdapter.PersonViewHolder holder, int position) {
        PersonData curItem = persons.get(position);
        String name = persons.get(position).getName();
        if (name != null) holder.binding.personName.setText(persons.get(position).getName());

        double popularity = persons.get(position).getPopularity();
        holder.binding.popularity.setText(String.valueOf(popularity));

        Picasso.get()
                .load(ApiClient.IMAGE_BASE_URL + curItem.getProfilePath())
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.ic_menu_help)
                .into(holder.binding.imageView2);
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder{

        PersonItemBinding binding;

        public PersonViewHolder(@NonNull PersonItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }


    }
}
