package co.jibola.locus;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;


import co.jibola.locus.R;
import co.jibola.locus.database.LocationDbSchema;

import static co.jibola.locus.database.LocationDbSchema.*;

public class LocationDbSchemaTest extends AndroidTestCase {
    LocationDbSchema mLocationDbSchema;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test");
        mLocationDbSchema = new LocationDbSchema();
    }

    public void testSchemaIsCreated() throws Exception {
        assertNotNull(mLocationDbSchema);
    }

    public void testTableIsCreatedWithCorrectName() throws Exception {
        assertNotNull(LocationTable.NAME);
        assertEquals(LocationTable.NAME.toString(), "locations");
    }

    public void testColumnsAreCreatedWithCorrectNames() throws Exception{
        assertNotNull(LocationTable.Columns.LATITUDE);
        assertNotNull(LocationTable.Columns.LONGITUDE);
        assertNotNull(LocationTable.Columns.DATE);
        assertNotNull(LocationTable.Columns.DURATION);
        assertNotNull(LocationTable.Columns.ADDRESS);

        assertEquals(LocationTable.Columns.LATITUDE, "latitude");
        assertEquals(LocationTable.Columns.LONGITUDE, "longitude");
        assertEquals(LocationTable.Columns.DATE, "date");
        assertEquals(LocationTable.Columns.DURATION, "duration");
        assertEquals(LocationTable.Columns.ADDRESS, "address");
    }
}