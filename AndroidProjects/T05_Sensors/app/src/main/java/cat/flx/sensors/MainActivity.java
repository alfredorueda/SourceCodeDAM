package cat.flx.sensors;

import android.database.MatrixCursor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener, AdapterView.OnItemClickListener {


    private TextView textView1, textView2, textView3, textView4, textView5;
    private ListView listView1;
    private SensorManager sensorManager;
    private Sensor sensor;
    private List<Sensor> sensors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView1 = (ListView) findViewById(R.id.listView1);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView5 = (TextView) findViewById(R.id.textView5);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        MatrixCursor cursor =
                new MatrixCursor(new String[]{"_ID", "NAME", "DESC"});
        sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for (int i = 0; i < sensors.size(); i++) {
            Sensor sensor = sensors.get(i);
            cursor.addRow(new String[]{String.valueOf(i), sensor.getName(),
                    sensor.getVendor()});
        }
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this, android.R.layout.two_line_list_item, cursor,
                new String[]{"NAME", "DESC"},
                new int[]{android.R.id.text1, android.R.id.text2}, 0);
        listView1.setAdapter(adapter);
        listView1.setOnItemClickListener(this);
        sensor = null;
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


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        textView3.setText("X " + event.values[0]);
        textView4.setText("Y " + event.values[1]);
        textView5.setText("Z " + event.values[2]);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Sensor sensor = sensors.get(position);
        textView1.setText(sensor.getName());
        textView2.setText(sensor.getVendor());
        sensorManager.unregisterListener(this);
        this.sensor = sensor;
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
}
