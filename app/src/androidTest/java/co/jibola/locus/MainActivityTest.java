package co.jibola.locus;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private MainActivity activity;
    private Button mStartTrackingButton;
    private Button mStopTrackingButton;
    private TextView mTimeTextView;
    private SeekBar mTimeSeekBar;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
        mStartTrackingButton = (Button) activity.findViewById(R.id.start_tracking_button);
        mStopTrackingButton = (Button) activity.findViewById(R.id.stop_tracking_button);
        mTimeTextView = (TextView)activity.findViewById(R.id.time_textView);
        mTimeSeekBar = (SeekBar)activity.findViewById(R.id.time_seekBar);
    }

    public void testActivityExists() {
        assertNotNull(activity);
    }

    public void testActivityViews() throws Exception {
        assertNotNull(mStartTrackingButton);
        assertNotNull(mStopTrackingButton);
        assertNotNull(mTimeTextView);
        assertNotNull(mTimeSeekBar);
    }
}