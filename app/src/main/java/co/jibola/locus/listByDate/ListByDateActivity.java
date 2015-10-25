package co.jibola.locus.listByDate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import co.jibola.locus.locationsForSelectedDay.LocationsForSelectedDayActivity;
import co.jibola.locus.R;
import co.jibola.locus.database.LocationBaseHelper;

public class ListByDateActivity extends AppCompatActivity {

    private ListView mlistOfDates;
    private LocationBaseHelper mLocationBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_by_date);

        mlistOfDates = (ListView)findViewById(R.id.dates_listView);
        mLocationBaseHelper = new LocationBaseHelper(this);

        populateListView();
    }

    private void populateListView(){
        ListByDateAdapter adapter = new ListByDateAdapter(this, 0, mLocationBaseHelper.getUniqueDates());
        mlistOfDates.setAdapter(adapter);
        mlistOfDates.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                String date = mlistOfDates.getItemAtPosition(position).toString();
                Intent i = new Intent(getApplicationContext(), LocationsForSelectedDayActivity.class);
                i.putExtra("date", date);
                startActivity(i);
            }
        });
    }
}