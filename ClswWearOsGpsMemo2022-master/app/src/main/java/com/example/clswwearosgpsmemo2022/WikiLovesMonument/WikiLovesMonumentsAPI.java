package com.example.clswwearosgpsmemo2022.WikiLovesMonument;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WikiLovesMonumentsAPI {
    @GET("api.php?action=search&coord=43.61668029999998,7.070247973514571&radius=3000&props=country|id|name|municipality|lat|lon")
    Call<Monuments> getMonuments();
}
