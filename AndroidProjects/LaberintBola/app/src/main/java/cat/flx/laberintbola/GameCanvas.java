package cat.flx.laberintbola;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RadialGradient;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.view.View;

public class GameCanvas extends View {

    public interface GameEndListener {
        public void gameFinished();
    }

    private GameMap map;
    private boolean playing;
    private float bX, bY;
    private float bVX, bVY;

    private Paint paintWall, paintBall, paintEnd;
    private GameEndListener lstnr;
    
    public GameCanvas(Context context) { this(context, null, 0); }
    public GameCanvas(Context context, AttributeSet attrs) { this(context, attrs, 0); }
    public GameCanvas(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        playing = false;
        bX = bY = 0.5f;
        bVX = bVY = 0;
        paintWall = new Paint();
        paintWall.setStyle(Style.STROKE);
        paintWall.setStrokeWidth(10);
        paintBall = new Paint();
        paintBall.setStyle(Style.FILL_AND_STROKE);
        paintEnd = new Paint(paintBall);
        paintEnd.setColor(Color.RED);
    }
    
    public void setOnGameEndListener(GameEndListener lstnr) { this.lstnr = lstnr; }
     
    public void setGameMap(GameMap map) {
        this.map = map;
        bX = map.getStartX() + 0.5f;
        bY = map.getStartY() + 0.5f;
        w = 0;
        this.invalidate();
    }
    
    public void setPlaying(boolean playing) {
        this.playing = playing;
        this.invalidate();
    }
        
    public void updateBall(float ax, float ay, float delta) {
        if (!playing) return;
        bVX += ax * delta * 0.2;
        bVY += ay * delta * 0.2;
        bVX = 0.97f * bVX;
        bVY = 0.97f * bVY;
        int i = (int) Math.floor(bX);
        int j = (int) Math.floor(bY);
        bX = bX + bVX * delta;
        bY = bY + bVY * delta;
        float dX = bX - i;
        float dY = bY - j;
        if ((dX <= 0.3) && map.hasWall(i, j, GameMap.LEFT)) { bX = i + 0.3f; bVX = -bVX; }
        if ((dX >= 0.7) && map.hasWall(i, j, GameMap.RIGHT)) { bX = i + 0.7f; bVX = -bVX; }
        if ((dY <= 0.3) && map.hasWall(i, j, GameMap.TOP)) { bY = j + 0.3f; bVY = -bVY; }
        if ((dY >= 0.7) && map.hasWall(i, j, GameMap.BOTTOM)) { bY = j + 0.7f; bVY = -bVY; }
        this.invalidate();
        if ((i == map.getEndX()) && (j == map.getEndY())) {
            if (dX < 0.4) return;
            if (dX > 0.6) return;
            if (dY < 0.4) return;
            if (dY > 0.6) return;
            if (lstnr != null) 
                lstnr.gameFinished();
        }
    }
    
    private int w, h, mw, mh, cs, r, top, left;
    
    @Override public void onDraw(Canvas canvas) {
        if (map == null) return;
        if (w != getWidth()) {
            w = getWidth();
            h = getHeight();
            mw = map.getWidth();
            mh = map.getHeight();
            int cw = (w - 10) / mw;
            int ch = (h - 10) / mh;
            cs = Math.min(cw, ch);
            top = (h - 10 - cs * mh) / 2 + 5;
            left = (w - 10 - cs * mw) / 2 + 5;
            r = (int)(cs * 0.3);
        }
        
        for (int y = 0; y < mh; y++) {
            for (int x = 0; x < mw; x++) {
                int cl = left + cs * x;
                int ct = top + cs * y;
                if (map.hasWall(x, y, GameMap.TOP)) 
                    canvas.drawLine(cl, ct, cl + cs, ct, paintWall);
                if (map.hasWall(x, y, GameMap.RIGHT)) 
                    canvas.drawLine(cl + cs, ct, cl + cs, ct + cs, paintWall);
                if (map.hasWall(x, y, GameMap.BOTTOM)) 
                    canvas.drawLine(cl, ct + cs, cl + cs, ct + cs, paintWall);
                if (map.hasWall(x, y, GameMap.LEFT)) 
                    canvas.drawLine(cl, ct, cl, ct + cs, paintWall);
            }
        }
        int x = (int)(left + cs * (map.getEndX() + 0.5));
        int y = (int)(top + cs * (map.getEndY() + 0.5));
        canvas.drawCircle(x, y, cs * 0.1f, paintEnd);

        x = (int)(left + cs * bX);
        y = (int)(top + cs * bY);
        drawBall(x, y, canvas);
    }
    
    public void drawBall(int x, int y, Canvas canvas) {
        paintBall.setShader(new RadialGradient(x - r/2, y - r/2, r, Color.WHITE, 0xff008000, TileMode.CLAMP));
        canvas.drawCircle(x, y, r, paintBall);
    }

}
