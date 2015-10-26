package com.example.xavi.controlesylayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //private TextView mensaje;
    //private Button tecla;
    //private int contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("xmollv", "onCreate()");
        setContentView(R.layout.prod_cat);

        /*mensaje = (TextView) findViewById(R.id.mensaje);
        tecla   = (Button) findViewById(R.id.tecla);

        tecla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensaje.setText("Hola" + contador);
                contador++;
            }
        }); */
    }

    @Override protected void onStart(){
        super.onStart();
        Log.d("xmollv", "onStart()");
    }

    @Override protected void onResume(){
        super.onResume();
        Log.d("xmollv", "onResume()");
        //mensaje.setText("Hola" + contador);
    }

    @Override protected void onPause(){
        super.onPause();
        Log.d("xmollv", "onPause()");
    }

    @Override protected void onStop(){
        super.onStop();
        Log.d("xmollv", "onStop()");
    }

    @Override protected void onDestroy(){
        super.onDestroy();
        Log.d("xmollv", "onDestroy()");
    }
}
