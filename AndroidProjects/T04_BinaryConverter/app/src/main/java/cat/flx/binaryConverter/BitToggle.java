package cat.flx.binaryConverter;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class BitToggle extends TextView implements OnClickListener {

    public BitToggle(Context context) {
        this(context, null, 0);
    }
    public BitToggle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public BitToggle(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setOnClickListener(this);
        setValue(false);
    }

    public interface OnBitChangeListener {
        void bitChanged(View view, boolean value);
    }

    protected OnBitChangeListener listener;

    public void setOnBitChangeListener(OnBitChangeListener listener) {
        this.listener = listener;
    }

    public boolean getValue() {
        return (!this.getText().equals("0"));
    }

    public void setValue(boolean value) {
        if (value) { setTextColor(Color.RED); setText("1"); }
        else { setTextColor(Color.BLUE); setText("0"); }
        if (listener != null) listener.bitChanged(this, value);
    }

    @Override
    public void onClick(View v) {
        setValue(!getValue());
    }

}
