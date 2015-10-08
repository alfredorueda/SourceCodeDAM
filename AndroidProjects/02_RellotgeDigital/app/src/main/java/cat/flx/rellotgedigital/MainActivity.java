package cat.flx.rellotgedigital;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AnalogClock;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

	private TextView labelDate, labelTime;
	private String timeFormat = "HH:mm:ss";
	private String dateFormat = "dd/MM/yyyy";
	private boolean sel24h = true;
	private MenuItem sel24hMenuItem;
    private boolean selDay = false;
    private MenuItem selDayMenuItem;
	
	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		labelDate = (TextView) findViewById(R.id.labelDate);
		labelTime = (TextView) findViewById(R.id.labelTime);
		AnalogClock analogClock = (AnalogClock) findViewById(R.id.analogClock1);
		analogClock.setOnClickListener(new OnClickListener() {
			@Override public void onClick(View arg0) {
				String format = dateFormat + " " + timeFormat;
                if (selDay){
                    format = "EEEE, " + format;
                }
				String time = new SimpleDateFormat(format, Locale.US).format(new Date());
				time = getResources().getString(R.string.the_time_is) + " " + time;
				Toast.makeText(MainActivity.this, time, Toast.LENGTH_SHORT).show();
				Log.d("flx", time);
			}
		});

		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				String time = new SimpleDateFormat(timeFormat, Locale.US).format(new Date());
				labelTime.setText(time);
				String date = new SimpleDateFormat(dateFormat, Locale.US).format(new Date());
				labelDate.setText(date);

				handler.postDelayed(this, 200);
			}
		}, 100);
	}

	@Override public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		sel24hMenuItem = menu.findItem(R.id.action_sel24h);
        selDayMenuItem = menu.findItem(R.id.action_showDay);
		return true;
	}

	@Override public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_sel24h:
				change12or24(!sel24h);
				return true;
            case R.id.action_showDay:
                changeShowDay(!selDay);
                return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void change12or24(boolean sel24h) {
		if (sel24h) {
			sel24hMenuItem.setTitle(R.string.action_sel12h);
			timeFormat="HH:mm:ss";
		}
		else {
			sel24hMenuItem.setTitle(R.string.action_sel24h);
			timeFormat="KK:mm:ss a";
		}
		this.sel24h = sel24h;
	}

    public void changeShowDay(boolean selDay) {
        if (selDay) {
            selDayMenuItem.setTitle(R.string.action_hideDay);
            timeFormat="HH:mm:ss";
        }
        else {
            selDayMenuItem.setTitle(R.string.action_showDay);
            timeFormat="KK:mm:ss a";
        }
        this.selDay = selDay;
    }

}
