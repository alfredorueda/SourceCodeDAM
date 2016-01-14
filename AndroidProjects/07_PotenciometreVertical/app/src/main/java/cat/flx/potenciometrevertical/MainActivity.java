package cat.flx.potenciometrevertical;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity
        implements PotenciometreVertical.OnValueChangeListener {

    private PotenciometreVertical pv1, pv2, pv3;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pv1 = (PotenciometreVertical) findViewById(R.id.potenciometreVertical1);
        pv2 = (PotenciometreVertical) findViewById(R.id.potenciometreVertical2);
        pv3 = (PotenciometreVertical) findViewById(R.id.potenciometreVertical3);
        pv1.setMagnet(false); pv1.setTickSize(10); pv1.setShowTicks(true);
        pv2.setMagnet(false); pv2.setTickSize(10); pv2.setShowTicks(true);
        pv3.setMagnet(false); pv3.setTickSize(10); pv3.setShowTicks(true);
        pv1.setMax(255); pv1.setValue(128);
        pv2.setMax(255); pv2.setValue(128);
        pv3.setMax(255); pv3.setValue(128);
        pv1.setOnValueChangeListener(this);
        pv2.setOnValueChangeListener(this);
        pv3.setOnValueChangeListener(this);
    }

    @Override public void valueChanged(View view, int oldValue, int newValue) {
        Log.d("flx", pv1.getValue() + ", " + pv2.getValue() + ", " + pv3.getValue());
//      Log.d("flx", view.getId() + " : " + oldValue + "->" + newValue);
    }
}
