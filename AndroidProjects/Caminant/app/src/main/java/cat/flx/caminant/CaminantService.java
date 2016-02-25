package cat.flx.caminant;

import android.Manifest;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CaminantService extends Service
        implements GpsStatus.NmeaListener, LocationListener {

    private final IBinder binder = new CaminantBinder();

    public class CaminantBinder extends Binder {
        CaminantService getService() {
            return CaminantService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }


    private LocationManager locManager;
    private File file;
    private PrintWriter out;
    private boolean saving = false;
    private Route route;

    @Override
    public void onCreate() {
        Log.d("flx", "onCreate()");
        super.onCreate();
        clearRoute();
        locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
            locManager.addNmeaListener(this);
        }
    }

    public Route getRoute() { return route; }
    public void clearRoute() { route = new Route(); }

    @Override public void onDestroy() {
        Log.d("flx", "onDestroy()");
        super.onDestroy();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locManager.removeUpdates(this);
            locManager.removeNmeaListener(this);
        }
        stopSavingRoute();
        saving = false;
        stopForeground(true);
    }

    @Override public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(1, muntarNotificacion());
//        simulateLocations();
        return START_STICKY;
    }

    public Notification muntarNotificacion() {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.ic_media_play)
                .setContentTitle(getResources().getString(R.string.notification_title))
                .setContentText(getResources().getString(R.string.notification_text))
                .setContentIntent(pi);
        return builder.build();
    }

    public void simulateLocations() {
        route.add(new LatLon(41.38791700, 2.16991870, 12, 0)); // Barcelona
        route.add(new LatLon(41.53811240, 2.44474060, 28, 0)); // Mataro
        route.add(new LatLon(41.93043730, 2.25443350, 484, 0)); // Vic
        route.add(new LatLon(42.19945890, 2.19076180, 691, 0)); // Ripoll
    }

    public void setSaving(boolean saving) {
        this.saving = saving;
        if (saving) startSavingRoute();
        else stopSavingRoute();
    }
    public boolean isSaving() { return saving; }

    public void startSavingRoute() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        String filename = sdf.format(new Date()) + ".nmea";
        file = new File(getExternalFilesDir(null), filename);
        if (file == null) return;
        if (file != null && file.exists()) {
            file.delete(); // Remove to append to the file
            out = null;
        }
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
            Log.d("flx", "Save: ON. File: " + filename);
        }
        catch (IOException e) {
            out = null;
            Log.d("flx", "ERROR: " + e.getMessage());
        }
    }

    public void stopSavingRoute() {
        Log.d("flx", "Save: OFF");
        if (out != null) {
            out.flush();
            out.close();
        }
    }

    private boolean gpsFix = false;
    @Override public void onNmeaReceived(long timestamp, String nmea) {
        if (nmea.startsWith("$GPGGA")) {
            String[] parts = nmea.split(",");
            gpsFix = !parts[6].equals("0");
        }
        if ((out != null) && gpsFix) {
            out.print(nmea);
            out.flush();
        }
    }

    private LatLon lastLocation;
    public LatLon getLastLocation() { return lastLocation; }

    @Override public void onLocationChanged(Location location) {
        LatLon l = new LatLon(location);
        route.add(l);
        lastLocation = l;
    }

    @Override public void onStatusChanged(String provider, int status, Bundle extras) { }
    @Override public void onProviderEnabled(String provider) { }
    @Override public void onProviderDisabled(String provider) { }

}