package com.example.xavi.joystick;

/**
 * Created by Xavi on 01/02/16.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class Joystick extends View implements GestureDetector.OnGestureListener {

    private Paint paint, paint2;
    private static final int ESPAI = 5;
    private GestureDetector gd;
    private int RADI;

    public Joystick(Context context) { this(context, null, 0); }
    public Joystick(Context context, AttributeSet attrs) { this(context, attrs, 0); }
    public Joystick(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        //Afegeix el detector de gestos
        gd = new GestureDetector(context, this);
        paint = new Paint();
        paint2 = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5.0f);

        paint2.setColor(Color.RED);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(5.0f);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //Fa el component cuadrat mirant el seu width i height
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        w = h = (w < h ) ? w : h;
        if (w < 50 || h < 50) { //fa que el mínim siguin 50p
            w = 50;
            h = 50;
        }
        setMeasuredDimension(w, h);
    }

    @Override
    public void onDraw(Canvas canvas) {
        int w = getWidth();
        int cw = w / 2;
        //L'espai es per a que el cercle es vegi be, si no es veia tallat
        int r = cw - ESPAI;
        canvas.drawCircle(cw, cw, r, paint);
        canvas.drawCircle(cw, cw, RADI, paint2); //Segón cercle que es va moguent
    }

    @Override
    public boolean onTouchEvent(MotionEvent e){ return gd.onTouchEvent(e); }

    @Override
    public boolean onDown(MotionEvent e) { return true; }

    @Override
    public void onShowPress(MotionEvent e) { }

    @Override
    public boolean onSingleTapUp(MotionEvent e) { return false; }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

        //Funcions per a treure el radi i l'angle
        int w = getWidth();
        int cw = w / 2;
        double x = e2.getX() - cw;
        double y = e2.getY() - cw;
        double r = Math.sqrt(x * x + y * y);
        double a = Math.atan2(x,-y);

        //Atura l'scroll si es surt del cercle
        if (r > cw) return false;

        //Logs per debugar
        Log.d("XMOLLV", ""+r);
        Log.d("XMOLLV", "" + a);

        this.invalidate();
        //Guarda el radi per poder dibuixar el segon cercle
        RADI = (int)r;
        //Si el listener existeix, se li pasa l'informació
        if (listener != null){
            listener.joystickMove(this,r,a);
        }

        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) { }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) { return false; }

    //Creació del listener per a la main activity
    private onJoystickListener listener;
    public void setOnJoystickListener(onJoystickListener listener){
        this.listener = listener;
    }

    //Interficie per a que la main activity hagi d'implementar tots els mètodes
    public interface onJoystickListener{
        public void joystickMove(View view, double r, double a);
    }
}