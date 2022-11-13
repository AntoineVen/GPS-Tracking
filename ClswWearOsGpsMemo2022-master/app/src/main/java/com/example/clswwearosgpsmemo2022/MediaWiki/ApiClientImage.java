package com.example.clswwearosgpsmemo2022.MediaWiki;

import android.util.Log;

import com.example.clswwearosgpsmemo2022.WikiLovesMonument.ApiClientMonuments;
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

public class ApiClientImage {
    private static final String LOG_TAG = ApiClientMonuments.class.getSimpleName();

    public static String MONUMENT_API_BASE_URL = "https://heritage.toolforge.org/api/";

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