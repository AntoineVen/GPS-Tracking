package com.antoinev.tmdb;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TmdbApi {

    String KEY="f8c59b73c44d9240c1ded0a07da0d5f5";

    // TMDB API call example:
    // https://api.themoviedb.org/3/configuration?api_key=f8c59b73c44d9240c1ded0a07da0d5f5
    // https://api.themoviedb.org/3/person/popular?api_key=f8c59b73c44d9240c1ded0a07da0d5f5&page=1

    @GET("api.php?action=search&coord=43.61668029999998,7.070247973514571&radius=3000&props=country|id|name|municipality|lat|lon|image")
    Call<Root> getMonuments();
}
