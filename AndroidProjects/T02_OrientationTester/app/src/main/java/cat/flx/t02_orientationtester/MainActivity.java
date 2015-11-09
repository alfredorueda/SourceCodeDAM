package cat.flx.t02_orientationtester;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editor;
    private TextView result;
    private Button btnSalute;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("flx", "onCreate()");
        setContentView(R.layout.activity_main);
        editor = (EditText) findViewById(R.id.editText1);
        result = (TextView) findViewById(R.id.textView2);
        btnSalute = (Button) findViewById(R.id.button1);
        btnSalute.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                String msg = "Hola " + editor.getText().toString();
                result.setText(msg);
            }
        });
        if (savedInstanceState != null) {
            CharSequence msg = savedInstanceState.getCharSequence("msg");
            result.setText(msg);
        }
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        Log.d("flx", "onSaveInstanceState()");
        outState.putCharSequence("msg", result.getText());
    }

    @Override public void onStart() {
        super.onStart();
        Log.d("flx","onStart()");
    }
    @Override public void onRestart() {
        super.onRestart();
        Log.d("flx","onRestart()");
    }
    @Override public void onResume() {
        super.onResume();
        Log.d("flx","onResume()");
    }
    @Override public void onPause() {
        super.onPause();
        Log.d("flx","onPause()");
    }
    @Override public void onStop() {
        super.onStop();
        Log.d("flx","onStop()");
    }
    @Override public void onDestroy() {
        super.onDestroy();
        Log.d("flx","onDestroy()");
    }
}
