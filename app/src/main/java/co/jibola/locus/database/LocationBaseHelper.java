package co.jibola.locus.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import co.jibola.locus.models.Location;

import static co.jibola.locus.database.LocationDbSchema.*;

public class LocationBaseHelper extends SQLiteOpenHelper{
    public static final  int VERSION = 1;
    public static final String DATABASE_NAME = "locationBase.db";
    SQLiteDatabase db;

    public LocationBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + LocationTable.NAME + "(" +
                        " _id integer primary key autoincrement, " +
                        LocationTable.Columns.LATITUDE + ", " +
                        LocationTable.Columns.LONGITUDE + ", " +
                        LocationTable.Columns.DATE + ", " +
                        LocationTable.Columns.DURATION + ", " +
                        LocationTable.Columns.ADDRESS +
                        ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean insertLocation(double latitude, double longitude, String date, long duration, String address){
        ContentValues contentValues = new ContentValues();
        contentValues.put(LocationTable.Columns.LATITUDE, latitude);
        contentValues.put(LocationTable.Columns.LONGITUDE, longitude);
        contentValues.put(LocationTable.Columns.DATE, date);
        contentValues.put(LocationTable.Columns.DURATION, duration);
        contentValues.put(LocationTable.Columns.ADDRESS, address);

        long result = db.insert(LocationTable.NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return  true;
    }

    public ArrayList<String> getUniqueDates() {
        ArrayList<String> dates = new ArrayList<>();
        try {
            Cursor cursor = db.query(true, LocationTable.NAME, new String[]{LocationTable.Columns.DATE}, null, null, LocationTable.Columns.DATE, null, null, null);
            while (cursor.moveToNext()) {
                dates.add(cursor.getString(cursor.getColumnIndex(LocationTable.Columns.DATE)));
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dates;
    }

    public ArrayList<Location> getDateLocations(String date) {
        ArrayList<Location> locations = new ArrayList<>();
        Cursor res = db.query(LocationTable.NAME, null, LocationTable.Columns.DATE + "=?", new String[]{date}, null, null, null);
        while (res.moveToNext()) {
            Location location = new Location();
            location.setLatitude(res.getDouble(res.getColumnIndex(LocationTable.Columns.LATITUDE)));
            location.setLongitude(res.getDouble(res.getColumnIndex(LocationTable.Columns.LONGITUDE)));
            location.setDate(res.getString(res.getColumnIndex(LocationTable.Columns.DATE)));
            location.setDuration(res.getLong(res.getColumnIndex(LocationTable.Columns.DURATION)));
            location.setAddress(res.getString(res.getColumnIndex(LocationTable.Columns.ADDRESS)));
            locations.add(location);
        }
        return locations;
    }

    public ArrayList<String> getUniqueLocations() {
        ArrayList<String> dates = new ArrayList<>();
        try {
            Cursor cursor = db.query(true, LocationTable.NAME, new String[]{LocationTable.Columns.ADDRESS}, null, null, LocationTable.Columns.ADDRESS, null, null, null);
            while (cursor.moveToNext()) {
                dates.add(cursor.getString(cursor.getColumnIndex(LocationTable.Columns.ADDRESS)));
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dates;
    }

    public long getTimeSpentAtLocations(String location) {
        Cursor res = db.query(LocationTable.NAME, null, LocationTable.Columns.ADDRESS + "=?", new String[]{location}, null, null, null);
        long j = 0;
        while (res.moveToNext()) {
            j = j + res.getLong(res.getColumnIndex(LocationTable.Columns.DURATION));
        }
        return j;
    }
}