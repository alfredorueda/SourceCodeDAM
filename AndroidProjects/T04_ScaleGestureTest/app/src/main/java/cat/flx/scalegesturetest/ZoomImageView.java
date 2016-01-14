package cat.flx.scalegesturetest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import java.util.Locale;

public class ZoomImageView extends View implements
        ScaleGestureDetector.OnScaleGestureListener,
        GestureDetector.OnGestureListener {

    private Bitmap bmp;
    private ScaleGestureDetector sgd;
    private GestureDetector gd;
    private Paint paintText;

    public ZoomImageView(Context context) { this(context, null, 0); }
    public ZoomImageView(Context context, AttributeSet attrs) { this(context,attrs,0); }
    public ZoomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        sgd = new ScaleGestureDetector(context, this);
        gd = new GestureDetector(context, this);
        paintText = new Paint();
        paintText.setTextSize(24);
        paintText.setColor(Color.RED);
    }

    public void setImageURI(Uri imageUri) {
        if (bmp != null) bmp.recycle();
        try {
            bmp = MediaStore.Images.Media.getBitmap(
                    getContext().getContentResolver(), imageUri);
            fitToScreen();
            this.postDelayed(new Runnable() {
                @Override public void run() { fitToScreen(); }
            }, 250);
        }
        catch (Exception e) { bmp = null; }
    }

    private boolean debugInfo = false;
    public void setDebugInfo(boolean y) { this.debugInfo = y; }
    public boolean getDebugInfo() { return this.debugInfo; }

    @Override public boolean onTouchEvent(MotionEvent event) {
        boolean scaled = sgd.onTouchEvent(event);
        if (!sgd.isInProgress()) return gd.onTouchEvent(event);
        return scaled;
    }

    private float scale = -1;
    private int left, top;
    private float offsetX, offsetY;

    public void fitToScreen() {
        offsetX = 0;
        offsetY = 0;
        scale = 1;
        if (bmp == null) return;
        int w = getWidth();
        int h = getHeight();
        int bw = bmp.getWidth();
        int bh = bmp.getHeight();
        float fw = (float)w / bw;
        float fh = (float)h / bh;
        scale = (fw < fh) ? fw : fh;
        left = (int) ((w - scale * bw) / 2);
        top = (int) ((h - scale * bh) / 2);
        invalidate();
    }

    @Override public void onDraw(Canvas canvas) {
        if (scale == -1) fitToScreen();
        canvas.drawColor(Color.BLACK);
        if (bmp == null) return;
        canvas.save();
        canvas.translate(left + offsetX, top + offsetY);
        canvas.scale(scale, scale);
        canvas.drawBitmap(bmp, 0, 0, null);
        canvas.restore();
        if (debugInfo) {
            String msg = String.format(Locale.US, "OX:%.2f OY:%.2f Sc:%.3f",
                    offsetX, offsetY, scale);
            canvas.drawText(msg, 0, getHeight(), paintText);
        }
    }

    @Override public boolean onScale(ScaleGestureDetector detector) {
        onScaleEnd(detector);
        return true;
    }
    @Override public boolean onScaleBegin(ScaleGestureDetector detector) {
        return true;
    }
    @Override public void onScaleEnd(ScaleGestureDetector detector) {
        float fx = detector.getFocusX() - left;
        float fy = detector.getFocusY() - top;
        float sf = detector.getScaleFactor();
        offsetX = fx - (fx - offsetX) * sf;
        offsetY = fy - (fy - offsetY) * sf;
        scale *= sf;
        this.invalidate();
    }

    @Override public boolean onDown(MotionEvent e) { return true; }
    @Override public void onShowPress(MotionEvent e) { }
    @Override public boolean onSingleTapUp(MotionEvent e) { return true; }
    @Override public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                      float distanceX, float distanceY) {
        offsetX -= distanceX;
        offsetY -= distanceY;
        this.invalidate();
        return true;
    }
    @Override public void onLongPress(MotionEvent e) { }
    @Override public boolean onFling(MotionEvent e1, MotionEvent e2,
                                     float velocityX, float velocityY) {
        return false;
    }
}