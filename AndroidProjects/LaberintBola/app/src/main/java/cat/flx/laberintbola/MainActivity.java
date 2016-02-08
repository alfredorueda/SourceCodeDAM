package cat.flx.laberintbola;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements SensorEventListener, GameCanvas.GameEndListener {

    private GameCanvas gameCanvas;
    private SensorManager sensorManager;
    private Sensor sensor;

    private int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameCanvas = (GameCanvas) findViewById(R.id.gameCanvas1);
        loadGame(0);
        gameCanvas.setOnGameEndListener(this);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    public void loadGame(int n) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    getAssets().open("games.txt")));
            for (int i = 0; i < n; i++) in.readLine();
            String game = in.readLine();
            if (game == null) return;
            String[] parts = game.split(":");
            if (parts.length != 7) return;
            int mw = Integer.parseInt(parts[0]);
            int mh = Integer.parseInt(parts[1]);
            int sx = Integer.parseInt(parts[3]);
            int sy = Integer.parseInt(parts[4]);
            int ex = Integer.parseInt(parts[5]);
            int ey = Integer.parseInt(parts[6]);
            in.close();
            this.level = n;
            Toast.makeText(this, "Level " + level, Toast.LENGTH_SHORT).show();
            GameMap map = new GameMap (mw, mh, parts[2], sx, sy, ex, ey);
            gameCanvas.setGameMap(map);
        }
        catch (IOException ioe) { }
    }

    @Override public void onResume() {
        super.onResume();
        gameCanvas.setPlaying(true);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override public void onPause() {
        super.onPause();
        gameCanvas.setPlaying(false);
        sensorManager.unregisterListener(this);
    }

    @Override public void onAccuracyChanged(Sensor sensor, int accuracy) { }

    private long lastMillis;
    @Override public void onSensorChanged(SensorEvent event) {
        float ax = -event.values[0];
        float ay = event.values[1];
        long millis = System.currentTimeMillis();
        float delta = (millis - lastMillis)/1000f;
        if (lastMillis != 0) gameCanvas.updateBall(ax, ay, delta);
        lastMillis = millis;
    }

    @Override
    public void gameFinished() {
        level++;
        loadGame(level);
    }
}
