package com.major.dustincontroller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.major.dustincontroller.model.Level;
import com.major.dustincontroller.model.Location;
import com.major.dustincontroller.utils.APIClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Faiz on 19-05-2016.
 */
public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private final static String TAG = "MainActivity";

    private GoogleMap mMap;
    private Location mCurrentLocation;
    private Level mCurrentLevel;
    private String HOST;

    private TextView mLevel, mLevelTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mLevel = (TextView) findViewById(R.id.level_status);
        mLevelTime = (TextView) findViewById(R.id.level_time);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        HOST = prefs.getString(getResources().getString(R.string.pref_address_key), "");
        if (!HOST.isEmpty()) {
            getLocation();
            getLevel();
        } else {
            Toast.makeText(this, "Web Service Address is not set!", Toast.LENGTH_SHORT).show();
        }

    }

    private void getLocation() {
        Call<Location> location = APIClient.getInstance(HOST).getApi().getLocation();
        location.enqueue(new Callback<Location>() {
            @Override
            public void onResponse(Call<Location> call, Response<Location> response) {
                if (response.isSuccessful()) {
                    mCurrentLocation = response.body();
                    if (mCurrentLocation != null) {
                        setLocation(mCurrentLocation);
                    } else {
                        Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Location> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getLevel() {
        Call<Level> level = APIClient.getInstance(HOST).getApi().getLevel();
        level.enqueue(new Callback<Level>() {
            @Override
            public void onResponse(Call<Level> call, Response<Level> response) {
                if (response.isSuccessful()) {
                    mCurrentLevel = response.body();
                    if (mCurrentLevel != null) {
                        updateUI(mCurrentLevel);
                    } else {
                        Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Level> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                intent.putExtra(PreferenceActivity.EXTRA_SHOW_FRAGMENT, SettingsActivity.GeneralPreferenceFragment.class.getName());
                intent.putExtra(PreferenceActivity.EXTRA_NO_HEADERS, true);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (mCurrentLocation != null) {
            setLocation(mCurrentLocation);
        }
    }

    private void setLocation(Location location) {
        LatLng dustInLocation = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(dustInLocation).title("DustIn Location"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(dustInLocation, 17.0f));
    }

    private void updateUI(Level level) {
        mLevel.setText(level.getLevelStatus());
        mLevelTime.setText(level.getLevelTime());
    }
}
