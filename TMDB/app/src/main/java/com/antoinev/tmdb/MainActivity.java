package com.antoinev.tmdb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.antoinev.tmdb.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    public TmdbApi tmdbApi = null;
    int mCurrentPage = 1;
    List<Monument> results = new ArrayList<>();
    PersonListAdapter mPersonListAdapter = null;

    private final Context mContext = this;
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(ApiClient.get() != null){
            tmdbApi = ApiClient.get().create(TmdbApi.class);
        } else finish();

        binding.popularPersonRv.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.popularPersonRv.setLayoutManager(layoutManager);

        mPersonListAdapter = new PersonListAdapter(results);
        binding.popularPersonRv.setAdapter(mPersonListAdapter);

        refreshPopularPersons();
        setPageNumber();
    }

    private void refreshPopularPersons() {
        if (tmdbApi != null) {
            Call<Monuments> call = tmdbApi.getMonuments();
            binding.progressWheel.setVisibility(View.VISIBLE);
            call.enqueue(new Callback<Monuments>() {
                @Override
                public void onResponse(@NonNull Call<Monuments> call, @NonNull Response<Monuments> response) {
                    results.clear();
                    if (response.code() == 200) {
                        Monuments monumentResponse = response.body();
                        if (monumentResponse != null && monumentResponse.getMonument() != null) {
                            results.addAll(monumentResponse.getMonument());
                            Log.d(LOG_TAG, "Number of popular person found=" + results.size());
                        }
                    } else {
                        Log.e(LOG_TAG, "HTTP error " + response.code());
                        results.clear();
                        Toast toast = Toast.makeText(mContext, R.string.toast_http_error, Toast.LENGTH_LONG);
                        toast.show();

                    }

                    mPersonListAdapter.notifyItemRangeChanged(0, results.size());
                    binding.popularPersonRv.scrollToPosition(0);
                    setPageNumber();
                    binding.progressWheel.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(@NonNull Call<Monuments> call, @NonNull Throwable t) {
                    Log.e(LOG_TAG, "Call to 'getPersonPopular' failed");
                    Log.e(LOG_TAG, t.toString());
                    mCurrentPage = 1;
                    setPageNumber();
                    binding.progressWheel.setVisibility(View.GONE);

                }
            });

        } else {
            Log.e(LOG_TAG, "Api not initialized");
        }
    }

    // helper to update page number caption
    private void setPageNumber() {
        String caption = getResources().getString(R.string.page_number, mCurrentPage);
        //mPageNumberTv.setText(caption);
        binding.pageNumberTv.setText(caption);
    }

    public void onClickPrevious(View view) {
        if (mCurrentPage > 1) {
            mCurrentPage--;
            refreshPopularPersons();
        } else {
            Toast toast = Toast.makeText(this, R.string.toast_no_previous_page, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void onClickNext(View view) {
        mCurrentPage++;
        refreshPopularPersons();
    }
}