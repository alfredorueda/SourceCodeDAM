package com.example.xavi.tresenraya;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNuevaPartida = (Button) findViewById(R.id.btnNuevaPartida);
        final TresEnRayaView terv = (TresEnRayaView) findViewById(R.id.tresenraya);

        btnNuevaPartida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                terv.iniciarPartida();
            }
        });
    }
}
