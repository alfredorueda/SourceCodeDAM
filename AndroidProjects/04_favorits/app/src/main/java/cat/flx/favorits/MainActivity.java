package cat.flx.favorits;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, View.OnLongClickListener {


    Button[] btn;
    int[] btnId = { R.id.btn01, R.id.btn02, R.id.btn03, R.id.btn04, R.id.btn05,
            R.id.btn06, R.id.btn07, R.id.btn08, R.id.btn09, R.id.btn10 };

    class BtnInfo {
        int idx;
        String text;
        String url;
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = new Button[btnId.length];
        for (int i = 0; i < btnId.length; i++) {
            btn[i] = (Button) findViewById(btnId[i]);
            btn[i].setOnClickListener(this);
            btn[i].setOnLongClickListener(this);
        }
    }

    @Override public void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String undef = getResources().getString(R.string.undefined);
        for (int i = 0; i < btn.length; i++) {
            String prefText = String.format(Locale.getDefault(), "text%02d", i + 1);
            String text = prefs.getString(prefText, undef);
            if (text.isEmpty()) text = undef;
            String prefUrl = String.format(Locale.getDefault(), "url%02d", i + 1);
            String url = prefs.getString(prefUrl, "");
            BtnInfo info = new BtnInfo();
            info.idx = i + 1;
            info.text = text;
            info.url = url;
            btn[i].setText(text);
            btn[i].setTag(info);
        }
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent i = new Intent(this, SettingsActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override public void onClick(View arg0) {
        BtnInfo info = (BtnInfo) arg0.getTag();
        String url = info.url;
        if (!url.isEmpty()) {
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "http://" + url;
            }
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(i);
        }
    }

    @Override public boolean onLongClick(View arg0) {
        BtnInfo info = (BtnInfo) arg0.getTag();
        Intent i = new Intent(this, EditActivity.class);
        i.putExtra("idx", info.idx);
        startActivity(i);
        return true;
    }
}
