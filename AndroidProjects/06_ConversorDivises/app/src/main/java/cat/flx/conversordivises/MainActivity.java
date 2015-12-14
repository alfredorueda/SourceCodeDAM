package cat.flx.conversordivises;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements TextWatcher, OnItemSelectedListener {

    Currencies currencies;
    Spinner spinner1, spinner2;
    EditText editText1;
    TextView textView1;

    String currencyFrom, currencyTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        editText1 = (EditText) findViewById(R.id.editText1);
        textView1 = (TextView) findViewById(R.id.textView1);
        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
        editText1.addTextChangedListener(this);
        init();
        loadPrefs();
        editText1.setText("");
    }

    public void init() {
        currencies = new Currencies();
        boolean loaded = currencies.loadFromFile(this);
        Log.d("flx", "Loaded: " + loaded);
        if (!loaded) downloadCurrencies();
        else {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            Calendar c = Calendar.getInstance();
            String now = sdf.format(c.getTime());
            c.add(Calendar.DATE, -1);
            String yesterday = sdf.format(c.getTime());
            boolean valid = now.equals(currencies.getTime()) || yesterday.equals(currencies.getTime());
            if (!valid) askForDownload();
            else updateSpinners();
        }
    }

    public void downloadCurrencies() {
        CurrenciesDownloader dd = new CurrenciesDownloader(this);
        dd.setOnCurrenciesLoadedListener(new OnCurrenciesLoadedListener() {
            @Override
            public void onCurrenciesLoaded(boolean ok) {
                Log.d("flx", "Download result: " + ok);
                if (ok) init();
                else askForDownload();
            }
        });
        dd.execute();
    }

    public void askForDownload() {

        if (currencies != null) {
            if (currencies.getTime() != null) {
                String title = getResources().getString(R.string.update_currencies_title);
                String msg = getResources().getString(R.string.last_update_message);
                String btnOk = getResources().getString(R.string.button_download);
                String btnNo = getResources().getString(R.string.button_not_now);
                msg = msg.replaceAll("@", currencies.getTime());
                new AlertDialog.Builder(this).setTitle(title).setMessage(msg)
                        .setPositiveButton(btnOk, new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                MainActivity.this.downloadCurrencies();
                            }
                        })
                        .setNegativeButton(btnNo, new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                MainActivity.this.init();
                            }
                        })
                        .show();
                return;
            }
        }
        String title = getResources().getString(R.string.notify_empty_file_title);
        String msg = getResources().getString(R.string.notify_empty_file_message);
        new AlertDialog.Builder(this).setTitle(title).setMessage(msg).show();
    }

    public void updateSpinners() {
        CurrencyCursor cursor = new CurrencyCursor(currencies);
        SimpleCursorAdapter adapter;
        adapter = new SimpleCursorAdapter(
                this,
                R.layout.spinner_item,
                cursor,
                new String[]{"currency", "name"},
                new int[]{R.id.textView1, R.id.textView2},
                0);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
    }

    public void loadPrefs() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        currencyFrom = prefs.getString("currencyFrom", "EUR");
        currencyTo = prefs.getString("currencyTo", "USD");
        spinner1.setSelection(currencies.getPosition(currencyFrom), true);
        spinner2.setSelection(currencies.getPosition(currencyTo), true);
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor ed = prefs.edit();
        ed.putString("currencyFrom", currencyFrom);
        ed.putString("currencyTo", currencyTo);
        ed.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_update_currencies) {
            downloadCurrencies();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        updateChange();
    }

    public void updateChange() {
        String ed = String.valueOf(editText1.getText()).trim();
        double from = 0;
        try {
            from = Double.parseDouble(ed);
        } catch (NumberFormatException nfe) {
        }
        double to = currencies.convert(from, currencyFrom, currencyTo);
        textView1.setText(String.valueOf(to));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        String cur = currencies.getCurrencies().get(position).currency;
        if (parent.equals(spinner1)) currencyFrom = cur;
        if (parent.equals(spinner2)) currencyTo = cur;
        updateChange();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
