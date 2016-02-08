package cat.flx.accelerometer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

public class AccelView extends View {

    private Paint paint, paintDot;
    private float x, y;
    
    public AccelView(Context context) { this(context, null, 0); }
    public AccelView(Context context, AttributeSet attrs) { this(context, attrs, 0); }
    public AccelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        x = y = 0;
        paint = new Paint();
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth(3.0f);
        paintDot = new Paint();
        paintDot.setStyle(Style.FILL_AND_STROKE);
        paintDot.setColor(Color.RED);
    }
    
    public void setXY(float x, float y) {
        this.x = x;
        this.y = y;
        this.invalidate();
    }
    
    @Override public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        if (w > h) w = h; else h = w;
        setMeasuredDimension(w, h);
    }
    
    @Override public void onDraw(Canvas canvas) {
        int w = getWidth();
        int c = w / 2;
        int r = c - 10;
        canvas.drawColor(Color.TRANSPARENT);
        canvas.drawCircle(c, c, r, paint);
        canvas.drawLine(10, c, w - 10, c, paint);
        canvas.drawLine(c, 10, c, w - 10, paint);
        int cx = (int)(c - w / 20.0f * x);
        int cy = (int)(c + w / 20.0f * y);
        canvas.drawCircle(cx, cy, 15, paintDot);
    }    
}
