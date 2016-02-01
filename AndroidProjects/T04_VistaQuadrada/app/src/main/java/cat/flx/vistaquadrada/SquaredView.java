package cat.flx.vistaquadrada;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class SquaredView extends View {
    private Paint paint;

    public SquaredView(Context context) { this(context, null, 0); }
    public SquaredView(Context context, AttributeSet attrs) { this(context, attrs, 0); }
    public SquaredView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5.0f);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        String w_txt = "UNSPECIFIED ";
        int w_mode = MeasureSpec.getMode(widthMeasureSpec);
        if (w_mode == MeasureSpec.EXACTLY) w_txt = "EXACTLY ";
        if (w_mode == MeasureSpec.AT_MOST) w_txt = "AT MOST ";
        int h_mode = MeasureSpec.getMode(heightMeasureSpec);
        String h_txt = "UNSPECIFIED ";
        if (h_mode == MeasureSpec.EXACTLY) h_txt = "EXACTLY ";
        if (h_mode == MeasureSpec.AT_MOST) h_txt = "AT MOST ";
        Log.d("flx", getId() + " W: " + w_txt + w + ", H: " + h_txt + h);
        if (w > h) w = h;
        else h = w;
        Log.d("flx", " -> " + getId() + " W: " + w + ", H: " + h);
        setMeasuredDimension(w, h);
    }

    @Override
    public void onDraw(Canvas canvas) {
        int top = 3;
        int left = 3;
        int bottom = getHeight() - 3;
        int right = getWidth() - 3;
        canvas.drawRect(left, top, right, bottom, paint);
        //canvas.drawLine(left, top, right, bottom, paint);
        //canvas.drawLine(left, bottom, right, top, paint);
    }
}
