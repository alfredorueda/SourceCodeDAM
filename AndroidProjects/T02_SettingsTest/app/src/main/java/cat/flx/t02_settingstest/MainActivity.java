package cat.flx.t02_settingstest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView labelTime, labelDate;

    private boolean showDate;
    private int sel12or24h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        labelTime = (TextView) findViewById(R.id.labelTime);
        labelDate = (TextView) findViewById(R.id.labelDate);
        loadSettings();
        final Handler updateHandler = new Handler();
        updateHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String fmtTime = "HH:mm:ss";
                if (sel12or24h == 12) fmtTime = "KK:mm:ss a";
                String currentTime =
                        new SimpleDateFormat(fmtTime, Locale.US).format(new Date());
                labelTime.setText(currentTime);

                labelDate.setVisibility(showDate ? View.VISIBLE : View.GONE);

                String fmtDate = "dd/MM/yyyy";
                String currentDate =
                        new SimpleDateFormat(fmtDate, Locale.US).format(new Date());
                labelDate.setText(currentDate);
                updateHandler.postDelayed(this, 200);
            }
        }, 100);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public static final int ACTION_SETTINGS_DONE = 1;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivityForResult(intent, ACTION_SETTINGS_DONE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTION_SETTINGS_DONE) {
            loadSettings();
        }
    }

    public void loadSettings() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        showDate = prefs.getBoolean("show_date", true);
        sel12or24h = Integer.parseInt(prefs.getString("sel12or24h", "24"));
    }
}
