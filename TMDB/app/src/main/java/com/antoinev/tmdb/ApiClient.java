package com.antoinev.tmdb;

import android.util.Log;

import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String LOG_TAG = ApiClient.class.getSimpleName();

    //public static String TMDBAPI_BASE_URL = "https://api.themoviedb.org/3/";
    //public static String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w45";

    public static String MONUMENT_API_BASE_URL = "https://heritage.toolforge.org/api/";
    //public static String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w45";

    private static Retrofit retrofit = null;

    static Retrofit get() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        try {
            retrofit = new Retrofit.Builder()
                    .baseUrl(MONUMENT_API_BASE_URL)
                    .addConverterFactory(TikXmlConverterFactory.create())
                    .client(client)
                    .build();
        } catch (IllegalArgumentException e) {
            Log.e(LOG_TAG, e.getMessage());
        }

        return retrofit;
    }

}
