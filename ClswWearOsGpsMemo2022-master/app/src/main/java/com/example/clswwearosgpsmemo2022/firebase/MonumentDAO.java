package com.example.clswwearosgpsmemo2022.firebase;

import com.example.clswwearosgpsmemo2022.WikiLovesMonument.Monument;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
}
