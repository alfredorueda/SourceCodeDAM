package cat.flx.slidecalc;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class SeekWithTextView extends LinearLayout
        implements SeekBar.OnSeekBarChangeListener {

    private SeekBar seekBar;
    private TextView textView;

    public SeekWithTextView(Context context) { this(context, null); }
    public SeekWithTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.seek_with_textview_layout, this, true);
        seekBar = (SeekBar) findViewById(R.id.seek_with_textView_seekBar1);
        textView = (TextView) findViewById(R.id.seek_with_textView_textView1);
        seekBar.setOnSeekBarChangeListener(this);
        textView.setText("0");
    }

    public int getMax() { return seekBar.getMax(); }
    public void setMax(int max) { seekBar.setMax(max); }

    public int getValue() { return seekBar.getProgress(); }
    public void setValue(int value) {
        if (value < 0) value = 0;
        if (value > seekBar.getMax()) value = getMax();
        seekBar.setProgress(value);
        textView.setText(String.valueOf(value));
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (listener != null) {
            listener.onProgressChanged(seekBar, progress, fromUser);
        }
        if (fromUser) setValue(progress);
    }
    @Override public void onStartTrackingTouch(SeekBar seekBar) { }
    @Override public void onStopTrackingTouch(SeekBar seekBar) { }

    private SeekBar.OnSeekBarChangeListener listener;
    public void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener listener) {
        this.listener = listener;
    }

}