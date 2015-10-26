package co.jibola.locus;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.RenamingDelegatingContext;
import android.widget.ListView;

import co.jibola.locus.database.LocationBaseHelper;
import co.jibola.locus.listByDate.ListByDateActivity;
import co.jibola.locus.listByTimeSpent.LocationsByTimeSpentActivity;

public class ListByTimeSpentActivityTest extends ActivityInstrumentationTestCase2<LocationsByTimeSpentActivity> {

    private LocationsByTimeSpentActivity mLocationsByTimeSpentActivity;
    private ListView mlistOfLocations;

    public ListByTimeSpentActivityTest() {
        super(LocationsByTimeSpentActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mLocationsByTimeSpentActivity = getActivity();
        mlistOfLocations = (ListView) mLocationsByTimeSpentActivity.findViewById(R.id.listViewx);
        RenamingDelegatingContext context = new RenamingDelegatingContext(getActivity(), "test");
    }

    public void testActivityWasCreated(){
        assertNotNull(mLocationsByTimeSpentActivity);
    }

    public void testListWasCreated(){
        assertNotNull(mlistOfLocations);
    }
}