package com.example.xavi.tresenraya;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Xavi on 18/01/16.
 */
public class TresEnRayaView extends View implements GestureDetector.OnGestureListener{

    private Paint pCuadricula, pCirculo, pCruz;

    private String estado = " X OOX XO";

    private GestureDetector gd;

    public TresEnRayaView(Context context) { this(context, null, 0); }
    public TresEnRayaView(Context context, AttributeSet attrs) { this(context, attrs, 0); }
    public TresEnRayaView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        gd = new GestureDetector(context, this);
        pCuadricula = new Paint();
        pCuadricula.setColor(Color.LTGRAY);
        pCuadricula.setStyle(Paint.Style.STROKE);
        pCuadricula.setStrokeWidth(5);

        pCirculo = new Paint(pCuadricula);
        pCirculo.setColor(Color.BLUE);
        pCirculo.setStrokeWidth(20);

        pCruz = new Paint(pCirculo);
        pCruz.setColor(Color.GREEN);
    }

    @Override
    public  void onMeasure(int specw, int spech) {
        int w = MeasureSpec.getSize(specw);
        int h = MeasureSpec.getSize(spech);

        if (w < h) {
            h = w;
        } else {
            w = h;
        }

        setMeasuredDimension(w, h);
    }

    @Override
    public  void onDraw(Canvas canvas) {
        //canvas.drawColor(Color.RED);
        int w = getWidth();
        int cw = (w-10)/3;

        canvas.drawLine(5, 5+cw, w-5, 5+cw, pCuadricula);
        canvas.drawLine(5, 5+cw+cw, w-5, 5+cw+cw, pCuadricula);

        canvas.drawLine(5+cw, 5, 5+cw, w-5, pCuadricula);
        canvas.drawLine(5 + cw + cw, 5, 5 + cw + cw, w - 5, pCuadricula);

        for(int j = 0; j < 3; j++){
            for(int i = 0; i < 3; i++){
                char simbolo = estado.charAt(j*3+i);
                if (simbolo == ' ') continue;

                if (simbolo == 'X') {
                    int x1 = 5 + i * cw + 10;
                    int y1 = 5 + j * cw + 10;
                    int x2 = x1 + cw - 20;
                    int y2 = y1 + cw - 20 ;
                    canvas.drawLine(x1, y1, x2, y2, pCruz);
                    canvas.drawLine(x1, y2, x2, y1, pCruz);
                } else {
                    int cx = 5 + i * cw + cw / 2;
                    int cy = 5 + j * cw + cw / 2;
                    int r = cw / 2 - 10;
                    canvas.drawCircle(cx, cy, r, pCirculo);
                }
                //canvas.drawText(String.valueOf(simbolo), x1, y1 + cw / 2, pCirculo);
            }
        }
    }

    @Override
    public  boolean onTouchEvent(MotionEvent e){
        return gd.onTouchEvent(e);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
