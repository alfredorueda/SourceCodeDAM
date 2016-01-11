package cat.flx.binaryConverter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements BitToggle.OnBitChangeListener {

    BitToggle bit2, bit1, bit0;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bit2 = (BitToggle) findViewById(R.id.bit2);
        bit1 = (BitToggle) findViewById(R.id.bit1);
        bit0 = (BitToggle) findViewById(R.id.bit0);
        result = (TextView) findViewById(R.id.result);

        bit2.setOnBitChangeListener(this);
        bit1.setOnBitChangeListener(this);
        bit0.setOnBitChangeListener(this);
        bitChanged(null, false);
    }

    @Override public void bitChanged(View view, boolean value) {
        int v = bit2.getValue() ? 4 : 0;
        v += bit1.getValue() ? 2 : 0;
        v += bit0.getValue() ? 1 : 0;
        result.setText("=" + v);
    }
}
