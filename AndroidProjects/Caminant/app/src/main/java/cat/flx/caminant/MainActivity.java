package cat.flx.caminant;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity
        implements CompoundButton.OnCheckedChangeListener, Route.RouteListener {

    private TextView locationTV;
    private RouteView routeView;
    private ToggleButton btnSaveRoute;

    private CaminantService caminantService;
    private boolean bound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationTV = (TextView) findViewById(R.id.locationTV);
        routeView = (RouteView) findViewById(R.id.routeView);
        btnSaveRoute = (ToggleButton) findViewById(R.id.btnSaveRoute);
        btnSaveRoute.setOnCheckedChangeListener(this);
    }

    @Override public void onResume() {
        super.onResume();
        Log.d("flx", "onResume()");
        Intent intent = new Intent(this, CaminantService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override public void onPause() {
        super.onPause();
        Log.d("flx", "onPause()");
        if (bound) {
            unbindService(connection);
            bound = false;
        }
    }

    @Override public void latLonAdded(LatLon l) {
        if (l != null) {
            String txt = "Latitude: " + l.getLatitudeString() + "\n" +
                    "Longitude: " + l.getLongitudeString() + "\n" +
                    "Altitude: " + l.getAltitudeString() + "\n" +
                    "Accuracy: " + l.getAccuracyString();
            locationTV.setText(txt);
        }
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override public void onServiceConnected(ComponentName className, IBinder service) {
            CaminantService.CaminantBinder binder = (CaminantService.CaminantBinder) service;
            caminantService = binder.getService();
            bound = true;
            if (caminantService.isSaving()) btnSaveRoute.setChecked(true);
            Route route = caminantService.getRoute();
            routeView.setRoute(route);
            route.addRouteListener(MainActivity.this);
            Log.d("flx", "Connected!");
        }

        @Override public void onServiceDisconnected(ComponentName arg0) {
            bound = false;
            Log.d("flx", "Disconnected!");
        }
    };

    @Override public void onCheckedChanged(CompoundButton view, boolean checked) {
        if (bound) caminantService.setSaving(checked);
        Intent intent = new Intent(this, CaminantService.class);
        if (checked) startService(intent);
        else stopService(intent);
    }
}

