package co.jibola.locus;

import android.test.ActivityInstrumentationTestCase2;
import android.test.RenamingDelegatingContext;
import android.widget.ListView;
import android.widget.TextView;

import co.jibola.locus.database.LocationBaseHelper;
import co.jibola.locus.listByDate.ListByDateActivity;
import co.jibola.locus.locationsForSelectedDay.LocationsForSelectedDayActivity;

public class LocationsForSelectedDayActivityTest extends ActivityInstrumentationTestCase2<LocationsForSelectedDayActivity>{

    private LocationsForSelectedDayActivity mLocationsForSelectedDayActivity;
    private TextView mDateHeader;
    private String date;
    private ListView mlistOfLocations;

    public LocationsForSelectedDayActivityTest() {
        super(LocationsForSelectedDayActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mLocationsForSelectedDayActivity = getActivity();
        mlistOfLocations = (ListView) mLocationsForSelectedDayActivity.findViewById(R.id.listViewoflocations);
        mDateHeader = (TextView)mLocationsForSelectedDayActivity.findViewById(R.id.selected_date_textview);
        date = "5/6/2016";
    }

    public void testActivityWasCreated(){
        assertNotNull(mLocationsForSelectedDayActivity);
    }

    public void testViews(){
        assertNotNull(mlistOfLocations);
        assertNotNull(mDateHeader);
    }
}