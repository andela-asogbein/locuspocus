package co.jibola.locus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import co.jibola.locus.services.LocationListenerService;

public class TrackingFragment extends Fragment {

    private Button mStartTrackingButton;
    private Button mStopTrackingButton;
    private TextView mTimeTextView;
    private SeekBar mTimeSeekBar;
    Intent mService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tracking, container, false);
        initialiseWidgets(v);
        mService = new Intent(getActivity(), LocationListenerService.class);
        return v;
    }

    public void initialiseWidgets(View v){
        mStartTrackingButton = (Button)v.findViewById(R.id.start_tracking_button);
        mStartTrackingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mService.putExtra("minutesToTrackLocation", mTimeSeekBar.getProgress());

                getActivity().startService(mService);
                mStopTrackingButton.setVisibility(View.VISIBLE);
                mStartTrackingButton.setVisibility(View.INVISIBLE);
                mTimeSeekBar.setEnabled(false);
            }
        });

        mStopTrackingButton = (Button)v.findViewById(R.id.stop_tracking_button);
        mStopTrackingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().stopService(mService);
                mStopTrackingButton.setVisibility(View.INVISIBLE);
                mStartTrackingButton.setVisibility(View.VISIBLE);
                mTimeSeekBar.setEnabled(true);
            }
        });

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