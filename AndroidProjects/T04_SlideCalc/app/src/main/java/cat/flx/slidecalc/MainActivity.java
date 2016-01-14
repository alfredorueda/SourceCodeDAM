package cat.flx.slidecalc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements SeekBar.OnSeekBarChangeListener {

    SeekWithTextView swtv1, swtv2;
    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swtv1 = (SeekWithTextView) findViewById(R.id.seekWithTextView1);
        swtv2 = (SeekWithTextView) findViewById(R.id.seekWithTextView2);
        textView1 = (TextView) findViewById(R.id.textView1);

        swtv1.setMax(10); swtv1.setValue(8);
        swtv2.setMax(255); swtv2.setValue(42);

        swtv1.setOnSeekBarChangeListener(this);
        swtv2.setOnSeekBarChangeListener(this);
        onProgressChanged(null, 0, false);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int total = swtv1.getValue() + swtv2.getValue();
        textView1.setText("=" + total);
    }

    @Override public void onStartTrackingTouch(SeekBar seekBar) { }
    @Override public void onStopTrackingTouch(SeekBar seekBar) { }
}