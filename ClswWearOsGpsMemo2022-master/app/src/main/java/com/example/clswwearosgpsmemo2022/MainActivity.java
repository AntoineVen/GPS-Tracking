package com.example.clswwearosgpsmemo2022;

import static androidx.wear.activity.ConfirmationActivity.EXTRA_MESSAGE;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.wear.activity.ConfirmationActivity;
import androidx.wear.ambient.AmbientModeSupport;
import androidx.wear.ambient.AmbientModeSupport.AmbientCallback;
import androidx.wear.widget.WearableLinearLayoutManager;

import com.example.clswwearosgpsmemo2022.WikiLovesMonument.ApiClientMonuments;
import com.example.clswwearosgpsmemo2022.WikiLovesMonument.Monument;
import com.example.clswwearosgpsmemo2022.WikiLovesMonument.Monuments;
import com.example.clswwearosgpsmemo2022.WikiLovesMonument.WikiLovesMonumentsAPI;
import com.example.clswwearosgpsmemo2022.databinding.ActivityMainBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// inherit from FragmentActivity for Ambient mode support
public class MainActivity extends FragmentActivity implements AmbientModeSupport.AmbientCallbackProvider {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final String LOC_PROVIDER = "WATCH";
    private static final int REQUEST_COARSE_AND_FINE_LOCATION_RESULT_CODE = 101;
    public static final long LOCATION_UPDATE_INTERVAL = 60000; // duration in milliseconds
    private static final int MAX_LOCATION_RECORDED = 10;
    public static String pictureURL;

    public WikiLovesMonumentsAPI tmdbApi = null;
    List<Monument> monumentsList = new ArrayList<>();
    MonumentsDisplayAdapter monumentsListAdapter = null;

    private final Context mContext = this;
    private ActivityMainBinding binding;


    // Location
    private FusedLocationProviderClient fusedLocationClient;

    // Vibration
    private Vibrator vibrator;
    private long[] vibrationPattern = {0, 150, 50, 50};
    //-1 - don't repeat
    private final int indexInPatternToRepeat = -1;

