package com.example.clswwearosgpsmemo2022;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MediaWikiApi {

    String KEY="";

    // MediaWiki API call example:
    // https://api.themoviedb.org/3/configuration?api_key=f8c59b73c44d9240c1ded0a07da0d5f5
    // https://api.themoviedb.org/3/person/popular?api_key=f8c59b73c44d9240c1ded0a07da0d5f5&page=1

    @GET("api.php?action=query&titles=File:Fontaine%20de%20l%27Hotel%20de%20Ville%20(Aix%20en%20Provence)%201.jpg&prop=imageinfo&iiprop=url")
    Call<MonumentImagesResponse> getMonumentImages(
            @retrofit2.http.Query("api_key") String apiKey
    );
}
