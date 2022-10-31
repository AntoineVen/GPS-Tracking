package com.example.clswwearosgpsmemo2022;

import android.util.Log;

import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

public class ApiImageClient {
    private static final String LOG_TAG  = ApiImageClient.class.getSimpleName();
    private static String MONUMENT_IMAGE_API_BASE_URL = "https://www.mediawiki.org/w/api/";
    private static Retrofit retrofit = null;

    static Retrofit get() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        try {
            retrofit = new Retrofit.Builder()
                    .baseUrl(MONUMENT_IMAGE_API_BASE_URL)
                    .addConverterFactory(TikXmlConverterFactory.create())
                    .client(client)
                    .build();
        } catch (IllegalArgumentException e) {
            Log.e(LOG_TAG, e.getMessage());
        }

        return retrofit;
    }

}
