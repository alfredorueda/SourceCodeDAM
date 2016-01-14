package cat.flx.colorets;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

// TODO Afegir salvaguarda de color i checkboxes en giro
// TODO Afegir salvaguarda de color i checkboxes entre execucions

public class MainActivity extends AppCompatActivity implements
        PotenciometreVertical.OnValueChangeListener,
        VisorColor.OnRandomizeListener {

    private PotenciometreVertical pvR, pvG, pvB;
    private VisorColor vc;
    private TextView txtColor;
    private CheckBox chkRandom, chkLock, chkMagnet;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pvR = (PotenciometreVertical) findViewById(R.id.potR);
        pvG = (PotenciometreVertical) findViewById(R.id.potG);
        pvB = (PotenciometreVertical) findViewById(R.id.potB);
        vc = (VisorColor) findViewById(R.id.visorColor);
        txtColor = (TextView) findViewById(R.id.txtColor);
        chkLock = (CheckBox) findViewById(R.id.chkLockGrays);
        chkMagnet = (CheckBox) findViewById(R.id.chkMagnet16);
        chkRandom = (CheckBox) findViewById(R.id.chkRandomColor);
        chkRandom.setChecked(vc.isRandomColorOnClick());
        pvR.setOnValueChangeListener(this);
        pvG.setOnValueChangeListener(this);
        pvB.setOnValueChangeListener(this);
        vc.setOnVisorColorRandomizeListener(this);
        chkMagnet.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                pvR.setMagnet(isChecked);
                pvG.setMagnet(isChecked);
                pvB.setMagnet(isChecked);
            }
        });
        chkRandom.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                vc.setRandomColorOnClick(isChecked);
            }
        });
        setColor(Color.LTGRAY);
    }

    @Override public void valueChanged(View view, int oldValue, int newValue) {
        int color;
        if (chkLock.isChecked())
            color = Color.rgb(newValue, newValue, newValue);
        else
            color = Color.rgb(pvR.getValue(), pvG.getValue(), pvB.getValue());
        setColor(color);
    }

    @Override public void colorRandomized(View view, int color) {
        setColor(color);
    }

    public void setColor(int color) {
        pvR.setValue(Color.red(color));
        pvG.setValue(Color.green(color));
        pvB.setValue(Color.blue(color));
        vc.setColor(color);
        txtColor.setText("#" + String.format("%06X", color & 0xFFFFFF));
    }

}