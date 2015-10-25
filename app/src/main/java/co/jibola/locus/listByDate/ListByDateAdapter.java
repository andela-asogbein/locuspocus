package co.jibola.locus.listByDate;

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

public class ListByDateAdapter extends ArrayAdapter{
    private Activity activity;
    private ArrayList<String> date;
    private static LayoutInflater inflater = null;
    private LocationBaseHelper mLocationBaseHelper;


    public ListByDateAdapter (Activity activity, int textViewResourceId, ArrayList<String> date) {
        super(activity, textViewResourceId, date);
        try {
            this.activity = activity;
            this.date = date;
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mLocationBaseHelper = new LocationBaseHelper(activity);
        } catch (Exception e) {
        }
    }

    public static class ViewHolder {
        public TextView display_date;
        public TextView display_numberOfLocations;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final ViewHolder viewHolder;
        try {
            if (convertView == null) {
                view = inflater.inflate(R.layout.list_and_number_of_locations, null);
                viewHolder = new ViewHolder();

                viewHolder.display_date = (TextView) view.findViewById(R.id.date);
                viewHolder.display_numberOfLocations = (TextView)view.findViewById(R.id.number_of_locations);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

            viewHolder.display_date.setText(date.get(position).toString());
            ArrayList<Location> myListItems  = mLocationBaseHelper.getDateLocations(date.get(position).toString());
            viewHolder.display_numberOfLocations.setText(myListItems.size()+" Locations");
        } catch (Exception e) {}
        return view;
    }
}