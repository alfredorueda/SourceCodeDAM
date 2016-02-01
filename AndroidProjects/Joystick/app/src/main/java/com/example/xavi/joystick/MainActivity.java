package com.example.xavi.joystick;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Joystick.onJoystickListener{

    private TextView text1, text2;
    private Joystick joystick;

    public void joystickMove(View view, double r, double a) {
        text1.setText("R: " + (float)((int)(r*100)/100));
        text2.setText("A: " + (float)((int)(Math.toDegrees(a)*100)/100));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        text1.setText("R:");
        text2.setText("A:");

        joystick = (Joystick) findViewById(R.id.view);
        joystick.setOnJoystickListener(this);

    }
}