    // Warning this is not a method, but a member data declaration
    private final LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(@NonNull LocationResult locationResult) {
            Log.d(LOG_TAG, "onLocationResult(" + locationResult.getLastLocation() + ")");
            if (locationResult.getLocations().size() > 0) {
                updateNearbyMonuments(locationResult.getLastLocation());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        Log.d(LOG_TAG, "onCreate()");

        // Init view binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*
         * Attach an ambient mode controller, which will be used by
         * the activity to determine if the current mode is ambient.
         */
        AmbientModeSupport.AmbientController ambientController = AmbientModeSupport.attach(this);
        /*
         * activity's task will be moved to the front if it was the last activity to be running
         *  when ambient started, depending on how much time the system spent in ambient mode.
         */
        ambientController.setAutoResumeEnabled(true);

        monumentsList.add(new Monument("Searching Monuments..."));

        if(ApiClientMonuments.get() != null){
            tmdbApi = ApiClientMonuments.get().create(WikiLovesMonumentsAPI.class);
        } else finish();

        // Ensure watch has an embedded physical GPS for the application to work
        if (hasGps()) {
            Log.d(LOG_TAG, "Found standalone GPS hardware");
            // dummy init for test purpose only
            //locations.add(makeLoc(0.0d, 0.0));

            // Wearable recycler view init
            binding.monumentsWrv.setEdgeItemsCenteringEnabled(true);
            binding.monumentsWrv.setLayoutManager(new WearableLinearLayoutManager(this));

            //If there is no userID, we create one in the local storage. userID will then be used to stock and access our online data in Firebase.
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            String userID = preferences.getString("userID", "NOT INSTANCIATED");
            if(userID == "NOT INSTANCIATED"){
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("userID", UUID.randomUUID().toString());
                editor.commit();
                userID = preferences.getString("userID", "NOT INSTANCIATED");
            }
            Log.d("USERID", userID);
            monumentsListAdapter = new MonumentsDisplayAdapter(monumentsList, userID);
            binding.monumentsWrv.setAdapter(monumentsListAdapter);

            // Location init
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

            // Check for coarse and fine location permission
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                Log.d(LOG_TAG, "All required location permission are already granted");
                startLocationUpdates();
            } else {
                Log.d(LOG_TAG, "ask for coarse and fine location permission");
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_FINE_LOCATION,
                        },
                        REQUEST_COARSE_AND_FINE_LOCATION_RESULT_CODE);
            }

        } else {
            Log.e(LOG_TAG, "This hardware doesn't have GPS.");
            noGpsExitConfirmation();
        }
    }

    private void updateNearbyMonuments(Location location){
        if (tmdbApi != null) {
            String coord = location.getLatitude() + "," + location.getLongitude();
            Call<Monuments> call = tmdbApi.getMonuments(coord);
            call.enqueue(new Callback<Monuments>() {
                @Override
                public void onResponse(@NonNull Call<Monuments> call, @NonNull Response<Monuments> response) {
                    if (response.code() == 200) {
                        Monuments monumentResponse = response.body();
                        if (monumentResponse != null && monumentResponse.getMonument() != null) {
                            //stock la list des monuments récupérés lors de la requête
                            List<Monument> monuments = monumentResponse.getMonument();
                            for (Monument m : monuments) {
                                if (monumentsList.stream().noneMatch(x -> Objects.equals(x.id, m.id))){
                                    vibrator.vibrate(vibrationPattern, indexInPatternToRepeat);
                                    break;
                                }
                            }
                            monumentsList.clear();
                            monumentsList.addAll(monumentResponse.getMonument());
                            Log.d(LOG_TAG, "Number of monuments found = " + monumentsList.size());
                        }
                    } else {
                        Log.e(LOG_TAG, "HTTP error " + response.code());
                        monumentsList.clear();
                        Toast toast = Toast.makeText(mContext, "error", Toast.LENGTH_LONG);
                        toast.show();

                    }

                    monumentsListAdapter.notifyItemRangeChanged(0, monumentsList.size());
                }

                @Override
                public void onFailure(@NonNull Call<Monuments> call, @NonNull Throwable t) {
                    Log.e(LOG_TAG, "Call to 'updateNearbyMonument' failed");
                    Log.e(LOG_TAG, t.toString());

                }
            });

        } else {
            Log.e(LOG_TAG, "Api not initialized");
        }
    }

    @SuppressLint("MissingPermission")
    public void startLocationUpdates() {
        Log.d(LOG_TAG, "startLocationUpdates()");

        // create location request
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(LOCATION_UPDATE_INTERVAL);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
        );
    }

    public void stopLocationUpdates() {
        Log.d(LOG_TAG, "stopLocationUpdates()");
        fusedLocationClient.removeLocationUpdates(locationCallback);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(LOG_TAG, "onRequestPermissionsResult(): " + Arrays.toString(permissions));
        if (requestCode == REQUEST_COARSE_AND_FINE_LOCATION_RESULT_CODE) {
            Log.i(LOG_TAG, "Received response for GPS permission request");
            if (grantResults.length == 2 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Log.i(LOG_TAG, "GPS permission granted.");
                startLocationUpdates();
            } else {
                Log.i(LOG_TAG, "GPS permission NOT granted.");
            }
        } else {
            Log.e(LOG_TAG, "Unhandled Request Permissions Result code");
        }
    }

    /* *********************************************************************************************
     * HELPER METHODS
     * *********************************************************************************************
     */
    private boolean hasGps() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS);
    }

    public void noGpsExitConfirmation() {
        Intent intent = new Intent(this, ConfirmationActivity.class);
        intent.putExtra(ConfirmationActivity.EXTRA_ANIMATION_TYPE, ConfirmationActivity.FAILURE_ANIMATION);
        intent.putExtra(ConfirmationActivity.EXTRA_ANIMATION_DURATION_MILLIS, 3000);
        intent.putExtra(ConfirmationActivity.EXTRA_MESSAGE, getString(R.string.no_gps_message));
        startActivity(intent);
        finish();
    }

    private Location makeLoc(double lat, double lon) {
        Location location = new Location(LOC_PROVIDER);
        Date now = new Date();
        location.setLatitude(lat);
        location.setLongitude(lon);
        location.setTime(now.getTime());
        return location;
    }

    @Override
    public AmbientCallback getAmbientCallback() {
        Log.d(LOG_TAG,"getAmbientCallback");
        return new CustomAmbientCallback(this);
    }

    /*
     * Inner class for ambient mde call back implementation
     */
    public static class CustomAmbientCallback extends AmbientCallback {
        private static final String LOG_TAG= CustomAmbientCallback.class.getSimpleName();
        MainActivity activity;

        // Ctor
        CustomAmbientCallback(MainActivity activity) {
            this.activity = activity;
        }

        @Override
        public void onEnterAmbient(Bundle ambientDetails) {
            super.onEnterAmbient(ambientDetails);
            Log.d(LOG_TAG, "onEnterAmbient() " + ambientDetails);
            activity.stopLocationUpdates();
        }

        @Override
        public void onExitAmbient() {
            super.onExitAmbient();
            Log.d(LOG_TAG, "onExitAmbient()");
            activity.startLocationUpdates();

        }
    }
}