package cat.flx.t03_tests;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static int buttons[] = {
            R.id.button1,
            R.id.button2,
            R.id.button3,
            R.id.button4
    };
    private static Class classes[] = {
            ArrayAdapter1Activity.class,
            ArrayAdapter2Activity.class,
            ArrayAdapter3Activity.class,
            SimpleCursorAdapter1Activity.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < buttons.length; i++) {
            Button btn = (Button) findViewById(buttons[i]);
            btn.setTag(classes[i]);
            btn.setOnClickListener(this);
        }
    }

    @Override public void onClick(View view) {
        Class cl = (Class) view.getTag();
        Intent intent = new Intent(MainActivity.this, cl);
        startActivity(intent);
    }
}
