package com.example.clswwearosgpsmemo2022.WikiLovesMonument;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WikiLovesMonumentsAPI {
    @GET("api.php?action=search&radius=3000&props=country|id|name|municipality|lat|lon|image")
    Call<Monuments> getMonuments(
            @retrofit2.http.Query("coord") String coord
    );
}
