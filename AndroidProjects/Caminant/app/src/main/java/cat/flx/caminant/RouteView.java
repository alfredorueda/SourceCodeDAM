package cat.flx.caminant;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.location.Location;
import android.util.AttributeSet;
import android.view.View;

public class RouteView extends View
        implements Route.RouteListener {

    private Route route;
    private int left, top;
    private double scale;
    private double mleft, mright, mtop, mbottom;

    public RouteView(Context context) { this(context, null, 0); }
    public RouteView(Context context, AttributeSet attrs) { this(context, attrs, 0); }
    public RouteView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setRoute(Route route) {
        this.route = route;
        route.addRouteListener(this);
        update();
    }

    @Override public void latLonAdded(LatLon l) {
        update();
    }

    public void update() {
        int w = getWidth(); if (w == 0) return;
        int h = getHeight(); if (h == 0) return;
        double xspan = route.getMaxLongitude() - route.getMinLongitude();
        double yspan = route.getMaxLatitude() - route.getMinLatitude();
        scale = 1;
        if ((xspan != 0) && (yspan != 0)) {
            double xscale = (w - GAP - GAP) / xspan;
            double yscale = (h - GAP - GAP) / yspan;
            scale = Math.min(xscale, yscale);
        }
        left = (int)((w - scale * xspan) / 2);
        top = (int)((h - scale * yspan) / 2);
        Location ltl = new LatLon(route.getMinLatitude(), route.getMinLongitude()).getAsLocation();
        Location ltr = new LatLon(route.getMinLatitude(), route.getMaxLongitude()).getAsLocation();
        Location lbl = new LatLon(route.getMaxLatitude(), route.getMinLongitude()).getAsLocation();
        Location lbr = new LatLon(route.getMaxLatitude(), route.getMaxLongitude()).getAsLocation();
        mtop = ltl.distanceTo(ltr);
        mbottom = lbl.distanceTo(lbr);
        mleft = ltl.distanceTo(lbl);
        mright = ltr.distanceTo(lbr);
        this.invalidate();
    }

    private Paint pText, pDot, pLast, pAcc;
    private static final int GAP = 20;
    private RectF r;

    @Override public void onDraw(Canvas canvas) {
        if (route == null) return;
        int w = getWidth(); if (w == 0) return;
        int h = getHeight(); if (h == 0) return;
        if (pText == null) {
            pText = new Paint();
            pText.setColor(Color.BLACK);
            pText.setStyle(Style.STROKE);
            pDot = new Paint();
            pDot.setStyle(Style.FILL_AND_STROKE);
            pDot.setColor(Color.BLUE);
            pDot.setStrokeWidth(10);
            pLast = new Paint(pDot);
            pLast.setColor(Color.RED);
            pAcc = new Paint(pDot);
            pAcc.setColor(0x408080ff);
            r = new RectF();
        }
        //		canvas.drawColor(Color.WHITE);

        String txt = String.format("%.1f", mtop) + "m";
        canvas.drawText(txt, (w - pText.measureText(txt)) / 2, 10, pText);
        txt = String.format("%.1f", mbottom) + "m";
        canvas.drawText(txt, (w - pText.measureText(txt)) / 2, h - 2, pText);
        txt = String.format("%.1f", mleft)  + "m";
        int xtxt = 10, ytxt = (int)((h - pText.measureText(txt)) / 2);
        canvas.save();
        canvas.rotate(-90, xtxt, ytxt);
        canvas.drawText(txt, xtxt, ytxt, pText);
        canvas.restore();
        txt = String.format("%.1f", mright)  + "m";
        xtxt = w - 2; ytxt = (int)((h - pText.measureText(txt)) / 2);
        canvas.save();
        canvas.rotate(-90, xtxt, ytxt);
        canvas.drawText(txt, xtxt, ytxt, pText);
        canvas.restore();

        if (route.size() == 0) return;

        int x0 = -1, y0 = -1;
        for (int i = 0; i < route.size(); i++) {
            LatLon l = route.get(i);
            int x = (int)(left + (l.longitude - route.getMinLongitude()) * scale);
            int y = (int)(top + (route.getMaxLatitude() - l.latitude) * scale);
            if (x0 != -1) canvas.drawLine(x0, y0, x, y, pText);
            if (i == route.size() - 1) canvas.drawPoint(x, y, pLast);
            else canvas.drawPoint(x, y, pDot);
            x0 = x;
            y0 = y;
        }
        LatLon l = route.get(route.size() - 1);
        double ac = l.accuracy;
        double xradius = ac * w / (mtop + mbottom);
        double yradius = ac * h / (mleft + mright);
        r.left = (float)(x0 - xradius);
        r.right = (float)(x0 + xradius);
        r.top = (float)(y0 - yradius);
        r.bottom = (float)(y0 + yradius);
        canvas.drawOval(r, pAcc);
    }
}
