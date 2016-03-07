package cat.flx.rodalies;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    private Spinner spinOrig;
    private Spinner spinDest;
    protected Parada[] parades;

    protected class Parada {
        int id;
        String nom;

        public Parada(int id, String nom) {
            this.id = id;
            this.nom = nom;
        }
        public String toString() { return nom; }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinOrig = (Spinner) findViewById(R.id.spin_orig);
        spinDest = (Spinner) findViewById(R.id.spin_dest);
        Button btnReload = (Button) findViewById(R.id.btn_reload);
        btnReload.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                new UpdateListTask().execute();
            }
        });
        Button btnSearch = (Button) findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Carregar el trajecte amb la url:
                // http://android.flx.cat/rodalies/consulta.php?nucleo=50&oid=79404&did=77107&df=20160307
                // On:
                //      nucleo és 50 per a rodalies de Barcelona
                //      oid és el id de l'estació d'origen (79404 = Badalona)
                //      did és el id de l'estació de destí (77106 = Balenyà-Tona-Seva)
                //      df és la data de la consulta en format YYYYMMDD

                // 1) Codificar una tasca específica per a aquesta operació
                // 2) Fer que es carregui el HTML obtingut al WebView de l'activitat
                // 3) Alguna millora?
                //      Per exemple, selecció del nucli de rodalies descarregant
                //          http://android.flx.cat/rodalies/cityList.php
            }
        });
    }

    public class UpdateListTask extends AsyncTask<Void, Void, Boolean> {
        private String error = null;
        private String result = "";
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = ProgressDialog.show(MainActivity.this, "Descarregant estacions...", "");
        }

        @Override
        protected Boolean doInBackground(Void... unused) {
            try {
                URL url = new URL("http://android.flx.cat/rodalies/stopList.php?ciudad=barcelona&nucleo=50&format=json");
                BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                String str = "";
                while ((str = in.readLine()) != null) {
                    result += str;
                }
                in.close();
                return true;
            } catch (Exception e) {
                error = e.getMessage();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean ok) {
            dialog.dismiss();
            if (error != null) {
                Toast.makeText(MainActivity.this, "ERROR: " + error, Toast.LENGTH_LONG).show();
                return;
            }
            try {
                Log.d("flx", result);
                JSONObject reader = new JSONObject(result);
                int total = reader.length();
                parades = new Parada[total];
                for(int i = 0; i<total; i++){
                    int key = Integer.parseInt(reader.names().getString(i));
                    String value = reader.get(reader.names().getString(i)).toString();
                    Log.d("flx", key + " = " + value);
                    Parada parada = new Parada(key, value);
                    parades[i] = parada;
                }
                Arrays.sort(parades, new Comparator<Parada>() {
                    @Override
                    public int compare(Parada lhs, Parada rhs) {
                        return lhs.nom.compareTo(rhs.nom);
                    }
                });
                ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_item, parades);
                spinOrig.setAdapter(adapter);
                spinDest.setAdapter(adapter);
            }
            catch (Exception e) {
                Toast.makeText(MainActivity.this, "ERROR: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

}
