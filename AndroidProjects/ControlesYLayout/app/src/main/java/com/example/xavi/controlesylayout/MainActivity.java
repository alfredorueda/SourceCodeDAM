package com.example.xavi.controlesylayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mensaje;
    private Button tecla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mensaje = (TextView) findViewById(R.id.mensaje);
        tecla   = (Button) findViewById(R.id.tecla);
        tecla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensaje.setText("Hola");
            }
        });
    }
}
