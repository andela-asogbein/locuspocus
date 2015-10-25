package co.jibola.locus.database;

public class LocationDbSchema {

    public static final class LocationTable{
        public static final String NAME = "locations";

        public static final class Columns{
            public static final String LATITUDE = "latitude";
            public static final String LONGITUDE = "longitude";
            public static final String DATE = "date";
            public static final String DURATION = "duration";
            public static final String ADDRESS = "address";
        }
    }
}