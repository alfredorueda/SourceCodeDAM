package cat.flx.potenciometrevertical;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class PotenciometreVertical extends View
        implements GestureDetector.OnGestureListener {

    public interface OnValueChangeListener {
        void valueChanged(View view, int oldValue, int newValue);
    }

    private int min, max, value, tickSize;
    private boolean showTicks, magnet;
    private Paint paint;
    private Drawable bola;
    private GestureDetector gd;
    private OnValueChangeListener listener;

    public PotenciometreVertical(Context context) {
        this(context, null, 0);
    }

    public PotenciometreVertical(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PotenciometreVertical(Context context, AttributeSet attrs,
                                 int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setMin(0);
        setMax(10);
        setValue(5);
        setTickSize(3);
        setShowTicks(true);
        setMagnet(true);
        paint = new Paint();
        paint.setTextSize(2 * GAPH - 12);
        bola = ContextCompat.getDrawable(context, R.drawable.bola_potenciometre);
        gd = new GestureDetector(context, this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gd.onTouchEvent(event);
    }

    public int getMin() { return min; }
    public int getMax() { return max; }
    public int getValue() { return value; }
    public int getTickSize() { return tickSize; }
    public boolean isShowTicks() { return showTicks; }
    public boolean isMagnet() { return magnet; }

    public void setMin(int min) {
        this.min = min;
        this.invalidate();
    }

    public void setMax(int max) {
        this.max = max;
        this.invalidate();
    }

    public void setValue(int value) {
        if (this.value == value) return;
        int oldValue = value;
        this.value = value;
        this.invalidate();
        if (listener != null) {
            listener.valueChanged(this, oldValue, value);
        }
    }

    public void setTickSize(int tickSize) {
        this.tickSize = tickSize;
        this.invalidate();
    }

    public void setShowTicks(boolean showTicks) {
        this.showTicks = showTicks;
        this.invalidate();
    }

    public void setMagnet(boolean magnet) {
        this.magnet = magnet;
    }

    private static final int GAPW = 50;
    private static final int GAPH = 30;

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        if (w > 2 * GAPW) w = 2 * GAPW;
        if (h < 2 * GAPH) h = 2 * GAPH;
        setMeasuredDimension(w, h);
    }

    @Override
    public void onDraw(Canvas canvas) {
        int w = getWidth();
        int h = getHeight();
        int cx = w / 2;
        paint.setColor(Color.GRAY);
        if (showTicks) {
            for (int i = min; i <= max; i += tickSize) {
                int y = valToY(i);
                canvas.drawLine(cx - 5, y, cx + 5, y, paint);
            }
        }
        paint.setColor(Color.LTGRAY);
        paint.setStrokeWidth(3);
        canvas.drawLine(cx, GAPH, cx, h - GAPH, paint);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(1);
        canvas.drawLine(cx, GAPH, cx, h - GAPH, paint);
        int y = valToY(value);
        bola = ContextCompat.getDrawable(this.getContext(), R.drawable.bola_potenciometre);
        bola.setBounds(cx - 45, y - 28, cx + 45, y + 28);
        bola.draw(canvas);
        String valTxt = String.valueOf(value);
        int valW = (int) paint.measureText(valTxt);
        canvas.drawText(valTxt, cx - valW / 2, y + GAPH - 9, paint);
    }

    public int valToY(int v) {
        int h = getHeight();
        return (int) (h - GAPH - (v - min) * (float) (h - 2 * GAPH) / (max - min));
    }

    public int yToVal(int y) {
        int h = getHeight();
        int v = (int) (min + (float) (h - GAPH - y) * (max - min) / (h - 2 * GAPH) + 0.5);
        if (v < min) v = min;
        if (v > max) v = max;
        return v;
    }

    @Override public boolean onDown(MotionEvent e) {
        int v = yToVal((int) e.getY());
        if (magnet) {
            v = (int) ((float) (v - min) / tickSize + 0.5) * tickSize;
            if (v > max) v = max;
        }
        setValue(v);
        this.invalidate();
        return true;
    }

    @Override public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                      float distanceX, float distanceY) { return onDown(e2); }

    @Override public void onShowPress(MotionEvent e) { }
    @Override public boolean onSingleTapUp(MotionEvent e) { return false; }
    @Override public void onLongPress(MotionEvent e) { }
    @Override public boolean onFling(MotionEvent e1, MotionEvent e2,
                                     float velocityX, float velocityY) { return false; }

    public void setOnValueChangeListener(OnValueChangeListener listener) {
        this.listener = listener;
    }
}
