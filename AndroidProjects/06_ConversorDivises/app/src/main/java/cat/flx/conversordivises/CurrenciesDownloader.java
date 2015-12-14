package cat.flx.conversordivises;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurrenciesDownloader extends AsyncTask<Void, String, Boolean> {

    private Context context;
    public CurrenciesDownloader(Context context) {
        this.context = context;
    }

	private OnCurrenciesLoadedListener listener = null;
	public void setOnCurrenciesLoadedListener(OnCurrenciesLoadedListener listener) {
		this.listener = listener;
	}

    private ProgressDialog pDialog;

    @Override protected void onPreExecute() {
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Downloading file. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setMax(100);
        pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pDialog.setCancelable(true);
        pDialog.setTitle(R.string.please_wait);
        String msg = context.getResources().getString(R.string.updating_currencies);
        pDialog.setMessage(msg);
        pDialog.setCancelable(true);
        pDialog.show();
    }

	@Override protected Boolean doInBackground(Void... params) {
		int count;
		try {
			URL url = new URL("http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");
			URLConnection connection = url.openConnection();
			connection.connect();
			int lengthOfFile = connection.getContentLength();
            pDialog.setMax(lengthOfFile);
			InputStream input = new BufferedInputStream(url.openStream(), 8192);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte data[] = new byte[1024];
			long total = 0;
			while ((count = input.read(data)) != -1) {
				total += count;
 				publishProgress("" + (int) ((total * 100) / lengthOfFile));
				output.write(data, 0, count);
			}
            input.close();
			output.flush();
            String xml = new String(output.toByteArray());
            File dir = context.getExternalFilesDir(null);
            if (dir == null) return false;
            File f = new File(dir, "currencies.csv");
            f.delete();
            PrintWriter wr = new PrintWriter(f);
            Pattern pattern = Pattern.compile(".*<Cube time='(.*)'.*");
            Matcher matcher = pattern.matcher(xml);
            if (!matcher.find()) return false;
            String time = matcher.group(1);
            wr.println(time);
            wr.println("EUR:1.0000");
            pattern = Pattern.compile(".*<Cube currency='(.*)' rate='(.*)'.*");
            matcher = pattern.matcher(xml);
            while (matcher.find()) {
                String currency = matcher.group(1);
                String rate = matcher.group(2);
                wr.println(currency + ":" + rate);
            }
            wr.flush();
            wr.close();
		} catch (Exception e) {
			Log.e("Error: ", e.getMessage());
            return false;
		}

		return true;
	}

    protected void onProgressUpdate(String... progress) {
        pDialog.setProgress(Integer.parseInt(progress[0]));
    }

	@Override public void onPostExecute(Boolean result) {
        pDialog.dismiss();
		if (listener != null) listener.onCurrenciesLoaded(result);
	}
}