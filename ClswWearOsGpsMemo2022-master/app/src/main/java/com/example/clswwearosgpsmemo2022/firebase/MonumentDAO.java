package com.example.clswwearosgpsmemo2022.firebase;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.clswwearosgpsmemo2022.WikiLovesMonument.Monument;
import com.example.clswwearosgpsmemo2022.databinding.MonumentItemBinding;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MonumentDAO {
    private DatabaseReference databaseReference;
    public String userID;

    public MonumentDAO(String userID){
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://monumentwearable-default-rtdb.europe-west1.firebasedatabase.app");
        databaseReference = db.getReference(Monument.class.getSimpleName());
        this.userID = userID;
    }

    public Task<Void> add(String key, Monument monument){
        return databaseReference.child(userID).child(key).setValue(monument);
    }
    public Task<Void> update(String key, HashMap<String, Object> hashMap){
        return databaseReference.child(userID).child(key).updateChildren(hashMap);
    }
    public Task<Void> remove(String key){
        return databaseReference.child(userID).child(key).removeValue();
    }

    public void isInDB(String key, MonumentItemBinding item){
        if(key==null) return;
        DatabaseReference rootRef = databaseReference.child(userID).child(key);
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    //item.monumentName.setText("already visited");
                    item.visitedIcon.setVisibility(View.VISIBLE);
                } else {
                    //item.monumentName.setText("never visited");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed, how to handle?
            }
        });
    }
}
