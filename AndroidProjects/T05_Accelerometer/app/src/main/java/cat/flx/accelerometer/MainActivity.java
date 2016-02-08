package cat.flx.accelerometer;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensor;
    private TextView textView1, textView2, textView3, textView4;
    private AccelView accelView;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        accelView = (AccelView) findViewById(R.id.accelView1);
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    protected void onResume() {
        super.onResume();
        if (sensor != null)
            sensorManager.registerListener(
                    this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override public void onAccuracyChanged(Sensor sensor, int accuracy) {
        textView4.setText("Accuracy: " + accuracy);
    }

    @Override public void onSensorChanged(SensorEvent event) {
        textView1.setText("Value 0: " + event.values[0]);
        textView2.setText("Value 1: " + event.values[1]);
        textView3.setText("Value 2: " + event.values[2]);
        accelView.setXY(event.values[0], event.values[1]);
    }
}
