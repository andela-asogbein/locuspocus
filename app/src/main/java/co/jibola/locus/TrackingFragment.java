package co.jibola.locus;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class TrackingFragment extends Fragment {

    private Button mStartTrackingButton;
    private TextView mTimeTextView;
    private SeekBar mTimeSeekBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tracking, container, false);
        initialiseWidgets(v);
        return v;
    }

    public void initialiseWidgets(View v){
        mStartTrackingButton = (Button)v.findViewById(R.id.start_tracking_button);
        mTimeSeekBar = (SeekBar)v.findViewById(R.id.time_seekBar);

        final int step = 1;
        int max = 10;
        final int min = 0;
        mTimeSeekBar.setMax((max - min) / step);
        mTimeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressValue;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressValue = progress;
                mTimeTextView.setText(min + (progress * step) + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mTimeTextView.setText(min + (progressValue * step) + "");
            }
        });
        mTimeTextView = (TextView)v.findViewById(R.id.time_textView);
        mTimeTextView.setText(mTimeSeekBar.getProgress()+"");

    }
}
