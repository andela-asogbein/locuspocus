package co.jibola.locus;


import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import co.jibola.locus.database.LocationBaseHelper;

public class LocationBaseHelperTest extends AndroidTestCase {
    LocationBaseHelper mLocationBaseHelper;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test");
        mLocationBaseHelper = new LocationBaseHelper(context);
    }

    public void testLocationBaseHelperIsCreated(){
        assertNotNull(mLocationBaseHelper);
    }

    public void testInsertLocation(){
        boolean insertSuccessfull = mLocationBaseHelper.insertLocation(1.344,4.433,"2/4/2015", 58035,"apataa, ibadan");
        assertEquals(insertSuccessfull, true);
    }


    public void testGetTimeSpentAtLocations() {
        long time = mLocationBaseHelper.getTimeSpentAtLocations("Apata Street");
        assertEquals(time, 0);
    }
}