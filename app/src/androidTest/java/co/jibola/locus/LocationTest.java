package co.jibola.locus;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import co.jibola.locus.database.LocationDbSchema;
import co.jibola.locus.models.Location;

public class LocationTest extends AndroidTestCase {
    private Location mLocation;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mLocation = new Location();
    }

    public void testClassIsCreated(){
        assertNotNull(mLocation);
    }
}