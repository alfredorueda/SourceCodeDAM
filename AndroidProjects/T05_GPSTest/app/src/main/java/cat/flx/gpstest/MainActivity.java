package cat.flx.gpstest;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements GpsStatus.NmeaListener, LocationListener {

    private TextView textView1, textView2;
    private LocationManager locManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    public void onResume() {
        super.onResume();
 //       if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
 //           return;
 //       }
        try {
            locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
            locManager.addNmeaListener(this);
        }
        catch (Exception e) {
            Toast.makeText(this, "Error " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
 //       if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
 //           return;
 //       }
        locManager.removeUpdates(this);
        locManager.removeNmeaListener(this);
    }

    private long lastTimestamp = 0;
    @Override public void onNmeaReceived(long timestamp, String nmea) {
        if (timestamp - lastTimestamp > 500) {
            textView1.setText(Long.toString(timestamp));
            textView2.setText(nmea);
            lastTimestamp = timestamp;
        }
        textView2.setText(textView2.getText() + nmea);
    }

    @Override public void onLocationChanged(Location location) { }
    @Override public void onStatusChanged(String provider,int status,Bundle extras) { }
    @Override public void onProviderEnabled(String provider) { }
    @Override public void onProviderDisabled(String provider) { }
}
