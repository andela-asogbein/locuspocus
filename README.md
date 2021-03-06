# Locus
[*Locus*](https://play.google.com/store/apps/details?id=co.jibola.locus) is an android app that lets users keep track of locations where they spend their time.

Any location that a user stays in for a predetermined amount of time is automatically saved and the user can subsequently view locations where she has spent the most time based on the date the locations were saved.
A user can also view the amount of time spent in all saved locations.

Users can also view locations with the aid of Google Maps embedded inside the app.

This application makes use of the[ Google Play services location API](https://developers.google.com/android/reference/com/google/android/gms/location/package-summary) to get the coordinates and address of a user. It also users Google Maps to show a user's saved locations on a map.

Locations of interest are saved in [SQLite](http://developer.android.com/reference/android/database/sqlite/package-summary.html), a lightweight database system which is built into android devices.

### How to Use

1. Select how long you want to stay in a location before it is saved. 
2. Click the “Start Tracking” button to start tracking your movements. 
3. Click the “Stop Tracking” button anytime to stop tracking your movements. 
4. Click the calendar icon on the toolbar from the home screen to view dates when locations were stored. 
5. Click the location icon on the toolbar from the home screen to view how much time you have spent in unique locations. 
  
Click [here](https://play.google.com/store/apps/details?id=co.jibola.locus) here to download app from Google Play.

### Running locally
1. Clone the repo or download as a ZIP file and unzip it
2. Import the project folder into [Android Studio](https://developer.android.com/studio/index.html)
3. Android Studio will prompt you to download any required dependency or download them automatically 
4. Run the app on your mobile device or an emulator
5. Enjoy!
