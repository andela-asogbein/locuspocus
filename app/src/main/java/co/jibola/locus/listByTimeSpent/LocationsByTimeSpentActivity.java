package co.jibola.locus.listByTimeSpent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import co.jibola.locus.MapsActivity;
import co.jibola.locus.R;
import co.jibola.locus.database.LocationBaseHelper;
import co.jibola.locus.models.Location;

public class LocationsByTimeSpentActivity extends AppCompatActivity {

    private ListView mlistOfLocations;
    private LocationBaseHelper mLocationBaseHelper;
    private Random randomGenerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations_by_time_spent);
        randomGenerator = new Random();
        mlistOfLocations = (ListView)findViewById(R.id.listViewx);
        mLocationBaseHelper = new LocationBaseHelper(this);

        populateListView();
    }

    private void populateListView(){
        LocationsByTimesSpentAdapter adapter = new LocationsByTimesSpentAdapter(this, 0, mLocationBaseHelper.getUniqueAddresses());
        mlistOfLocations.setAdapter(adapter);

        mlistOfLocations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String addr = mlistOfLocations.getItemAtPosition(position).toString();
                ArrayList<Location> records = mLocationBaseHelper.getAddressLocationRecords(addr);
                int index = randomGenerator.nextInt(records.size());
                Location randomLocation = records.get(index);

                Intent mapIntent = new Intent(getApplicationContext(), MapsActivity.class);
                mapIntent.putExtra("latitude", randomLocation.getLatitude());
                mapIntent.putExtra("longitdue", randomLocation.getLongitude());
                startActivity(mapIntent);

            }
        });
    }
}