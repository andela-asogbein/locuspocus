package co.jibola.locus;

import android.test.ActivityInstrumentationTestCase2;
import android.test.RenamingDelegatingContext;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import co.jibola.locus.database.LocationBaseHelper;
import co.jibola.locus.listByDate.ListByDateActivity;
import co.jibola.locus.listByDate.ListByDateAdapter;

public class ListByDateActivityTest  extends ActivityInstrumentationTestCase2<ListByDateActivity>{

    private ListByDateActivity mListByDateActivity;
    private ListView mlistOfDates;
    private LocationBaseHelper mLocationBaseHelper;
    private ListByDateAdapter mListByDateAdapter;

    public ListByDateActivityTest() {
        super(ListByDateActivity.class);
    }

        @Override
        public void setUp() throws Exception {
            super.setUp();
            mListByDateActivity = getActivity();
            mlistOfDates = (ListView) mListByDateActivity.findViewById(R.id.dates_listView);
            RenamingDelegatingContext context = new RenamingDelegatingContext(getActivity(), "test");
            mLocationBaseHelper = new LocationBaseHelper(context);
        }

    public void testActivityWasCreated(){
        assertNotNull(mListByDateActivity);
    }

    public void testListWasCreated(){
        assertNotNull(mlistOfDates);
    }
}