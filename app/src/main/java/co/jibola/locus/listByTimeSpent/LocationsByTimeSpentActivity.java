package co.jibola.locus.listByTimeSpent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import co.jibola.locus.R;
import co.jibola.locus.database.LocationBaseHelper;

public class LocationsByTimeSpentActivity extends AppCompatActivity {

    private ListView mlistOfLocations;
    private LocationBaseHelper mLocationBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations_by_time_spent);

        mlistOfLocations = (ListView)findViewById(R.id.listViewx);
        mLocationBaseHelper = new LocationBaseHelper(this);

        populateListView2();
    }

    private void populateListView(){
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,  mLocationBaseHelper.getUniqueLocations());
        mlistOfLocations.setAdapter(adapter);
        mlistOfLocations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                String location = mlistOfLocations.getItemAtPosition(position).toString();
                long timeSpent = mLocationBaseHelper.getTimeSpentAtLocations(location);
                Toast.makeText(getApplicationContext(), "You spent " + timeSpent + " here", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateListView2(){
        LocationsByTimesSpentAdapter adapter = new LocationsByTimesSpentAdapter(this, 0, mLocationBaseHelper.getUniqueLocations());
        mlistOfLocations.setAdapter(adapter);
    }
}