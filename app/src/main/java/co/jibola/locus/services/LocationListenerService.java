package co.jibola.locus.services;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import co.jibola.locus.database.LocationBaseHelper;

public class LocationListenerService extends Service implements LocationListener, ConnectionCallbacks, OnConnectionFailedListener{
    public static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
    public static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = UPDATE_INTERVAL_IN_MILLISECONDS / 2;

    private Location mCurrentLocation;
    GoogleApiClient mGoogleApiClient;
    protected LocationRequest mLocationRequest;

    private long mills1;
    private long mills2;
    private int minutesToTrackLocation;

    LocationBaseHelper mLocationBaseHelper;

    public LocationListenerService(){
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        minutesToTrackLocation = intent.getIntExtra("minutesToTrackLocation", 1);
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        buildGoogleApiClient();
        mGoogleApiClient.connect();

        mLocationBaseHelper = new LocationBaseHelper(this);
    }

    public void startLocationUpdates() {
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        Toast.makeText(this, "Keeping track of locations you stay in for "+ minutesToTrackLocation +" minute(s)", Toast.LENGTH_SHORT).show();
        mills1 = new Date().getTime();
    }

    public void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        Toast.makeText(this, "Location tracking stopped", Toast.LENGTH_SHORT).show();
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        this.createLocationRequest();
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setSmallestDisplacement(1);
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
    }

    @Override
    public void onLocationChanged(Location location) {
        mCurrentLocation = location;
        mills2 = new Date().getTime();
        if(mills2-mills1 >= (minutesToTrackLocation * 60 * 1000)){
            String address = getAddress(location.getLatitude(), location.getLongitude());
            mLocationBaseHelper.insertLocation(location.getLatitude(), location.getLongitude(), currentDate(), mills2 - mills1, address);
        }
        mills1 = mills2;
    }

    public String currentDate(){
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(new Date());
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        startLocationUpdates();
    }

    @Override
    public void onConnectionSuspended(int cause) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopLocationUpdates();
    }

    public String getAddress(double latitude, double longitude) {
        String address = "No address found for this location";
        Geocoder geoCoder = new Geocoder(this, Locale.getDefault());
        try {
            address = geoCoder.getFromLocation(latitude, longitude, 1).get(0).getAddressLine(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return address;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}