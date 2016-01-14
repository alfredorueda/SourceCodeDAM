package cat.flx.gesturetest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;

public class GestureView extends View implements OnGestureListener {

    Paint paintFons, paintLinia;
    GestureDetector gd;

    public GestureView(Context context) { this(context, null, 0); }
    public GestureView(Context context, AttributeSet attrs) { this(context, attrs, 0); }
    public GestureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        gd = new GestureDetector(context, this);
        gd.setIsLongpressEnabled(true);
        paintFons = new Paint();
        paintFons.setColor(Color.GREEN);
        paintFons.setStyle(Style.FILL);
        paintLinia = new Paint();
        paintLinia.setColor(Color.BLACK);
        paintLinia.setStrokeWidth(5);
        paintLinia.setStyle(Style.STROKE);
    }

    @Override public void onDraw(Canvas canvas) {
        int w = getWidth();
        int h = getHeight();
        canvas.drawRect(0, 0, w, h, paintFons);
        canvas.drawRect(0, 0, w, h, paintLinia);
        canvas.drawLine(0, 0, w, h, paintLinia);
        canvas.drawLine(0, h, w, 0, paintLinia);
    }

    @Override public boolean onTouchEvent(MotionEvent event) {
        return gd.onTouchEvent(event);
    }

    @Override public boolean onDown(MotionEvent e) {
        Log.d("flx", "onDown() a (" + e.getX() + "," + e.getY() + ")");
        return true;
    }

    @Override public void onShowPress(MotionEvent e) {
        Log.d("flx", "onShowPress() a (" + e.getX() + "," + e.getY() + ")");
    }

    @Override public boolean onSingleTapUp(MotionEvent e) {
        Log.d("flx", "onSingleTapUp() a (" + e.getX() + "," + e.getY() + ")");
        return true;
    }

    @Override public void onLongPress(MotionEvent e) {
        Log.d("flx", "onLongPress() a (" + e.getX() + "," + e.getY() + ")");
    }

    @Override public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                      float distanceX, float distanceY) {
        Log.d("flx", "onScroll() de (" + e1.getX() + "," + e1.getY() +
                ") a (" + e2.getX() + "," + e2.getY() + ") amb distàncies (" +
                distanceX + "," + distanceY + ")");
        return true;
    }

    @Override public boolean onFling(MotionEvent e1, MotionEvent e2,
                                     float velocityX, float velocityY) {
        Log.d("flx", "onFling() de (" + e1.getX() + "," + e1.getY() +
                ") a (" + e2.getX() + "," + e2.getY() + ") amb velocitats (" +
                velocityX + "," + velocityY + ")");
        return true;
    }
}

