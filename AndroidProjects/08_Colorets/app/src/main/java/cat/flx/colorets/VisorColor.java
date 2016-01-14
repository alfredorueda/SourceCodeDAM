package cat.flx.colorets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class VisorColor extends View implements View.OnClickListener {

    private int color;
    private Paint paint;
    private boolean randomColorOnClick;
    private OnRandomizeListener listener;

    public interface OnRandomizeListener {
        void colorRandomized(View view, int color);
    }

    public VisorColor(Context context) {
        this(context, null, 0);
    }
    public VisorColor(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public VisorColor(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setStrokeWidth(3.5f);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        setColor(Color.TRANSPARENT);
        setRandomColorOnClick(false);
        if (attrs != null) readAttrs(context, attrs);
        this.setOnClickListener(this);
    }

    public int getColor() { return color; }
    public void setColor(int color) {
        this.color = color;
        this.invalidate();
    }

    public boolean isRandomColorOnClick() { return randomColorOnClick; }
    public void setRandomColorOnClick(boolean y) { this.randomColorOnClick = y; }

    public void readAttrs(Context context, AttributeSet attrs) {
        TypedArray a =
                context.obtainStyledAttributes(attrs, R.styleable.VisorColor);
        setColor(a.getColor(R.styleable.VisorColor_selectedColor, Color.TRANSPARENT));
        setRandomColorOnClick(a.getBoolean(R.styleable.VisorColor_randomColorOnClick, false));
        a.recycle();
    }

    @Override public void onDraw(Canvas canvas) {
        canvas.drawColor(color);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
    }

    @Override public void onClick(View v) {
        if (randomColorOnClick) {
            int r = (int)(Math.random() * 256);
            int g = (int)(Math.random() * 256);
            int b = (int)(Math.random() * 256);
            int color = Color.rgb(r, g, b);
            setColor(color);
            if (listener != null) listener.colorRandomized(this, color);
        }
    }

    public void setOnVisorColorRandomizeListener(OnRandomizeListener listener) {
        this.listener = listener;
    }
}

