package cat.flx.caminant;

import java.util.Locale;

import android.location.Location;

public class LatLon {
    public double latitude, longitude, altitude;
    public float accuracy;

    public LatLon(double latitude, double longitude) { this(latitude, longitude, 0, 0); }

    public LatLon(double latitude, double longitude, double altitude, float accuracy) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.accuracy = accuracy;
    }

    public LatLon(Location location) {
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
        this.altitude = location.getAltitude();
        this.accuracy = location.getAccuracy();
    }

    public Location getAsLocation() {
        Location l = new Location("flx");
        l.setLatitude(latitude);
        l.setLongitude(longitude);
        l.setAltitude(altitude);
        l.setAccuracy(accuracy);
        return l;
    }

    public String getLatitudeString() {
        String lat = Location.convert(this.latitude, Location.FORMAT_SECONDS);
        lat = lat.replaceFirst(":", "\u00B0").replaceFirst(":", "'") + "\"";
        return lat;
    }

    public String getLongitudeString() {
        String lon = Location.convert(this.longitude, Location.FORMAT_SECONDS);
        lon = lon.replaceFirst(":", "\u00B0").replaceFirst(":", "'") + "\"";
        return lon;
    }

    public String getAltitudeString() {
        return String.format(Locale.getDefault(), "%.2f", this.altitude) + "m";
    }

    public String getAccuracyString() {
        return String.format(Locale.getDefault(), "%.1f", this.accuracy) + "m";
    }
}
