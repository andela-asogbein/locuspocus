package co.jibola.locus.listByTimeSpent;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import co.jibola.locus.R;
import co.jibola.locus.database.LocationBaseHelper;
import co.jibola.locus.models.Location;

public class LocationsByTimesSpentAdapter extends ArrayAdapter{
    private Activity activity;
    private ArrayList<String> address;
    private static LayoutInflater inflater = null;
    private LocationBaseHelper mLocationBaseHelper;


    public LocationsByTimesSpentAdapter (Activity activity, int textViewResourceId, ArrayList<String> address) {
        super(activity, textViewResourceId, address);
        try {
            this.activity = activity;
            this.address = address;
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mLocationBaseHelper = new LocationBaseHelper(activity);
        } catch (Exception e) {
        }
    }

    public static class ViewHolder {
        public TextView locationAddress;
        public TextView timeSpentAtLocation;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final ViewHolder viewHolder;
        try {
            if (convertView == null) {
                view = inflater.inflate(R.layout.location_and_time_spent, null);
                viewHolder = new ViewHolder();

                viewHolder.locationAddress = (TextView) view.findViewById(R.id.location_address);
                viewHolder.timeSpentAtLocation = (TextView)view.findViewById(R.id.total_time_spent_at_location);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

            viewHolder.locationAddress.setText(address.get(position).toString());
            long timeSpent  = mLocationBaseHelper.getTimeSpentAtLocations(address.get(position).toString());

            viewHolder.timeSpentAtLocation.setText("You have spent a total of " + timeSpent/60000 +" minutes at this location");
        } catch (Exception e) {}
        return view;
    }
}