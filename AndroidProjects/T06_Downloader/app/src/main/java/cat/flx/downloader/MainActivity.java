package cat.flx.downloader;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private ImageButton imageButton;
    private TextView textView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        editText = (EditText)findViewById(R.id.editText);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    imageButton.callOnClick();
                    return true;
                }
                return false;
            }
        });
        imageButton = (ImageButton)findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = editText.getText().toString();
                new Downloader().execute(url);
            }
        });

        editText.setText("http://landing.stucom.com/imgs/logos/STUCOM-mini.png");
    }

    protected class Downloader extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            progressBar.setProgress(0);
            progressBar.setMax(100);
            progressBar.setIndeterminate(true);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            InputStream input = null;
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            HttpURLConnection connection = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                int status = connection.getResponseCode();
                Log.d("flx", "Response " + status);
                if (status != HttpURLConnection.HTTP_OK) {
                    return "ERROR: " + status + " " + connection.getResponseMessage();
                }
                int fileLength = connection.getContentLength();
                Log.d("flx", "Length: " + fileLength);
                input = connection.getInputStream();
                byte data[] = new byte[1024];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    if (fileLength > 0) {
                        publishProgress((int) (total * 100 / fileLength));
                    }
                    output.write(data, 0, count);
                    try { Thread.sleep(100); } catch (InterruptedException e) { }
                }
                String document = output.toString("UTF-8");
                return document;
            }
            catch (Exception e) {
                return "ERROR: " + e.getMessage();
            }
            finally {
                try { output.close(); } catch (Exception e) { }
                try { input.close(); } catch (Exception e) { }
                try { connection.disconnect(); } catch (Exception e) { }
            }
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            Log.d("flx", "Progress... " + progress[0]);
            progressBar.setIndeterminate(false);
            progressBar.setProgress(progress[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            if (result == null) result = "Errors!!!!";
            textView.setText(result);
            progressBar.setVisibility(View.GONE);
        }
    }
}
