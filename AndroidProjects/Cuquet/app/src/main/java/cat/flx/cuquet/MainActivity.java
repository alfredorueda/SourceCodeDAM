package cat.flx.cuquet;

import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
implements CuquetView.CuquetViewListener, SensorEventListener {

    private CuquetView cuquetView;
    private TextView tvScore, maxPuntuacioLabel;
    private int maxPuntuacio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cuquetView = (CuquetView) findViewById(R.id.cuquetView);
        Button btnNewGame = (Button) findViewById(R.id.btnNewGame);
        tvScore = (TextView) findViewById(R.id.tvScore);
        maxPuntuacioLabel = (TextView) findViewById(R.id.maxPuntuacioLabel);
        maxPuntuacio = 0;
        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvScore.setText("0");

                try {
                    SharedPreferences settings = getSharedPreferences("PreferenciesXavi2", 0);
                    maxPuntuacio = settings.getInt("maxPuntuacio", 0);
                    maxPuntuacioLabel.setText(""+maxPuntuacio);
                    System.out.println(maxPuntuacio);
                } catch (Exception e){
                    System.out.println(e.toString());
                }

                cuquetView.newGame();


            }
        });

        cuquetView.setCuquetViewListener(this);
        initAccelerometer();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        switch(event.getKeyCode()) {
            case KeyEvent.KEYCODE_A: cuquetView.update(0, +10); break;
            case KeyEvent.KEYCODE_Q: cuquetView.update(0, -10); break;
            case KeyEvent.KEYCODE_O: cuquetView.update(-10, 0); break;
            case KeyEvent.KEYCODE_P: cuquetView.update(+10, 0); break;
        }
        return super.dispatchKeyEvent(event);
    }

    public void initAccelerometer() {
        sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //sensor = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (sensor == null) {
            Toast.makeText(this, "No tenim acceleròmetre!!!", Toast.LENGTH_LONG).show();
            this.finish();
            return;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        sm.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
    }
    @Override
    public void onPause() {
        super.onPause();
        sm.unregisterListener(this, sensor);
    }

    private SensorManager sm;
    private Sensor sensor;

    @Override
    public void onSensorChanged(SensorEvent event) {
        //double accelX = Math.toDegrees(event.values[0]);
        float accelX = event.values[0];
        //double accelY = Math.toDegrees(event.values[1]);
        float accelY = event.values[1];
        //System.out.println("AccelX:" + accelX + "AccelY:" + accelY);
        cuquetView.update(-accelX, accelY);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) { }

    @Override
    public void scoreUpdated(View view, int score) {
        tvScore.setText(Integer.toString(score));
    }

    @Override
    public void gameLost(View view) {
        if (Integer.parseInt(tvScore.getText().toString()) > maxPuntuacio) {
            Toast.makeText(MainActivity.this, "Has millorat la máxima puntuació!", Toast.LENGTH_SHORT).show();

            SharedPreferences settings = getSharedPreferences("PreferenciesXavi2", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("maxPuntuacio", Integer.parseInt(tvScore.getText().toString()));
            editor.commit();
        }
        Toast.makeText(this, "Has perdut!!!", Toast.LENGTH_LONG).show();
    }
}
