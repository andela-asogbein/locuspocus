package co.jibola.locus.locationsForSelectedDay;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

import co.jibola.locus.R;
import co.jibola.locus.models.Location;

public class LocationsForSelectedDayAdapter extends ArrayAdapter{
    private Activity activity;
    private ArrayList<Location> location;
    private static LayoutInflater inflater = null;

    public LocationsForSelectedDayAdapter(Activity activity, int textViewResourceId, ArrayList<Location> location) {

        super(activity, textViewResourceId, location);
        try {
            this.activity = activity;
            this.location = location;
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        } catch (Exception e) {
        }
    }

    public int getCount() {
        return location.size();
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView display_address;
        public TextView display_coordinates;
        public TextView display_time;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final ViewHolder viewHolder;
        try {
            if (convertView == null) {
                view = inflater.inflate(R.layout.custom_list_view_for_date_list, null);
                viewHolder = new ViewHolder();

                viewHolder.display_address = (TextView) view.findViewById(R.id.adress_of_location);
                viewHolder.display_coordinates = (TextView)view.findViewById(R.id.coordinates_of_location);
                viewHolder.display_time = (TextView) view.findViewById(R.id.time_spent_at_location);

                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

            viewHolder.display_address.setText(location.get(position).getAddress());
            viewHolder.display_coordinates.setText(location.get(position).getLatitude()+" , "+location.get(position).getLongitude());
            viewHolder.display_time.setText("You spent " +(location.get(position).getDuration()/60000) +" minutes at this location");
        } catch (Exception e) {}
        return view;
    }
}