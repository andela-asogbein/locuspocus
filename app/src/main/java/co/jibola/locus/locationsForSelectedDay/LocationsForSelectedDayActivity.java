package co.jibola.locus.locationsForSelectedDay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import co.jibola.locus.MapsActivity;
import co.jibola.locus.R;
import co.jibola.locus.database.LocationBaseHelper;
import co.jibola.locus.models.Location;

public class LocationsForSelectedDayActivity extends AppCompatActivity {
    private LocationBaseHelper mLocationBaseHelper;
    private TextView mDateHeader;
    private String date;
    private ListView mlistOfLocations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations_for_selected_day);

        mLocationBaseHelper = new LocationBaseHelper(this);

        date = getIntent().getStringExtra("date");
        mDateHeader = (TextView)findViewById(R.id.selected_date_textview);
        mDateHeader.setText(date);

        mlistOfLocations = (ListView)findViewById(R.id.listViewoflocations);
        populateListView(date);
    }

    private void populateListView(String d){
        LocationsForSelectedDayAdapter locationsForSelectedDayAdapter;
        ArrayList<Location> myListItems  = mLocationBaseHelper.getDateLocations(d);
        locationsForSelectedDayAdapter = new LocationsForSelectedDayAdapter(LocationsForSelectedDayActivity.this, 0, myListItems);
        mlistOfLocations.setAdapter(locationsForSelectedDayAdapter);

        mlistOfLocations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Location l = (Location) mlistOfLocations.getItemAtPosition(position);
                Intent mapIntent = new Intent(getApplicationContext(), MapsActivity.class);
                mapIntent.putExtra("latitude", l.getLatitude());
                mapIntent.putExtra("longitdue", l.getLongitude());
                startActivity(mapIntent);
            }
        });
    }
}