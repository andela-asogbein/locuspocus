package co.jibola.locus;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity {

    private GoogleMap mMap;
    private double mLatitude;
    private double mLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mLatitude = getIntent().getDoubleExtra("latitude", 0);
        mLongitude = getIntent().getDoubleExtra("longitdue", 0);
        setUpMapIfNeeded(mLatitude, mLongitude);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded(mLatitude, mLongitude);
    }

    private void setUpMapIfNeeded(double latitude, double longitude) {
        if (mMap == null) {
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            if (mMap != null) {
                setUpMap(latitude, longitude);
            }
        }
    }

    private void setUpMap(double latitude, double longitude) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 17.0f) );
        mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("Marker"));
    }
}
